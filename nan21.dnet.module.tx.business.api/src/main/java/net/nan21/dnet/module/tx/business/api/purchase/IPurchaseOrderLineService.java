/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

/**
 * Interface to expose business functions specific for {@link PurchaseOrderLine} domain
 * entity.
 */
public interface IPurchaseOrderLineService
		extends
			IEntityService<PurchaseOrderLine> {

	/**
	 * Find by reference: order
	 */
	public List<PurchaseOrderLine> findByOrder(PurchaseOrder order);

	/**
	 * Find by ID of reference: order.id
	 */
	public List<PurchaseOrderLine> findByOrderId(String orderId);

	/**
	 * Find by reference: productAccount
	 */
	public List<PurchaseOrderLine> findByProductAccount(
			ProductAccount productAccount);

	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PurchaseOrderLine> findByProductAccountId(
			String productAccountId);

	/**
	 * Find by reference: uom
	 */
	public List<PurchaseOrderLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PurchaseOrderLine> findByUomId(String uomId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderLine> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderLine> findByTaxId(String taxId);

	/**
	 * Find by reference: lineTaxes
	 */
	public List<PurchaseOrderLine> findByLineTaxes(
			PurchaseOrderLineTax lineTaxes);

	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<PurchaseOrderLine> findByLineTaxesId(String lineTaxesId);
}
