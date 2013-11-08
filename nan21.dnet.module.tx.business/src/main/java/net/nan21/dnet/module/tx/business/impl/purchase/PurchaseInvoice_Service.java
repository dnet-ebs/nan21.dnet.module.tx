/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.purchase;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceTax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;

/**
 * Repository functionality for {@link PurchaseInvoice} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseInvoice_Service
		extends
			AbstractEntityService<PurchaseInvoice> {

	public PurchaseInvoice_Service() {
		super();
	}

	public PurchaseInvoice_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseInvoice> getEntityClass() {
		return PurchaseInvoice.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseInvoice findByDocno(String docNo) {
		return (PurchaseInvoice) this
				.getEntityManager()
				.createNamedQuery(PurchaseInvoice.NQ_FIND_BY_DOCNO)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docNo", docNo).getSingleResult();
	}
	/**
	 * Find by reference: docType
	 */
	public List<PurchaseInvoice> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<PurchaseInvoice> findByDocTypeId(String docTypeId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.docType.id = :docTypeId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: company
	 */
	public List<PurchaseInvoice> findByCompany(Org company) {
		return this.findByCompanyId(company.getId());
	}
	/**
	 * Find by ID of reference: company.id
	 */
	public List<PurchaseInvoice> findByCompanyId(String companyId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.company.id = :companyId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("companyId", companyId).getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<PurchaseInvoice> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<PurchaseInvoice> findByBpAccountId(String bpAccountId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<PurchaseInvoice> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<PurchaseInvoice> findByCurrencyId(String currencyId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.currency.id = :currencyId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: org
	 */
	public List<PurchaseInvoice> findByOrg(Org org) {
		return this.findByOrgId(org.getId());
	}
	/**
	 * Find by ID of reference: org.id
	 */
	public List<PurchaseInvoice> findByOrgId(String orgId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.org.id = :orgId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orgId", orgId).getResultList();
	}
	/**
	 * Find by reference: paymentMethod
	 */
	public List<PurchaseInvoice> findByPaymentMethod(DocType paymentMethod) {
		return this.findByPaymentMethodId(paymentMethod.getId());
	}
	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<PurchaseInvoice> findByPaymentMethodId(String paymentMethodId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.paymentMethod.id = :paymentMethodId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentMethodId", paymentMethodId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentTerm
	 */
	public List<PurchaseInvoice> findByPaymentTerm(PaymentTerm paymentTerm) {
		return this.findByPaymentTermId(paymentTerm.getId());
	}
	/**
	 * Find by ID of reference: paymentTerm.id
	 */
	public List<PurchaseInvoice> findByPaymentTermId(String paymentTermId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.paymentTerm.id = :paymentTermId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentTermId", paymentTermId).getResultList();
	}
	/**
	 * Find by reference: purchaseOrder
	 */
	public List<PurchaseInvoice> findByPurchaseOrder(PurchaseOrder purchaseOrder) {
		return this.findByPurchaseOrderId(purchaseOrder.getId());
	}
	/**
	 * Find by ID of reference: purchaseOrder.id
	 */
	public List<PurchaseInvoice> findByPurchaseOrderId(String purchaseOrderId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoice e where e.clientId = :clientId and e.purchaseOrder.id = :purchaseOrderId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("purchaseOrderId", purchaseOrderId)
				.getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<PurchaseInvoice> findByLines(PurchaseInvoiceLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<PurchaseInvoice> findByLinesId(String linesId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseInvoice e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
	/**
	 * Find by reference: taxes
	 */
	public List<PurchaseInvoice> findByTaxes(PurchaseInvoiceTax taxes) {
		return this.findByTaxesId(taxes.getId());
	}
	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<PurchaseInvoice> findByTaxesId(String taxesId) {
		return (List<PurchaseInvoice>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseInvoice e, IN (e.taxes) c where e.clientId = :clientId and c.id = :taxesId",
						PurchaseInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxesId", taxesId).getResultList();
	}
}
