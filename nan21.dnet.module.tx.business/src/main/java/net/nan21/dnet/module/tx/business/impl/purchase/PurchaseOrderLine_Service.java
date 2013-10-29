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
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

/**
 * Repository functionality for {@link PurchaseOrderLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PurchaseOrderLine_Service
		extends
			AbstractEntityService<PurchaseOrderLine> {

	public PurchaseOrderLine_Service() {
		super();
	}

	public PurchaseOrderLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PurchaseOrderLine> getEntityClass() {
		return PurchaseOrderLine.class;
	}
	/**
	 * Find by reference: order
	 */
	public List<PurchaseOrderLine> findByOrder(PurchaseOrder order) {
		return this.findByOrderId(order.getId());
	}
	/**
	 * Find by ID of reference: order.id
	 */
	public List<PurchaseOrderLine> findByOrderId(String orderId) {
		return (List<PurchaseOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLine e where e.clientId = :clientId and e.order.id = :orderId",
						PurchaseOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).getResultList();
	}
	/**
	 * Find by reference: productAccount
	 */
	public List<PurchaseOrderLine> findByProductAccount(
			ProductAccount productAccount) {
		return this.findByProductAccountId(productAccount.getId());
	}
	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PurchaseOrderLine> findByProductAccountId(
			String productAccountId) {
		return (List<PurchaseOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLine e where e.clientId = :clientId and e.productAccount.id = :productAccountId",
						PurchaseOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productAccountId", productAccountId)
				.getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<PurchaseOrderLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PurchaseOrderLine> findByUomId(String uomId) {
		return (List<PurchaseOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLine e where e.clientId = :clientId and e.uom.id = :uomId",
						PurchaseOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderLine> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderLine> findByTaxId(String taxId) {
		return (List<PurchaseOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PurchaseOrderLine e where e.clientId = :clientId and e.tax.id = :taxId",
						PurchaseOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
	/**
	 * Find by reference: lineTaxes
	 */
	public List<PurchaseOrderLine> findByLineTaxes(
			PurchaseOrderLineTax lineTaxes) {
		return this.findByLineTaxesId(lineTaxes.getId());
	}
	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<PurchaseOrderLine> findByLineTaxesId(String lineTaxesId) {
		return (List<PurchaseOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from PurchaseOrderLine e, IN (e.lineTaxes) c where e.clientId = :clientId and c.id = :lineTaxesId",
						PurchaseOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineTaxesId", lineTaxesId).getResultList();
	}
}
