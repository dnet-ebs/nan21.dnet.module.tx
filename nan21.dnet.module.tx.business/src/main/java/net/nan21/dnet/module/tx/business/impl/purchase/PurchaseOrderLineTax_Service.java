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
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderLineTaxService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

/**
 * Repository functionality for {@link PurchaseOrderLineTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseOrderLineTax_Service
		extends
			AbstractEntityService<PurchaseOrderLineTax>
		implements
			IPurchaseOrderLineTaxService {

	public PurchaseOrderLineTax_Service() {
		super();
	}

	public PurchaseOrderLineTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseOrderLineTax> getEntityClass() {
		return PurchaseOrderLineTax.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseOrderLineTax findByTax(PurchaseOrderLine line, Tax tax) {
		return (PurchaseOrderLineTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseOrderLineTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("line", line).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public PurchaseOrderLineTax findByTax(Long lineId, Long taxId) {
		return (PurchaseOrderLineTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseOrderLineTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: line
	 */
	public List<PurchaseOrderLineTax> findByLine(PurchaseOrderLine line) {
		return this.findByLineId(line.getId());
	}
	/**
	 * Find by ID of reference: line.id
	 */
	public List<PurchaseOrderLineTax> findByLineId(String lineId) {
		return (List<PurchaseOrderLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLineTax e where e.clientId = :clientId and e.line.id = :lineId",
						PurchaseOrderLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderLineTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderLineTax> findByTaxId(String taxId) {
		return (List<PurchaseOrderLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLineTax e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseOrderLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
