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
import net.nan21.dnet.module.tx.business.api.inventory.IStockHistoryComponentService;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistory;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockHistoryComponent;

/**
 * Repository functionality for {@link StockHistoryComponent} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class StockHistoryComponent_Service
		extends
			AbstractEntityService<StockHistoryComponent>
		implements
			IStockHistoryComponentService {

	public StockHistoryComponent_Service() {
		super();
	}

	public StockHistoryComponent_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<StockHistoryComponent> getEntityClass() {
		return StockHistoryComponent.class;
	}
	/**
	 * Find by reference: stockHistory
	 */
	public List<StockHistoryComponent> findByStockHistory(
			StockHistory stockHistory) {
		return this.findByStockHistoryId(stockHistory.getId());
	}
	/**
	 * Find by ID of reference: stockHistory.id
	 */
	public List<StockHistoryComponent> findByStockHistoryId(
			String stockHistoryId) {
		return (List<StockHistoryComponent>) this
				.getEntityManager()
				.createQuery(
						"select e from StockHistoryComponent e where e.clientId = :clientId and e.stockHistory.id = :stockHistoryId",
						StockHistoryComponent.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("stockHistoryId", stockHistoryId).getResultList();
	}
}
