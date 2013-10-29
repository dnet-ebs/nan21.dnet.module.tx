/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.sale;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;

/**
 * Repository functionality for {@link SalesInvoice} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesInvoice_Service extends AbstractEntityService<SalesInvoice> {

	public SalesInvoice_Service() {
		super();
	}

	public SalesInvoice_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesInvoice> getEntityClass() {
		return SalesInvoice.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesInvoice findByDocno(String docNo) {
		return (SalesInvoice) this
				.getEntityManager()
				.createNamedQuery(SalesInvoice.NQ_FIND_BY_DOCNO)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docNo", docNo).getSingleResult();
	}
	/**
	 * Find by reference: docType
	 */
	public List<SalesInvoice> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<SalesInvoice> findByDocTypeId(String docTypeId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.docType.id = :docTypeId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: company
	 */
	public List<SalesInvoice> findByCompany(Org company) {
		return this.findByCompanyId(company.getId());
	}
	/**
	 * Find by ID of reference: company.id
	 */
	public List<SalesInvoice> findByCompanyId(String companyId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.company.id = :companyId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("companyId", companyId).getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<SalesInvoice> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<SalesInvoice> findByBpAccountId(String bpAccountId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<SalesInvoice> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<SalesInvoice> findByCurrencyId(String currencyId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.currency.id = :currencyId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: org
	 */
	public List<SalesInvoice> findByOrg(Org org) {
		return this.findByOrgId(org.getId());
	}
	/**
	 * Find by ID of reference: org.id
	 */
	public List<SalesInvoice> findByOrgId(String orgId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.org.id = :orgId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orgId", orgId).getResultList();
	}
	/**
	 * Find by reference: billToLocation
	 */
	public List<SalesInvoice> findByBillToLocation(Location billToLocation) {
		return this.findByBillToLocationId(billToLocation.getId());
	}
	/**
	 * Find by ID of reference: billToLocation.id
	 */
	public List<SalesInvoice> findByBillToLocationId(String billToLocationId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.billToLocation.id = :billToLocationId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("billToLocationId", billToLocationId)
				.getResultList();
	}
	/**
	 * Find by reference: billToContact
	 */
	public List<SalesInvoice> findByBillToContact(BpContact billToContact) {
		return this.findByBillToContactId(billToContact.getId());
	}
	/**
	 * Find by ID of reference: billToContact.id
	 */
	public List<SalesInvoice> findByBillToContactId(String billToContactId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.billToContact.id = :billToContactId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("billToContactId", billToContactId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentMethod
	 */
	public List<SalesInvoice> findByPaymentMethod(DocType paymentMethod) {
		return this.findByPaymentMethodId(paymentMethod.getId());
	}
	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<SalesInvoice> findByPaymentMethodId(String paymentMethodId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.paymentMethod.id = :paymentMethodId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentMethodId", paymentMethodId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentTerm
	 */
	public List<SalesInvoice> findByPaymentTerm(PaymentTerm paymentTerm) {
		return this.findByPaymentTermId(paymentTerm.getId());
	}
	/**
	 * Find by ID of reference: paymentTerm.id
	 */
	public List<SalesInvoice> findByPaymentTermId(String paymentTermId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoice e where e.clientId = :clientId and e.paymentTerm.id = :paymentTermId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentTermId", paymentTermId).getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<SalesInvoice> findByLines(SalesInvoiceLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<SalesInvoice> findByLinesId(String linesId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesInvoice e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
	/**
	 * Find by reference: taxes
	 */
	public List<SalesInvoice> findByTaxes(SalesInvoiceTax taxes) {
		return this.findByTaxesId(taxes.getId());
	}
	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<SalesInvoice> findByTaxesId(String taxesId) {
		return (List<SalesInvoice>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesInvoice e, IN (e.taxes) c where e.clientId = :clientId and c.id = :taxesId",
						SalesInvoice.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxesId", taxesId).getResultList();
	}
}
