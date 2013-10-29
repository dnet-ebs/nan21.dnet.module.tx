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
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceLineTaxService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;

/**
 * Repository functionality for {@link PurchaseInvoiceLineTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseInvoiceLineTax_Service
		extends
			AbstractEntityService<PurchaseInvoiceLineTax>
		implements
			IPurchaseInvoiceLineTaxService {

	public PurchaseInvoiceLineTax_Service() {
		super();
	}

	public PurchaseInvoiceLineTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseInvoiceLineTax> getEntityClass() {
		return PurchaseInvoiceLineTax.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceLineTax findByTax(PurchaseInvoiceLine line, Tax tax) {
		return (PurchaseInvoiceLineTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseInvoiceLineTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("line", line).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceLineTax findByTax(Long lineId, Long taxId) {
		return (PurchaseInvoiceLineTax) this
				.getEntityManager()
				.createNamedQuery(
						PurchaseInvoiceLineTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: line
	 */
	public List<PurchaseInvoiceLineTax> findByLine(PurchaseInvoiceLine line) {
		return this.findByLineId(line.getId());
	}
	/**
	 * Find by ID of reference: line.id
	 */
	public List<PurchaseInvoiceLineTax> findByLineId(String lineId) {
		return (List<PurchaseInvoiceLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLineTax e where e.clientId = :clientId and e.line.id = :lineId",
						PurchaseInvoiceLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceLineTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceLineTax> findByTaxId(String taxId) {
		return (List<PurchaseInvoiceLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseInvoiceLineTax e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseInvoiceLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
