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
import net.nan21.dnet.module.tx.business.api.inventory.IStockHistoryService;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistory;

/**
 * Repository functionality for {@link StockHistory} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class StockHistory_Service extends AbstractEntityService<StockHistory>
		implements
			IStockHistoryService {

	public StockHistory_Service() {
		super();
	}

	public StockHistory_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<StockHistory> getEntityClass() {
		return StockHistory.class;
	}
	/**
	 * Find by reference: inventory
	 */
	public List<StockHistory> findByInventory(Inventory inventory) {
		return this.findByInventoryId(inventory.getId());
	}
	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<StockHistory> findByInventoryId(String inventoryId) {
		return (List<StockHistory>) this
				.getEntityManager()
				.createQuery(
						"select e from StockHistory e where e.clientId = :clientId and e.inventory.id = :inventoryId",
						StockHistory.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("inventoryId", inventoryId).getResultList();
	}
	/**
	 * Find by reference: location
	 */
	public List<StockHistory> findByLocation(InventoryLocation location) {
		return this.findByLocationId(location.getId());
	}
	/**
	 * Find by ID of reference: location.id
	 */
	public List<StockHistory> findByLocationId(String locationId) {
		return (List<StockHistory>) this
				.getEntityManager()
				.createQuery(
						"select e from StockHistory e where e.clientId = :clientId and e.location.id = :locationId",
						StockHistory.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("locationId", locationId).getResultList();
	}
	/**
	 * Find by reference: product
	 */
	public List<StockHistory> findByProduct(Product product) {
		return this.findByProductId(product.getId());
	}
	/**
	 * Find by ID of reference: product.id
	 */
	public List<StockHistory> findByProductId(String productId) {
		return (List<StockHistory>) this
				.getEntityManager()
				.createQuery(
						"select e from StockHistory e where e.clientId = :clientId and e.product.id = :productId",
						StockHistory.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productId", productId).getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<StockHistory> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<StockHistory> findByUomId(String uomId) {
		return (List<StockHistory>) this
				.getEntityManager()
				.createQuery(
						"select e from StockHistory e where e.clientId = :clientId and e.uom.id = :uomId",
						StockHistory.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
}
