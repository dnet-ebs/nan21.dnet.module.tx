/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderTax;

/**
 * Interface to expose business functions specific for {@link PurchaseOrderTax} domain
 * entity.
 */
public interface IPurchaseOrderTaxService
		extends
			IEntityService<PurchaseOrderTax> {

	/**
	 * Find by unique key
	 */
	public PurchaseOrderTax findByTax(PurchaseOrder order, Tax tax);

	/**
	 * Find by unique key
	 */
	public PurchaseOrderTax findByTax(Long orderId, Long taxId);

	/**
	 * Find by reference: order
	 */
	public List<PurchaseOrderTax> findByOrder(PurchaseOrder order);

	/**
	 * Find by ID of reference: order.id
	 */
	public List<PurchaseOrderTax> findByOrderId(String orderId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderTax> findByTaxId(String taxId);
}
