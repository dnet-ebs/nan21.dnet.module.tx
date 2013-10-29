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
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;

/**
 * Interface to expose business functions specific for {@link Stock} domain
 * entity.
 */
public interface IStockService extends IEntityService<Stock> {

	/**
	 * Find by reference: inventory
	 */
	public List<Stock> findByInventory(Inventory inventory);

	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<Stock> findByInventoryId(String inventoryId);

	/**
	 * Find by reference: location
	 */
	public List<Stock> findByLocation(InventoryLocation location);

	/**
	 * Find by ID of reference: location.id
	 */
	public List<Stock> findByLocationId(String locationId);

	/**
	 * Find by reference: product
	 */
	public List<Stock> findByProduct(Product product);

	/**
	 * Find by ID of reference: product.id
	 */
	public List<Stock> findByProductId(String productId);

	/**
	 * Find by reference: uom
	 */
	public List<Stock> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<Stock> findByUomId(String uomId);
}
