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
import net.nan21.dnet.module.tx.business.api.inventory.IStockComponentService;
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockComponent;

/**
 * Repository functionality for {@link StockComponent} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class StockComponent_Service
		extends
			AbstractEntityService<StockComponent>
		implements
			IStockComponentService {

	public StockComponent_Service() {
		super();
	}

	public StockComponent_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<StockComponent> getEntityClass() {
		return StockComponent.class;
	}
	/**
	 * Find by reference: stock
	 */
	public List<StockComponent> findByStock(Stock stock) {
		return this.findByStockId(stock.getId());
	}
	/**
	 * Find by ID of reference: stock.id
	 */
	public List<StockComponent> findByStockId(String stockId) {
		return (List<StockComponent>) this
				.getEntityManager()
				.createQuery(
						"select e from StockComponent e where e.clientId = :clientId and e.stock.id = :stockId",
						StockComponent.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("stockId", stockId).getResultList();
	}
}
