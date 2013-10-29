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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;

/**
 * Repository functionality for {@link PurchaseInvoiceLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseInvoiceLine_Service
		extends
			AbstractEntityService<PurchaseInvoiceLine> {

	public PurchaseInvoiceLine_Service() {
		super();
	}

	public PurchaseInvoiceLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseInvoiceLine> getEntityClass() {
		return PurchaseInvoiceLine.class;
	}
	/**
	 * Find by reference: invoice
	 */
	public List<PurchaseInvoiceLine> findByInvoice(PurchaseInvoice invoice) {
		return this.findByInvoiceId(invoice.getId());
	}
	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<PurchaseInvoiceLine> findByInvoiceId(String invoiceId) {
		return (List<PurchaseInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLine e where e.clientId = :clientId and e.invoice.id = :invoiceId",
						PurchaseInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId).getResultList();
	}
	/**
	 * Find by reference: productAccount
	 */
	public List<PurchaseInvoiceLine> findByProductAccount(
			ProductAccount productAccount) {
		return this.findByProductAccountId(productAccount.getId());
	}
	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PurchaseInvoiceLine> findByProductAccountId(
			String productAccountId) {
		return (List<PurchaseInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLine e where e.clientId = :clientId and e.productAccount.id = :productAccountId",
						PurchaseInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productAccountId", productAccountId)
				.getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<PurchaseInvoiceLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PurchaseInvoiceLine> findByUomId(String uomId) {
		return (List<PurchaseInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLine e where e.clientId = :clientId and e.uom.id = :uomId",
						PurchaseInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceLine> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceLine> findByTaxId(String taxId) {
		return (List<PurchaseInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLine e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
	/**
	 * Find by reference: lineTaxes
	 */
	public List<PurchaseInvoiceLine> findByLineTaxes(
			PurchaseInvoiceLineTax lineTaxes) {
		return this.findByLineTaxesId(lineTaxes.getId());
	}
	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<PurchaseInvoiceLine> findByLineTaxesId(String lineTaxesId) {
		return (List<PurchaseInvoiceLine>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseInvoiceLine e, IN (e.lineTaxes) c where e.clientId = :clientId and c.id = :lineTaxesId",
						PurchaseInvoiceLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineTaxesId", lineTaxesId).getResultList();
	}
}
