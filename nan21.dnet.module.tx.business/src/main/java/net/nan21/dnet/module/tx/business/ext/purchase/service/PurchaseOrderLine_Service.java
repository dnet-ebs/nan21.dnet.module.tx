/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.purchase.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderLineService;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseTax_Bd;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

/**
 * Business extensions specific for {@link PurchaseOrderLine} domain entity.
 * 
 */
public class PurchaseOrderLine_Service
		extends
		net.nan21.dnet.module.tx.business.impl.purchase.PurchaseOrderLine_Service
		implements IPurchaseOrderLineService {

	@Override
	protected void postInsert(PurchaseOrderLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postUpdate(PurchaseOrderLine e) throws BusinessException {
		this.calculateLineTaxesAndLineAmounts(e);
	}

	@Override
	protected void postInsert(List<PurchaseOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postUpdate(List<PurchaseOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void postDelete(List<PurchaseOrderLine> list) {
		this.onItemsChange(list);
	}

	@Override
	protected void preDeleteByIds(List<Object> ids, Map<String, Object> context) {
		context.put("docIds", this.findByIds(ids));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void postDeleteByIds(List<Object> ids, Map<String, Object> context) {
		this.onItemsChange((List<PurchaseOrderLine>) context.get("docIds"));
	}

	/**
	 * Notify document to recalculate its amounts.
	 * 
	 * @param list
	 */
	protected void onItemsChange(List<PurchaseOrderLine> list) {
		List<Object> docIds = new ArrayList<Object>();
		for (PurchaseOrderLine line : list) {
			if (!docIds.contains(line.getOrder().getId())) {
				docIds.add(line.getOrder().getId());
			}
		}
		for (Object id : docIds) {
			sendMessage("PurchaseOrder-calculateAmounts", id);
		}
	}

	/**
	 * Calculate line taxes.
	 * 
	 * @param line
	 * @throws BusinessException
	 */
	protected void calculateLineTaxesAndLineAmounts(PurchaseOrderLine line)
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
			PurchaseTax_Bd delegate = this
					.getBusinessDelegate(PurchaseTax_Bd.class);
			List<PurchaseOrderLineTax> lineTaxes = new ArrayList<PurchaseOrderLineTax>();
			delegate.createLineTax(line, null, lineTaxes);

			for (PurchaseOrderLineTax lineTax : lineTaxes) {
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
									+ PurchaseOrderLineTax.class
											.getSimpleName()
									+ " e where e.line.id = :lineId")
					.setParameter("lineId", line.getId()).executeUpdate();

			for (PurchaseOrderLineTax itemTax : lineTaxes) {
				this.getEntityManager().persist(itemTax);
			}
		}
	}

}
