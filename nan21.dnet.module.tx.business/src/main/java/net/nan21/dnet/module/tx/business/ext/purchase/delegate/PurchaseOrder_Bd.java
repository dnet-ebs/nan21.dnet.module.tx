package net.nan21.dnet.module.tx.business.ext.purchase.delegate;

import java.math.BigDecimal;
import java.util.List;

import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderTax;

public class PurchaseOrder_Bd extends AbstractBusinessDelegate {
	/**
	 * Calculate the document level amounts for the given order id.
	 * 
	 * @param invoiceId
	 */
	public void calculateDocumentAmounts(String orderId) {
		this.getEntityManager().flush();
		Object[] x = (Object[]) this
				.getEntityManager()
				.createQuery(
						"select sum(i.netAmount), sum(i.taxAmount) from "
								+ PurchaseOrderLine.class.getSimpleName()
								+ " i " + "where i.order.id = :orderId")
				.setParameter("orderId", orderId).getSingleResult();
		PurchaseOrder doc = this.getEntityManager().find(PurchaseOrder.class,
				orderId);

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
						"delete from " + PurchaseOrderTax.class.getSimpleName()
								+ " t " + " where t.order.id = :orderId")
				.setParameter("orderId", orderId).executeUpdate();

		// create new
		@SuppressWarnings("unchecked")
		List<Object[]> taxes = (List<Object[]>) this
				.getEntityManager()
				.createQuery(
						"select i.tax,  sum(i.baseAmount), sum(i.taxAmount) from "
								+ PurchaseOrderLineTax.class.getSimpleName()
								+ " i " + " where i.line.order.id = :orderId "
								+ " group by i.tax ")
				.setParameter("orderId", orderId).getResultList();
		for (Object[] tax : taxes) {
			Tax t = (Tax) tax[0];
			BigDecimal baseval = new BigDecimal(tax[1] + "");
			BigDecimal taxval = new BigDecimal(tax[2] + "");
			PurchaseOrderTax docTax = new PurchaseOrderTax();
			docTax.setOrder(doc);
			docTax.setTax(t);
			docTax.setBaseAmount(baseval);
			docTax.setTaxAmount(taxval);

			// TODO: calculate the amounts in local and reference currencies
			doc.addToTaxes(docTax);
		}

		this.getEntityManager().merge(doc);
	}
}
