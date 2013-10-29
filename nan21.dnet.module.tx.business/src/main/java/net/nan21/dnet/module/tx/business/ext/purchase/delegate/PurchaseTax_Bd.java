package net.nan21.dnet.module.tx.business.ext.purchase.delegate;

import java.util.Collection;
import java.util.List;

import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

public class PurchaseTax_Bd extends AbstractBusinessDelegate {

	/**
	 * Create item taxes for an order line
	 * 
	 * @param item
	 * @param taxDefinition
	 * @param itemTaxes
	 */
	public void createLineTax(PurchaseOrderLine item, Tax taxDefinition,
			List<PurchaseOrderLineTax> itemTaxes) {

		Tax tdef = null;
		if (taxDefinition == null) {
			tdef = item.getTax();
		} else {
			tdef = taxDefinition;
		}
		if (tdef.getSummary()) {
			Collection<Tax> childtdefs = tdef.getChildren();
			for (Tax childtdef : childtdefs) {
				this.createLineTax(item, childtdef, itemTaxes);
			}
		} else {
			PurchaseOrderLineTax tax = new PurchaseOrderLineTax();
			tax.setBaseAmount(item.getUnitPrice().multiply(item.getQuantity()));
			tax.setTax(tdef);
			tax.setTaxAmount(item.getUnitPrice().multiply(
					item.getQuantity().multiply(tdef.getRate())));
			tax.setLine(item);

			itemTaxes.add(tax);
		}
	}

	/**
	 * Create item taxes for an invoice line
	 * 
	 * @param item
	 * @param taxDefinition
	 * @param itemTaxes
	 */
	public void createLineTax(PurchaseInvoiceLine item, Tax taxDefinition,
			List<PurchaseInvoiceLineTax> itemTaxes) {

		Tax tdef = null;
		if (taxDefinition == null) {
			tdef = item.getTax();
		} else {
			tdef = taxDefinition;
		}
		if (tdef.getSummary()) {
			Collection<Tax> childtdefs = tdef.getChildren();
			for (Tax childtdef : childtdefs) {
				this.createLineTax(item, childtdef, itemTaxes);
			}
		} else {
			PurchaseInvoiceLineTax tax = new PurchaseInvoiceLineTax();
			tax.setBaseAmount(item.getUnitPrice().multiply(item.getQuantity()));
			tax.setTax(tdef);
			tax.setTaxAmount(item.getUnitPrice().multiply(
					item.getQuantity().multiply(tdef.getRate())));
			tax.setLine(item);

			itemTaxes.add(tax);
		}
	}
}
