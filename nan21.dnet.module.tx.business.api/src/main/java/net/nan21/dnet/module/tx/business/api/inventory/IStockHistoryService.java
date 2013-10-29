/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.inventory;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistory;

/**
 * Interface to expose business functions specific for {@link StockHistory} domain
 * entity.
 */
public interface IStockHistoryService extends IEntityService<StockHistory> {

	/**
	 * Find by reference: inventory
	 */
	public List<StockHistory> findByInventory(Inventory inventory);

	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<StockHistory> findByInventoryId(String inventoryId);

	/**
	 * Find by reference: location
	 */
	public List<StockHistory> findByLocation(InventoryLocation location);

	/**
	 * Find by ID of reference: location.id
	 */
	public List<StockHistory> findByLocationId(String locationId);

	/**
	 * Find by reference: product
	 */
	public List<StockHistory> findByProduct(Product product);

	/**
	 * Find by ID of reference: product.id
	 */
	public List<StockHistory> findByProductId(String productId);

	/**
	 * Find by reference: uom
	 */
	public List<StockHistory> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<StockHistory> findByUomId(String uomId);
}
