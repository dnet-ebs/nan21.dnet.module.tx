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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;

/**
 * Repository functionality for {@link SalesInvoiceLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesInvoiceLine_Service
		extends
			AbstractEntityService<SalesInvoiceLine> {

	public SalesInvoiceLine_Service() {
		super();
	}

	public SalesInvoiceLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesInvoiceLine> getEntityClass() {
		return SalesInvoiceLine.class;
	}
	/**
	 * Find by reference: invoice
	 */
	public List<SalesInvoiceLine> findByInvoice(SalesInvoice invoice) {
		return this.findByInvoiceId(invoice.getId());
	}
	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<SalesInvoiceLine> findByInvoiceId(String invoiceId) {
		return (List<SalesInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLine e where e.clientId = :clientId and e.invoice.id = :invoiceId",
						SalesInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId).getResultList();
	}
	/**
	 * Find by reference: productAccount
	 */
	public List<SalesInvoiceLine> findByProductAccount(
			ProductAccount productAccount) {
		return this.findByProductAccountId(productAccount.getId());
	}
	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<SalesInvoiceLine> findByProductAccountId(String productAccountId) {
		return (List<SalesInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLine e where e.clientId = :clientId and e.productAccount.id = :productAccountId",
						SalesInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productAccountId", productAccountId)
				.getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<SalesInvoiceLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<SalesInvoiceLine> findByUomId(String uomId) {
		return (List<SalesInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLine e where e.clientId = :clientId and e.uom.id = :uomId",
						SalesInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceLine> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceLine> findByTaxId(String taxId) {
		return (List<SalesInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLine e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
	/**
	 * Find by reference: lineTaxes
	 */
	public List<SalesInvoiceLine> findByLineTaxes(SalesInvoiceLineTax lineTaxes) {
		return this.findByLineTaxesId(lineTaxes.getId());
	}
	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<SalesInvoiceLine> findByLineTaxesId(String lineTaxesId) {
		return (List<SalesInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesInvoiceLine e, IN (e.lineTaxes) c where e.clientId = :clientId and c.id = :lineTaxesId",
						SalesInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineTaxesId", lineTaxesId).getResultList();
	}
}
