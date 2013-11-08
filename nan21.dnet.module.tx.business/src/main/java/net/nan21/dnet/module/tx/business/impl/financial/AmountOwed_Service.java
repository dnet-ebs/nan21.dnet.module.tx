/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.financial;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.tx.business.api.financial.IAmountOwedService;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;

/**
 * Repository functionality for {@link AmountOwed} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class AmountOwed_Service extends AbstractEntityService<AmountOwed>
		implements
			IAmountOwedService {

	public AmountOwed_Service() {
		super();
	}

	public AmountOwed_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<AmountOwed> getEntityClass() {
		return AmountOwed.class;
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<AmountOwed> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<AmountOwed> findByBpAccountId(String bpAccountId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<AmountOwed> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<AmountOwed> findByCurrencyId(String currencyId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.currency.id = :currencyId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: salesOrder
	 */
	public List<AmountOwed> findBySalesOrder(SalesOrder salesOrder) {
		return this.findBySalesOrderId(salesOrder.getId());
	}
	/**
	 * Find by ID of reference: salesOrder.id
	 */
	public List<AmountOwed> findBySalesOrderId(String salesOrderId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.salesOrder.id = :salesOrderId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("salesOrderId", salesOrderId).getResultList();
	}
	/**
	 * Find by reference: salesInvoice
	 */
	public List<AmountOwed> findBySalesInvoice(SalesInvoice salesInvoice) {
		return this.findBySalesInvoiceId(salesInvoice.getId());
	}
	/**
	 * Find by ID of reference: salesInvoice.id
	 */
	public List<AmountOwed> findBySalesInvoiceId(String salesInvoiceId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.salesInvoice.id = :salesInvoiceId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("salesInvoiceId", salesInvoiceId).getResultList();
	}
	/**
	 * Find by reference: purchaseOrder
	 */
	public List<AmountOwed> findByPurchaseOrder(PurchaseOrder purchaseOrder) {
		return this.findByPurchaseOrderId(purchaseOrder.getId());
	}
	/**
	 * Find by ID of reference: purchaseOrder.id
	 */
	public List<AmountOwed> findByPurchaseOrderId(String purchaseOrderId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.purchaseOrder.id = :purchaseOrderId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("purchaseOrderId", purchaseOrderId)
				.getResultList();
	}
	/**
	 * Find by reference: purchaseInvoice
	 */
	public List<AmountOwed> findByPurchaseInvoice(
			PurchaseInvoice purchaseInvoice) {
		return this.findByPurchaseInvoiceId(purchaseInvoice.getId());
	}
	/**
	 * Find by ID of reference: purchaseInvoice.id
	 */
	public List<AmountOwed> findByPurchaseInvoiceId(String purchaseInvoiceId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.purchaseInvoice.id = :purchaseInvoiceId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("purchaseInvoiceId", purchaseInvoiceId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentMethod
	 */
	public List<AmountOwed> findByPaymentMethod(DocType paymentMethod) {
		return this.findByPaymentMethodId(paymentMethod.getId());
	}
	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<AmountOwed> findByPaymentMethodId(String paymentMethodId) {
		return (List<AmountOwed>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountOwed e where e.clientId = :clientId and e.paymentMethod.id = :paymentMethodId",
						AmountOwed.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentMethodId", paymentMethodId)
				.getResultList();
	}
}
