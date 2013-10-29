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
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderTax;

/**
 * Repository functionality for {@link SalesOrder} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesOrder_Service extends AbstractEntityService<SalesOrder> {

	public SalesOrder_Service() {
		super();
	}

	public SalesOrder_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesOrder> getEntityClass() {
		return SalesOrder.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesOrder findByDocno(String docNo) {
		return (SalesOrder) this
				.getEntityManager()
				.createNamedQuery(SalesOrder.NQ_FIND_BY_DOCNO)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docNo", docNo).getSingleResult();
	}
	/**
	 * Find by reference: docType
	 */
	public List<SalesOrder> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<SalesOrder> findByDocTypeId(String docTypeId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.docType.id = :docTypeId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: company
	 */
	public List<SalesOrder> findByCompany(Org company) {
		return this.findByCompanyId(company.getId());
	}
	/**
	 * Find by ID of reference: company.id
	 */
	public List<SalesOrder> findByCompanyId(String companyId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.company.id = :companyId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("companyId", companyId).getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<SalesOrder> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<SalesOrder> findByBpAccountId(String bpAccountId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<SalesOrder> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<SalesOrder> findByCurrencyId(String currencyId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.currency.id = :currencyId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
	/**
	 * Find by reference: org
	 */
	public List<SalesOrder> findByOrg(Org org) {
		return this.findByOrgId(org.getId());
	}
	/**
	 * Find by ID of reference: org.id
	 */
	public List<SalesOrder> findByOrgId(String orgId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.org.id = :orgId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orgId", orgId).getResultList();
	}
	/**
	 * Find by reference: billToLocation
	 */
	public List<SalesOrder> findByBillToLocation(Location billToLocation) {
		return this.findByBillToLocationId(billToLocation.getId());
	}
	/**
	 * Find by ID of reference: billToLocation.id
	 */
	public List<SalesOrder> findByBillToLocationId(String billToLocationId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.billToLocation.id = :billToLocationId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("billToLocationId", billToLocationId)
				.getResultList();
	}
	/**
	 * Find by reference: billToContact
	 */
	public List<SalesOrder> findByBillToContact(BpContact billToContact) {
		return this.findByBillToContactId(billToContact.getId());
	}
	/**
	 * Find by ID of reference: billToContact.id
	 */
	public List<SalesOrder> findByBillToContactId(String billToContactId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.billToContact.id = :billToContactId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("billToContactId", billToContactId)
				.getResultList();
	}
	/**
	 * Find by reference: shipToLocation
	 */
	public List<SalesOrder> findByShipToLocation(Location shipToLocation) {
		return this.findByShipToLocationId(shipToLocation.getId());
	}
	/**
	 * Find by ID of reference: shipToLocation.id
	 */
	public List<SalesOrder> findByShipToLocationId(String shipToLocationId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.shipToLocation.id = :shipToLocationId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("shipToLocationId", shipToLocationId)
				.getResultList();
	}
	/**
	 * Find by reference: shipToContact
	 */
	public List<SalesOrder> findByShipToContact(BpContact shipToContact) {
		return this.findByShipToContactId(shipToContact.getId());
	}
	/**
	 * Find by ID of reference: shipToContact.id
	 */
	public List<SalesOrder> findByShipToContactId(String shipToContactId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.shipToContact.id = :shipToContactId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("shipToContactId", shipToContactId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentMethod
	 */
	public List<SalesOrder> findByPaymentMethod(DocType paymentMethod) {
		return this.findByPaymentMethodId(paymentMethod.getId());
	}
	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<SalesOrder> findByPaymentMethodId(String paymentMethodId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.paymentMethod.id = :paymentMethodId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentMethodId", paymentMethodId)
				.getResultList();
	}
	/**
	 * Find by reference: paymentTerm
	 */
	public List<SalesOrder> findByPaymentTerm(PaymentTerm paymentTerm) {
		return this.findByPaymentTermId(paymentTerm.getId());
	}
	/**
	 * Find by ID of reference: paymentTerm.id
	 */
	public List<SalesOrder> findByPaymentTermId(String paymentTermId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrder e where e.clientId = :clientId and e.paymentTerm.id = :paymentTermId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentTermId", paymentTermId).getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<SalesOrder> findByLines(SalesOrderLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<SalesOrder> findByLinesId(String linesId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesOrder e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
	/**
	 * Find by reference: taxes
	 */
	public List<SalesOrder> findByTaxes(SalesOrderTax taxes) {
		return this.findByTaxesId(taxes.getId());
	}
	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<SalesOrder> findByTaxesId(String taxesId) {
		return (List<SalesOrder>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesOrder e, IN (e.taxes) c where e.clientId = :clientId and c.id = :taxesId",
						SalesOrder.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxesId", taxesId).getResultList();
	}
}
