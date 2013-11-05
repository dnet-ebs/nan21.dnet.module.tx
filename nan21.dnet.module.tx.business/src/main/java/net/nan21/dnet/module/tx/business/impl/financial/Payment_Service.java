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
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentLine;

/**
 * Repository functionality for {@link Payment} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class Payment_Service extends AbstractEntityService<Payment> {

	public Payment_Service() {
		super();
	}

	public Payment_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<Payment> getEntityClass() {
		return Payment.class;
	}
	/**
	 * Find by unique key
	 */
	public Payment findByDocno(String docNo) {
		return (Payment) this
				.getEntityManager()
				.createNamedQuery(Payment.NQ_FIND_BY_DOCNO)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docNo", docNo).getSingleResult();
	}
	/**
	 * Find by reference: docType
	 */
	public List<Payment> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<Payment> findByDocTypeId(String docTypeId) {
		return (List<Payment>) this
				.getEntityManager()
				.createQuery(
						"select e from Payment e where e.clientId = :clientId and e.docType.id = :docTypeId",
						Payment.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<Payment> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<Payment> findByCurrencyId(String currencyId) {
		return (List<Payment>) this
				.getEntityManager()
				.createQuery(
						"select e from Payment e where e.clientId = :clientId and e.currency.id = :currencyId",
						Payment.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: company
	 */
	public List<Payment> findByCompany(Org company) {
		return this.findByCompanyId(company.getId());
	}
	/**
	 * Find by ID of reference: company.id
	 */
	public List<Payment> findByCompanyId(String companyId) {
		return (List<Payment>) this
				.getEntityManager()
				.createQuery(
						"select e from Payment e where e.clientId = :clientId and e.company.id = :companyId",
						Payment.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("companyId", companyId).getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<Payment> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<Payment> findByBpAccountId(String bpAccountId) {
		return (List<Payment>) this
				.getEntityManager()
				.createQuery(
						"select e from Payment e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						Payment.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<Payment> findByLines(PaymentLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<Payment> findByLinesId(String linesId) {
		return (List<Payment>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from Payment e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						Payment.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
}
