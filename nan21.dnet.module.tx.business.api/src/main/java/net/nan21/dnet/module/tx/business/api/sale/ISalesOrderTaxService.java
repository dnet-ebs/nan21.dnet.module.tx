/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderTax;

/**
 * Interface to expose business functions specific for {@link SalesOrderTax} domain
 * entity.
 */
public interface ISalesOrderTaxService extends IEntityService<SalesOrderTax> {

	/**
	 * Find by unique key
	 */
	public SalesOrderTax findByTax(SalesOrder order, Tax tax);

	/**
	 * Find by unique key
	 */
	public SalesOrderTax findByTax(Long orderId, Long taxId);

	/**
	 * Find by reference: order
	 */
	public List<SalesOrderTax> findByOrder(SalesOrder order);

	/**
	 * Find by ID of reference: order.id
	 */
	public List<SalesOrderTax> findByOrderId(String orderId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderTax> findByTaxId(String taxId);
}
