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
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderTaxService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderTax;

/**
 * Repository functionality for {@link PurchaseOrderTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseOrderTax_Service
		extends
			AbstractEntityService<PurchaseOrderTax>
		implements
			IPurchaseOrderTaxService {

	public PurchaseOrderTax_Service() {
		super();
	}

	public PurchaseOrderTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseOrderTax> getEntityClass() {
		return PurchaseOrderTax.class;
	}
	/**
	 * Find by unique key
	 */
	public PurchaseOrderTax findByTax(PurchaseOrder order, Tax tax) {
		return (PurchaseOrderTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseOrderTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("order", order).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public PurchaseOrderTax findByTax(Long orderId, Long taxId) {
		return (PurchaseOrderTax) this
				.getEntityManager()
				.createNamedQuery(PurchaseOrderTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: order
	 */
	public List<PurchaseOrderTax> findByOrder(PurchaseOrder order) {
		return this.findByOrderId(order.getId());
	}
	/**
	 * Find by ID of reference: order.id
	 */
	public List<PurchaseOrderTax> findByOrderId(String orderId) {
		return (List<PurchaseOrderTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderTax e where e.clientId = :clientId and e.order.id = :orderId",
						PurchaseOrderTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderTax> findByTaxId(String taxId) {
		return (List<PurchaseOrderTax>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderTax e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseOrderTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
