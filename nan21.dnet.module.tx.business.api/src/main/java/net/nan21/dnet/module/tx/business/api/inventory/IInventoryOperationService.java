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
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryOperation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;

/**
 * Interface to expose business functions specific for {@link InventoryOperation} domain
 * entity.
 */
public interface IInventoryOperationService
		extends
			IEntityService<InventoryOperation> {

	/**
	 * Find by reference: inventory
	 */
	public List<InventoryOperation> findByInventory(Inventory inventory);

	/**
	 * Find by ID of reference: inventory.id
	 */
	public List<InventoryOperation> findByInventoryId(String inventoryId);

	/**
	 * Find by reference: location
	 */
	public List<InventoryOperation> findByLocation(InventoryLocation location);

	/**
	 * Find by ID of reference: location.id
	 */
	public List<InventoryOperation> findByLocationId(String locationId);

	/**
	 * Find by reference: product
	 */
	public List<InventoryOperation> findByProduct(Product product);

	/**
	 * Find by ID of reference: product.id
	 */
	public List<InventoryOperation> findByProductId(String productId);

	/**
	 * Find by reference: uom
	 */
	public List<InventoryOperation> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<InventoryOperation> findByUomId(String uomId);

	/**
	 * Find by reference: transactionLine
	 */
	public List<InventoryOperation> findByTransactionLine(
			InventoryTransactionLine transactionLine);

	/**
	 * Find by ID of reference: transactionLine.id
	 */
	public List<InventoryOperation> findByTransactionLineId(
			String transactionLineId);
}
