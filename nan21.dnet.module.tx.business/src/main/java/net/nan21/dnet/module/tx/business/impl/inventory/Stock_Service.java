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
import net.nan21.dnet.module.tx.business.api.inventory.IStockService;
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;

/**
 * Repository functionality for {@link Stock} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class Stock_Service extends AbstractEntityService<Stock>
		implements
			IStockService {

	public Stock_Service() {
		super();
	}

	public Stock_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<Stock> getEntityClass() {
		return Stock.class;
	}
	/**
	 * Find by reference: inventory
	 */
	public List<Stock> findByInventory(Inventory inventory) {
		return this.findByInventoryId(inventory.getId());
	}
	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<Stock> findByInventoryId(String inventoryId) {
		return (List<Stock>) this
				.getEntityManager()
				.createQuery(
						"select e from Stock e where e.clientId = :clientId and e.inventory.id = :inventoryId",
						Stock.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("inventoryId", inventoryId).getResultList();
	}
	/**
	 * Find by reference: location
	 */
	public List<Stock> findByLocation(InventoryLocation location) {
		return this.findByLocationId(location.getId());
	}
	/**
	 * Find by ID of reference: location.id
	 */
	public List<Stock> findByLocationId(String locationId) {
		return (List<Stock>) this
				.getEntityManager()
				.createQuery(
						"select e from Stock e where e.clientId = :clientId and e.location.id = :locationId",
						Stock.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("locationId", locationId).getResultList();
	}
	/**
	 * Find by reference: product
	 */
	public List<Stock> findByProduct(Product product) {
		return this.findByProductId(product.getId());
	}
	/**
	 * Find by ID of reference: product.id
	 */
	public List<Stock> findByProductId(String productId) {
		return (List<Stock>) this
				.getEntityManager()
				.createQuery(
						"select e from Stock e where e.clientId = :clientId and e.product.id = :productId",
						Stock.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productId", productId).getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<Stock> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<Stock> findByUomId(String uomId) {
		return (List<Stock>) this
				.getEntityManager()
				.createQuery(
						"select e from Stock e where e.clientId = :clientId and e.uom.id = :uomId",
						Stock.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
}
