package net.nan21.dnet.module.tx.business.ext.purchase.delegate;

import java.math.BigDecimal;
import java.util.List;

import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceTax;

public class PurchaseInvoice_Bd extends AbstractBusinessDelegate {

	/**
	 * Calculate the document level amounts for the given invoice id.
	 * 
	 * @param invoiceId
	 */
	public void calculateDocumentAmounts(String invoiceId) {
		this.getEntityManager().flush();
		Object[] x = (Object[]) this
				.getEntityManager()
				.createQuery(
						"select sum(i.netAmount), sum(i.taxAmount) from "
								+ PurchaseInvoiceLine.class.getSimpleName()
								+ " i where i.invoice.id = :invoiceId")
				.setParameter("invoiceId", invoiceId).getSingleResult();
		PurchaseInvoice doc = this.getEntityManager().find(
				PurchaseInvoice.class, invoiceId);
		BigDecimal netAmount = new BigDecimal("0");
		BigDecimal taxAmount = new BigDecimal("0");

		if (x[0] != null && x[1] != null) {
			netAmount = new BigDecimal(x[0] + "");
			taxAmount = new BigDecimal(x[1] + "");
		}

		doc.setNetAmount(netAmount);
		doc.setTaxAmount(taxAmount);
		doc.setAmount(netAmount.add(taxAmount));

		// TODO: calculate the amounts in local and reference currencies

		// re-create taxes:

		// delete existing
		this.getEntityManager()
				.createQuery(
						"delete from "
								+ PurchaseInvoiceTax.class.getSimpleName()
								+ " t " + " where t.invoice.id = :invoiceId")
				.setParameter("invoiceId", invoiceId).executeUpdate();

		// create new
		@SuppressWarnings("unchecked")
		List<Object[]> taxes = (List<Object[]>) this
				.getEntityManager()
				.createQuery(
						"select i.tax,  sum(i.baseAmount), sum(i.taxAmount) from "
								+ PurchaseInvoiceLineTax.class.getSimpleName()
								+ " i "
								+ " where i.line.invoice.id = :invoiceId "
								+ " group by i.tax ")
				.setParameter("invoiceId", invoiceId).getResultList();
		for (Object[] tax : taxes) {
			Tax t = (Tax) tax[0];
			BigDecimal baseval = new BigDecimal(tax[1] + "");
			BigDecimal taxval = new BigDecimal(tax[2] + "");
			PurchaseInvoiceTax docTax = new PurchaseInvoiceTax();
			docTax.setInvoice(doc);
			docTax.setTax(t);
			docTax.setBaseAmount(baseval);
			docTax.setTaxAmount(taxval);

			// TODO: calculate the amounts in local and reference currencies

			doc.addToTaxes(docTax);
		}

		this.getEntityManager().merge(doc);
	}

}
