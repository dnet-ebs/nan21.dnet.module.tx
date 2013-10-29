/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.inventory;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistory;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistoryComponent;

/**
 * Interface to expose business functions specific for {@link StockHistoryComponent} domain
 * entity.
 */
public interface IStockHistoryComponentService
		extends
			IEntityService<StockHistoryComponent> {

	/**
	 * Find by reference: stockHistory
	 */
	public List<StockHistoryComponent> findByStockHistory(
			StockHistory stockHistory);

	/**
	 * Find by ID of reference: stockHistory.id
	 */
	public List<StockHistoryComponent> findByStockHistoryId(
			String stockHistoryId);
}
