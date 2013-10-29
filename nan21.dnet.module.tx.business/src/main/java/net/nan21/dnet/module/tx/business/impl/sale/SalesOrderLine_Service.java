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
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;

/**
 * Repository functionality for {@link SalesOrderLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesOrderLine_Service
		extends
			AbstractEntityService<SalesOrderLine> {

	public SalesOrderLine_Service() {
		super();
	}

	public SalesOrderLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesOrderLine> getEntityClass() {
		return SalesOrderLine.class;
	}
	/**
	 * Find by reference: order
	 */
	public List<SalesOrderLine> findByOrder(SalesOrder order) {
		return this.findByOrderId(order.getId());
	}
	/**
	 * Find by ID of reference: order.id
	 */
	public List<SalesOrderLine> findByOrderId(String orderId) {
		return (List<SalesOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLine e where e.clientId = :clientId and e.order.id = :orderId",
						SalesOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("orderId", orderId).getResultList();
	}
	/**
	 * Find by reference: productAccount
	 */
	public List<SalesOrderLine> findByProductAccount(
			ProductAccount productAccount) {
		return this.findByProductAccountId(productAccount.getId());
	}
	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<SalesOrderLine> findByProductAccountId(String productAccountId) {
		return (List<SalesOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLine e where e.clientId = :clientId and e.productAccount.id = :productAccountId",
						SalesOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productAccountId", productAccountId)
				.getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<SalesOrderLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<SalesOrderLine> findByUomId(String uomId) {
		return (List<SalesOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLine e where e.clientId = :clientId and e.uom.id = :uomId",
						SalesOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderLine> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderLine> findByTaxId(String taxId) {
		return (List<SalesOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLine e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
	/**
	 * Find by reference: lineTaxes
	 */
	public List<SalesOrderLine> findByLineTaxes(SalesOrderLineTax lineTaxes) {
		return this.findByLineTaxesId(lineTaxes.getId());
	}
	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<SalesOrderLine> findByLineTaxesId(String lineTaxesId) {
		return (List<SalesOrderLine>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from SalesOrderLine e, IN (e.lineTaxes) c where e.clientId = :clientId and c.id = :lineTaxesId",
						SalesOrderLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineTaxesId", lineTaxesId).getResultList();
	}
}
