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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.business.api.inventory.IInventoryOperationService;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryOperation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;

/**
 * Repository functionality for {@link InventoryOperation} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class InventoryOperation_Service
		extends
			AbstractEntityService<InventoryOperation>
		implements
			IInventoryOperationService {

	public InventoryOperation_Service() {
		super();
	}

	public InventoryOperation_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<InventoryOperation> getEntityClass() {
		return InventoryOperation.class;
	}
	/**
	 * Find by reference: inventory
	 */
	public List<InventoryOperation> findByInventory(Inventory inventory) {
		return this.findByInventoryId(inventory.getId());
	}
	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<InventoryOperation> findByInventoryId(String inventoryId) {
		return (List<InventoryOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryOperation e where e.clientId = :clientId and e.inventory.id = :inventoryId",
						InventoryOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("inventoryId", inventoryId).getResultList();
	}
	/**
	 * Find by reference: location
	 */
	public List<InventoryOperation> findByLocation(InventoryLocation location) {
		return this.findByLocationId(location.getId());
	}
	/**
	 * Find by ID of reference: location.id
	 */
	public List<InventoryOperation> findByLocationId(String locationId) {
		return (List<InventoryOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryOperation e where e.clientId = :clientId and e.location.id = :locationId",
						InventoryOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("locationId", locationId).getResultList();
	}
	/**
	 * Find by reference: product
	 */
	public List<InventoryOperation> findByProduct(Product product) {
		return this.findByProductId(product.getId());
	}
	/**
	 * Find by ID of reference: product.id
	 */
	public List<InventoryOperation> findByProductId(String productId) {
		return (List<InventoryOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryOperation e where e.clientId = :clientId and e.product.id = :productId",
						InventoryOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productId", productId).getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<InventoryOperation> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<InventoryOperation> findByUomId(String uomId) {
		return (List<InventoryOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryOperation e where e.clientId = :clientId and e.uom.id = :uomId",
						InventoryOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
	/**
	 * Find by reference: transactionLine
	 */
	public List<InventoryOperation> findByTransactionLine(
			InventoryTransactionLine transactionLine) {
		return this.findByTransactionLineId(transactionLine.getId());
	}
	/**
	 * Find by ID of reference: transactionLine.id
	 */
	public List<InventoryOperation> findByTransactionLineId(
			String transactionLineId) {
		return (List<InventoryOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryOperation e where e.clientId = :clientId and e.transactionLine.id = :transactionLineId",
						InventoryOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("transactionLineId", transactionLineId)
				.getResultList();
	}
}
