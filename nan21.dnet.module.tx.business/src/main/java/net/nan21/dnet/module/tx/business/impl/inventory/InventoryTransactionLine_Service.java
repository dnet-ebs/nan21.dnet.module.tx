/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.inventory;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.business.api.inventory.IInventoryTransactionLineService;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;

/**
 * Repository functionality for {@link InventoryTransactionLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class InventoryTransactionLine_Service
		extends
			AbstractEntityService<InventoryTransactionLine>
		implements
			IInventoryTransactionLineService {

	public InventoryTransactionLine_Service() {
		super();
	}

	public InventoryTransactionLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<InventoryTransactionLine> getEntityClass() {
		return InventoryTransactionLine.class;
	}
	/**
	 * Find by reference: inventoryTransaction
	 */
	public List<InventoryTransactionLine> findByInventoryTransaction(
			InventoryTransaction inventoryTransaction) {
		return this.findByInventoryTransactionId(inventoryTransaction.getId());
	}
	/**
	 * Find by ID of reference: inventoryTransaction.id
	 */
	public List<InventoryTransactionLine> findByInventoryTransactionId(
			String inventoryTransactionId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.inventoryTransaction.id = :inventoryTransactionId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("inventoryTransactionId", inventoryTransactionId)
				.getResultList();
	}
	/**
	 * Find by reference: product
	 */
	public List<InventoryTransactionLine> findByProduct(Product product) {
		return this.findByProductId(product.getId());
	}
	/**
	 * Find by ID of reference: product.id
	 */
	public List<InventoryTransactionLine> findByProductId(String productId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.product.id = :productId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productId", productId).getResultList();
	}
	/**
	 * Find by reference: fromInventory
	 */
	public List<InventoryTransactionLine> findByFromInventory(
			Inventory fromInventory) {
		return this.findByFromInventoryId(fromInventory.getId());
	}
	/**
	 * Find by ID of reference: fromInventory.id
	 */
	public List<InventoryTransactionLine> findByFromInventoryId(
			String fromInventoryId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.fromInventory.id = :fromInventoryId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("fromInventoryId", fromInventoryId)
				.getResultList();
	}
	/**
	 * Find by reference: fromLocation
	 */
	public List<InventoryTransactionLine> findByFromLocation(
			InventoryLocation fromLocation) {
		return this.findByFromLocationId(fromLocation.getId());
	}
	/**
	 * Find by ID of reference: fromLocation.id
	 */
	public List<InventoryTransactionLine> findByFromLocationId(
			String fromLocationId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.fromLocation.id = :fromLocationId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("fromLocationId", fromLocationId).getResultList();
	}
	/**
	 * Find by reference: toInventory
	 */
	public List<InventoryTransactionLine> findByToInventory(
			Inventory toInventory) {
		return this.findByToInventoryId(toInventory.getId());
	}
	/**
	 * Find by ID of reference: toInventory.id
	 */
	public List<InventoryTransactionLine> findByToInventoryId(
			String toInventoryId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.toInventory.id = :toInventoryId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("toInventoryId", toInventoryId).getResultList();
	}
	/**
	 * Find by reference: toLocation
	 */
	public List<InventoryTransactionLine> findByToLocation(
			InventoryLocation toLocation) {
		return this.findByToLocationId(toLocation.getId());
	}
	/**
	 * Find by ID of reference: toLocation.id
	 */
	public List<InventoryTransactionLine> findByToLocationId(String toLocationId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.toLocation.id = :toLocationId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("toLocationId", toLocationId).getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<InventoryTransactionLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<InventoryTransactionLine> findByUomId(String uomId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.uom.id = :uomId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<InventoryTransactionLine> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<InventoryTransactionLine> findByCurrencyId(String currencyId) {
		return (List<InventoryTransactionLine>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransactionLine e where e.clientId = :clientId and e.currency.id = :currencyId",
						InventoryTransactionLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
}
