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
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderTax;

/**
 * Repository functionality for {@link PurchaseOrder} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseOrder_Service extends AbstractEntityService<PurchaseOrder> {

	public PurchaseOrder_Service() {
		super();
	}

	public PurchaseOrder_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseOrder> getEntityClass() {
		return PurchaseOrder.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseOrder findByDocno(String docNo) {
		return (PurchaseOrder) this
				.getEntityManager()
				.createNamedQuery(PurchaseOrder.NQ_FIND_BY_DOCNO)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docNo", docNo).getSingleResult();
	}
	/**
	 * Find by reference: docType
	 */
	public List<PurchaseOrder> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<PurchaseOrder> findByDocTypeId(String docTypeId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrder e where e.clientId = :clientId and e.docType.id = :docTypeId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: company
	 */
	public List<PurchaseOrder> findByCompany(Org company) {
		return this.findByCompanyId(company.getId());
	}
	/**
	 * Find by ID of reference: company.id
	 */
	public List<PurchaseOrder> findByCompanyId(String companyId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrder e where e.clientId = :clientId and e.company.id = :companyId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("companyId", companyId).getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<PurchaseOrder> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<PurchaseOrder> findByBpAccountId(String bpAccountId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrder e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<PurchaseOrder> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<PurchaseOrder> findByCurrencyId(String currencyId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrder e where e.clientId = :clientId and e.currency.id = :currencyId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: org
	 */
	public List<PurchaseOrder> findByOrg(Org org) {
		return this.findByOrgId(org.getId());
	}
	/**
	 * Find by ID of reference: org.id
	 */
	public List<PurchaseOrder> findByOrgId(String orgId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrder e where e.clientId = :clientId and e.org.id = :orgId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orgId", orgId).getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<PurchaseOrder> findByLines(PurchaseOrderLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<PurchaseOrder> findByLinesId(String linesId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseOrder e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
	/**
	 * Find by reference: taxes
	 */
	public List<PurchaseOrder> findByTaxes(PurchaseOrderTax taxes) {
		return this.findByTaxesId(taxes.getId());
	}
	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<PurchaseOrder> findByTaxesId(String taxesId) {
		return (List<PurchaseOrder>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseOrder e, IN (e.taxes) c where e.clientId = :clientId and c.id = :taxesId",
						PurchaseOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxesId", taxesId).getResultList();
	}
}
