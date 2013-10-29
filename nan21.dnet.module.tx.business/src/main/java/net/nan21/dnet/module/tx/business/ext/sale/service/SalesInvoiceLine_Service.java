/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.sale.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceLineService;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesTax_Bd;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;

/**
 * Business extensions specific for {@link SalesInvoiceLine} domain entity.
 * 
 */
public class SalesInvoiceLine_Service extends
		net.nan21.dnet.module.tx.business.impl.sale.SalesInvoiceLine_Service
		implements ISalesInvoiceLineService {

	@Override
	protected void postInsert(SalesInvoiceLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postUpdate(SalesInvoiceLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postInsert(List<SalesInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postUpdate(List<SalesInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postDelete(List<SalesInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void preDeleteByIds(List<Object> ids, Map<String, Object> context) {
		context.put("docIds", this.findByIds(ids));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void postDeleteByIds(List<Object> ids, Map<String, Object> context) {
		this.onItemsChange((List<SalesInvoiceLine>) context.get("docIds"));
	}

	/**
	 * Notify document to recalculate its amounts.
	 * 
	 * @param list
	 */
	protected void onItemsChange(List<SalesInvoiceLine> list) {
		List<Object> docIds = new ArrayList<Object>();
		for (SalesInvoiceLine line : list) {
			if (!docIds.contains(line.getInvoice().getId())) {
				docIds.add(line.getInvoice().getId());
			}
		}
		for (Object id : docIds) {
			sendMessage("SalesInvoice-calculateAmounts", id);
		}
	}

	/**
	 * Calculate line taxes.
	 * 
	 * @param line
	 * @throws BusinessException
	 */
	protected void calculateLineTaxesAndLineAmounts(SalesInvoiceLine line)
			throws BusinessException {

		// calculate line amounts
		line.setNetAmount(line.getQuantity().multiply(line.getUnitPrice()));

		if (line.getTax() != null) {
			SalesTax_Bd delegate = this.getBusinessDelegate(SalesTax_Bd.class);
			List<SalesInvoiceLineTax> lineTaxes = new ArrayList<SalesInvoiceLineTax>();
			delegate.createLineTax(line, null, lineTaxes);
			BigDecimal taxAmount = new BigDecimal(0);
			for (SalesInvoiceLineTax lineTax : lineTaxes) {
				taxAmount = lineTax.getTaxAmount().add(taxAmount);
			}
			line.setTaxAmount(taxAmount);
			line.setAmount(line.getNetAmount().add(taxAmount));
			this.getEntityManager().merge(line);
			this.getEntityManager()
					.createQuery(
							"delete from "
									+ SalesInvoiceLineTax.class.getSimpleName()
									+ " e where e.line.id = :lineId")
					.setParameter("lineId", line.getId()).executeUpdate();

			for (SalesInvoiceLineTax itemTax : lineTaxes) {
				this.getEntityManager().persist(itemTax);
			}
		}
	}

}
