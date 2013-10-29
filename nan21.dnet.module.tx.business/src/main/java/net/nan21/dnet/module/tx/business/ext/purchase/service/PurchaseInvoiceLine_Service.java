/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.purchase.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceLineService;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseTax_Bd;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;

/**
 * Business extensions specific for {@link PurchaseInvoiceLine} domain entity.
 * 
 */
public class PurchaseInvoiceLine_Service
		extends
		net.nan21.dnet.module.tx.business.impl.purchase.PurchaseInvoiceLine_Service
		implements IPurchaseInvoiceLineService {

	@Override
	protected void postInsert(PurchaseInvoiceLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postUpdate(PurchaseInvoiceLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postInsert(List<PurchaseInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postUpdate(List<PurchaseInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postDelete(List<PurchaseInvoiceLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void preDeleteByIds(List<Object> ids, Map<String, Object> context) {
		context.put("docIds", this.findByIds(ids));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void postDeleteByIds(List<Object> ids, Map<String, Object> context) {
		this.onItemsChange((List<PurchaseInvoiceLine>) context.get("docIds"));
	}

	/**
	 * Notify document to recalculate its amounts.
	 * 
	 * @param list
	 */
	protected void onItemsChange(List<PurchaseInvoiceLine> list) {
		List<Object> docIds = new ArrayList<Object>();
		for (PurchaseInvoiceLine line : list) {
			if (!docIds.contains(line.getInvoice().getId())) {
				docIds.add(line.getInvoice().getId());
			}
		}
		for (Object id : docIds) {
			sendMessage("PurchaseInvoice-calculateAmounts", id);
		}
	}

	/**
	 * Calculate line taxes.
	 * 
	 * @param line
	 * @throws BusinessException
	 */
	protected void calculateLineTaxesAndLineAmounts(PurchaseInvoiceLine line)
			throws BusinessException {

		// calculate line amounts
		line.setNetAmount(line.getQuantity().multiply(line.getUnitPrice()));

		if (line.getTax() != null) {
			PurchaseTax_Bd delegate = this
					.getBusinessDelegate(PurchaseTax_Bd.class);

			List<PurchaseInvoiceLineTax> lineTaxes = new ArrayList<PurchaseInvoiceLineTax>();
			delegate.createLineTax(line, null, lineTaxes);
			BigDecimal taxAmount = new BigDecimal(0);

			for (PurchaseInvoiceLineTax lineTax : lineTaxes) {
				taxAmount = lineTax.getTaxAmount().add(taxAmount);
			}
			line.setTaxAmount(taxAmount);
			line.setAmount(line.getNetAmount().add(taxAmount));
			this.getEntityManager().merge(line);
			this.getEntityManager()
					.createQuery(
							"delete from "
									+ PurchaseInvoiceLineTax.class
											.getSimpleName()
									+ " e where e.line.id = :lineId")
					.setParameter("lineId", line.getId()).executeUpdate();

			for (PurchaseInvoiceLineTax itemTax : lineTaxes) {
				this.getEntityManager().persist(itemTax);
			}
		}
	}

}
