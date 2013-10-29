package net.nan21.dnet.module.tx.business.ext.sale.delegate;

import java.util.Collection;
import java.util.List;

import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;

public class SalesTax_Bd extends AbstractBusinessDelegate {

	/**
	 * Create item taxes for an order line
	 * 
	 * @param item
	 * @param taxDefinition
	 * @param itemTaxes
	 */
	public void createLineTax(SalesOrderLine item, Tax taxDefinition,
			List<SalesOrderLineTax> itemTaxes) {

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
			SalesOrderLineTax tax = new SalesOrderLineTax();
			tax.setBaseAmount(item.getUnitPrice().multiply(item.getQuantity()));
			tax.setTax(tdef);
			tax.setTaxAmount(item.getUnitPrice().multiply(item.getQuantity())
					.multiply(tdef.getRate()));
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
	public void createLineTax(SalesInvoiceLine item, Tax taxDefinition,
			List<SalesInvoiceLineTax> itemTaxes) {

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
			SalesInvoiceLineTax tax = new SalesInvoiceLineTax();
			tax.setBaseAmount(item.getUnitPrice().multiply(item.getQuantity()));
			tax.setTax(tdef);
			tax.setTaxAmount(item.getUnitPrice().multiply(
					item.getQuantity().multiply(tdef.getRate())));
			tax.setLine(item);

			itemTaxes.add(tax);
		}
	}
}
