/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.sale.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderLineService;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesTax_Bd;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;

/**
 * Business extensions specific for {@link SalesOrderLine} domain entity.
 * 
 */
public class SalesOrderLine_Service extends
		net.nan21.dnet.module.tx.business.impl.sale.SalesOrderLine_Service
		implements ISalesOrderLineService {

	@Override
	protected void postInsert(SalesOrderLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postUpdate(SalesOrderLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postInsert(List<SalesOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postUpdate(List<SalesOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postDelete(List<SalesOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void preDeleteByIds(List<Object> ids, Map<String, Object> context) {
		context.put("docIds", this.findByIds(ids));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void postDeleteByIds(List<Object> ids, Map<String, Object> context) {
		this.onItemsChange((List<SalesOrderLine>) context.get("docIds"));
	}

	/**
	 * Notify document to recalculate its amounts.
	 * 
	 * @param list
	 */
	protected void onItemsChange(List<SalesOrderLine> list) {
		List<Object> docIds = new ArrayList<Object>();
		for (SalesOrderLine line : list) {
			if (!docIds.contains(line.getOrder().getId())) {
				docIds.add(line.getOrder().getId());
			}
		}
		for (Object id : docIds) {
			sendMessage("SalesOrder-calculateAmounts", id);
		}
	}

	/**
	 * Calculate line taxes.
	 * 
	 * @param line
	 * @throws BusinessException
	 */
	protected void calculateLineTaxesAndLineAmounts(SalesOrderLine line)
			throws BusinessException {
		int precision = line.getOrder().getCurrency().getStandardPrecision();
		RoundingMode rm = RoundingMode.HALF_UP;
		
		// calculate line amounts
		BigDecimal netAmount = line.getQuantity().multiply(line.getUnitPrice())
				.setScale(precision, rm);
		BigDecimal taxAmount = BigDecimal.ZERO;
		BigDecimal amount = netAmount;
		
		line.setNetAmount(netAmount);

		if (line.getTax() != null) {
			SalesTax_Bd delegate = this.getBusinessDelegate(SalesTax_Bd.class);
			List<SalesOrderLineTax> lineTaxes = new ArrayList<SalesOrderLineTax>();
			delegate.createLineTax(line, null, lineTaxes);
			 
			for (SalesOrderLineTax lineTax : lineTaxes) {
				taxAmount = lineTax.getTaxAmount().add(taxAmount);
			}
			
			taxAmount = taxAmount.setScale(precision, rm);
			amount = netAmount.add(taxAmount).setScale(precision, rm);
			
			line.setTaxAmount(taxAmount);
			line.setAmount(amount);
			this.getEntityManager().merge(line);
			this.getEntityManager()
					.createQuery(
							"delete from "
									+ SalesOrderLineTax.class.getSimpleName()
									+ " e where e.line.id = :lineId")
					.setParameter("lineId", line.getId()).executeUpdate();

			for (SalesOrderLineTax itemTax : lineTaxes) {
				this.getEntityManager().persist(itemTax);
			}
		}
	}

}
