package net.nan21.dnet.module.tx.business.ext.sale.delegate;

import java.math.BigDecimal;
import java.util.List;

import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;

public class SalesInvoice_Bd extends AbstractBusinessDelegate {

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
								+ SalesInvoiceLine.class.getSimpleName()
								+ " i where i.invoice.id = :invoiceId")
				.setParameter("invoiceId", invoiceId).getSingleResult();
		SalesInvoice doc = this.getEntityManager().find(SalesInvoice.class,
				invoiceId);
		BigDecimal netAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;

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
						"delete from " + SalesInvoiceTax.class.getSimpleName()
								+ " t " + " where t.invoice.id = :invoiceId")
				.setParameter("invoiceId", invoiceId).executeUpdate();

		// create new
		@SuppressWarnings("unchecked")
		List<Object[]> taxes = (List<Object[]>) this
				.getEntityManager()
				.createQuery(
						"select i.tax,  sum(i.baseAmount), sum(i.taxAmount) from "
								+ SalesInvoiceLineTax.class.getSimpleName()
								+ " i "
								+ " where i.line.invoice.id = :invoiceId "
								+ " group by i.tax ")
				.setParameter("invoiceId", invoiceId).getResultList();
		for (Object[] tax : taxes) {
			Tax t = (Tax) tax[0];

			BigDecimal baseval = new BigDecimal(tax[1] + "");
			BigDecimal taxval = new BigDecimal(tax[2] + "");

			SalesInvoiceTax docTax = new SalesInvoiceTax();
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
