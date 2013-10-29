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
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceTaxService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceTax;

/**
 * Repository functionality for {@link PurchaseInvoiceTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseInvoiceTax_Service
		extends
			AbstractEntityService<PurchaseInvoiceTax>
		implements
			IPurchaseInvoiceTaxService {

	public PurchaseInvoiceTax_Service() {
		super();
	}

	public PurchaseInvoiceTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseInvoiceTax> getEntityClass() {
		return PurchaseInvoiceTax.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceTax findByTax(PurchaseInvoice invoice, Tax tax) {
		return (PurchaseInvoiceTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseInvoiceTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoice", invoice).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceTax findByTax(Long invoiceId, Long taxId) {
		return (PurchaseInvoiceTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseInvoiceTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId)
				.setParameter("taxId", taxId).getSingleResult();
	}
	/**
	 * Find by reference: invoice
	 */
	public List<PurchaseInvoiceTax> findByInvoice(PurchaseInvoice invoice) {
		return this.findByInvoiceId(invoice.getId());
	}
	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<PurchaseInvoiceTax> findByInvoiceId(String invoiceId) {
		return (List<PurchaseInvoiceTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceTax e where e.clientId = :clientId and e.invoice.id = :invoiceId",
						PurchaseInvoiceTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceTax> findByTaxId(String taxId) {
		return (List<PurchaseInvoiceTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceTax e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseInvoiceTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
