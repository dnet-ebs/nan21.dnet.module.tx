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
	protected void preInsert(PurchaseInvoiceLine e) throws BusinessException {
		this.applyEntryModePreSave(e);
	}

	@Override
	protected void preUpdate(PurchaseInvoiceLine e) throws BusinessException {
		this.applyEntryModePreSave(e);
	}

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

		int precision = line.getInvoice().getCurrency().getStandardPrecision();
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

			List<PurchaseInvoiceLineTax> lineTaxes = new ArrayList<PurchaseInvoiceLineTax>();
			delegate.createLineTax(line, null, lineTaxes);

			if (!line.getUseGivenTax()) {
				for (PurchaseInvoiceLineTax lineTax : lineTaxes) {
					taxAmount = lineTax.getTaxAmount().add(taxAmount);
				}
				taxAmount = taxAmount.setScale(precision, rm);

				if (!line.getUseGivenTax()) {
					line.setTaxAmount(taxAmount);
				}

				amount = netAmount.add(taxAmount).setScale(precision, rm);

				line.setTaxAmount(taxAmount);
				line.setAmount(amount);
				this.getEntityManager().merge(line);
			}

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

	/**
	 * Calculate amounts based on selected entry-mode
	 * 
	 * @param e
	 */
	protected void applyEntryModePreSave(PurchaseInvoiceLine e) {
		if (e.getEntryMode() == null) {
			e.setEntryMode("price");
		}
		e.setUseGivenTax(false);
		BigDecimal ZERO = BigDecimal.ZERO;

		// BigDecimal quantity = e.getQuantity();
		// BigDecimal unitPrice = e.getUnitPrice();
		// BigDecimal netAmount = e.getNetAmount();
		// BigDecimal taxAmount = e.getTaxAmount();
		// BigDecimal amount = e.getAmount();

		BigDecimal quantity = (e.getQuantity() != null) ? e.getQuantity()
				: BigDecimal.ONE;
		BigDecimal unitPrice = (e.getUnitPrice() != null) ? e.getUnitPrice()
				: ZERO;
		BigDecimal netAmount = (e.getNetAmount() != null) ? e.getNetAmount()
				: ZERO;
		BigDecimal taxAmount = (e.getTaxAmount() != null) ? e.getTaxAmount()
				: ZERO;
		BigDecimal amount = (e.getAmount() != null) ? e.getAmount() : ZERO;

		if (e.getEntryMode().equals("price")) {

			netAmount = quantity.multiply(unitPrice);

		} else {
			e.setUseGivenTax(true);
			if (quantity.compareTo(ZERO) == 0) {
				quantity = BigDecimal.ONE;
			}

			if (netAmount.compareTo(ZERO) == 0) {
				netAmount = amount.subtract(taxAmount);
			} else if (amount.compareTo(ZERO) == 0) {
				amount = taxAmount.add(netAmount);
			} else if (taxAmount.compareTo(ZERO) == 0) {
				taxAmount = amount.subtract(netAmount);
			}
			unitPrice = netAmount.divide(quantity);
		}

		// apply values

		e.setQuantity(quantity);
		e.setUnitPrice(unitPrice);
		e.setNetAmount(netAmount);
		e.setTaxAmount(taxAmount);
		e.setAmount(amount);

	}

	// if (netAmount == null) {
	// // !net, tax, total
	// if (taxAmount != null && amount != null) {
	// // calculate as total - tax and use the given tax
	// e.setUseGivenTax(true);
	// netAmount = amount.subtract(taxAmount);
	// } else {
	// // !net, !tax , total
	// if (amount != null) {
	// if (e.getTax() == null) {
	// netAmount = amount;
	// taxAmount = ZERO;
	//
	// } else {
	// throw new RuntimeException(
	// "Insufficient information provided for `net-amount` entry mode. ");
	// }
	// } else {
	// throw new RuntimeException(
	// "Insufficient information provided for `net-amount` entry mode. ");
	// }
	//
	// }
	// if (netAmount.compareTo(ZERO) == 0) {
	// if (quantity == null) {
	// quantity = BigDecimal.ONE;
	// }
	// unitPrice = netAmount.divide(quantity);
	// }
	// } else {
	// if (taxAmount == null) {
	// if (amount != null) {
	// taxAmount = amount.subtract(netAmount);
	// } else {
	// taxAmount = ZERO;
	// }
	// } else {
	// if (amount == null) {
	// e.setUseGivenTax(true);
	// amount = netAmount.add(taxAmount);
	// }
	// }
	// if (quantity == null) {
	// quantity = BigDecimal.ONE;
	// }
	// unitPrice = netAmount.divide(quantity);
	// }

}
