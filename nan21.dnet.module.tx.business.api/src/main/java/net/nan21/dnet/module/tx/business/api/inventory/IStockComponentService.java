/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.inventory;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockComponent;

/**
 * Interface to expose business functions specific for {@link StockComponent} domain
 * entity.
 */
public interface IStockComponentService extends IEntityService<StockComponent> {

	/**
	 * Find by reference: stock
	 */
	public List<StockComponent> findByStock(Stock stock);

	/**
	 * Find by ID of reference: stock.id
	 */
	public List<StockComponent> findByStockId(String stockId);
}
