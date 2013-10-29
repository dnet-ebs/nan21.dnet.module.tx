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
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderTaxService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderTax;

/**
 * Repository functionality for {@link SalesOrderTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesOrderTax_Service extends AbstractEntityService<SalesOrderTax>
		implements
			ISalesOrderTaxService {

	public SalesOrderTax_Service() {
		super();
	}

	public SalesOrderTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesOrderTax> getEntityClass() {
		return SalesOrderTax.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesOrderTax findByTax(SalesOrder order, Tax tax) {
		return (SalesOrderTax) this
				.getEntityManager()
				.createNamedQuery(SalesOrderTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("order", order).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public SalesOrderTax findByTax(Long orderId, Long taxId) {
		return (SalesOrderTax) this
				.getEntityManager()
				.createNamedQuery(SalesOrderTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: order
	 */
	public List<SalesOrderTax> findByOrder(SalesOrder order) {
		return this.findByOrderId(order.getId());
	}
	/**
	 * Find by ID of reference: order.id
	 */
	public List<SalesOrderTax> findByOrderId(String orderId) {
		return (List<SalesOrderTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderTax e where e.clientId = :clientId and e.order.id = :orderId",
						SalesOrderTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderTax> findByTaxId(String taxId) {
		return (List<SalesOrderTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderTax e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesOrderTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
