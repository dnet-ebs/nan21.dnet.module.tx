/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.inventory;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;

/**
 * Interface to expose business functions specific for {@link InventoryTransactionLine} domain
 * entity.
 */
public interface IInventoryTransactionLineService
		extends
			IEntityService<InventoryTransactionLine> {

	/**
	 * Find by reference: inventoryTransaction
	 */
	public List<InventoryTransactionLine> findByInventoryTransaction(
			InventoryTransaction inventoryTransaction);

	/**
	 * Find by ID of reference: inventoryTransaction.id
	 */
	public List<InventoryTransactionLine> findByInventoryTransactionId(
			String inventoryTransactionId);

	/**
	 * Find by reference: product
	 */
	public List<InventoryTransactionLine> findByProduct(Product product);

	/**
	 * Find by ID of reference: product.id
	 */
	public List<InventoryTransactionLine> findByProductId(String productId);

	/**
	 * Find by reference: fromInventory
	 */
	public List<InventoryTransactionLine> findByFromInventory(
			Inventory fromInventory);

	/**
	 * Find by ID of reference: fromInventory.id
	 */
	public List<InventoryTransactionLine> findByFromInventoryId(
			String fromInventoryId);

	/**
	 * Find by reference: fromLocation
	 */
	public List<InventoryTransactionLine> findByFromLocation(
			InventoryLocation fromLocation);

	/**
	 * Find by ID of reference: fromLocation.id
	 */
	public List<InventoryTransactionLine> findByFromLocationId(
			String fromLocationId);

	/**
	 * Find by reference: toInventory
	 */
	public List<InventoryTransactionLine> findByToInventory(
			Inventory toInventory);

	/**
	 * Find by ID of reference: toInventory.id
	 */
	public List<InventoryTransactionLine> findByToInventoryId(
			String toInventoryId);

	/**
	 * Find by reference: toLocation
	 */
	public List<InventoryTransactionLine> findByToLocation(
			InventoryLocation toLocation);

	/**
	 * Find by ID of reference: toLocation.id
	 */
	public List<InventoryTransactionLine> findByToLocationId(String toLocationId);

	/**
	 * Find by reference: uom
	 */
	public List<InventoryTransactionLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<InventoryTransactionLine> findByUomId(String uomId);

	/**
	 * Find by reference: currency
	 */
	public List<InventoryTransactionLine> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<InventoryTransactionLine> findByCurrencyId(String currencyId);
}
