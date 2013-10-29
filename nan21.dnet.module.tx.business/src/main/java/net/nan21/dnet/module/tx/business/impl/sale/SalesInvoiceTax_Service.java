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
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceTaxService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;

/**
 * Repository functionality for {@link SalesInvoiceTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesInvoiceTax_Service
		extends
			AbstractEntityService<SalesInvoiceTax>
		implements
			ISalesInvoiceTaxService {

	public SalesInvoiceTax_Service() {
		super();
	}

	public SalesInvoiceTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesInvoiceTax> getEntityClass() {
		return SalesInvoiceTax.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesInvoiceTax findByTax(SalesInvoice invoice, Tax tax) {
		return (SalesInvoiceTax) this
				.getEntityManager()
				.createNamedQuery(SalesInvoiceTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoice", invoice).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public SalesInvoiceTax findByTax(Long invoiceId, Long taxId) {
		return (SalesInvoiceTax) this
				.getEntityManager()
				.createNamedQuery(SalesInvoiceTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId)
				.setParameter("taxId", taxId).getSingleResult();
	}
	/**
	 * Find by reference: invoice
	 */
	public List<SalesInvoiceTax> findByInvoice(SalesInvoice invoice) {
		return this.findByInvoiceId(invoice.getId());
	}
	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<SalesInvoiceTax> findByInvoiceId(String invoiceId) {
		return (List<SalesInvoiceTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceTax e where e.clientId = :clientId and e.invoice.id = :invoiceId",
						SalesInvoiceTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("invoiceId", invoiceId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceTax> findByTaxId(String taxId) {
		return (List<SalesInvoiceTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceTax e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesInvoiceTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
