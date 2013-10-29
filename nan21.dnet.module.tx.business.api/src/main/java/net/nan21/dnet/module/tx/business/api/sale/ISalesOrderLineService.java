/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;

/**
 * Interface to expose business functions specific for {@link SalesOrderLine} domain
 * entity.
 */
public interface ISalesOrderLineService extends IEntityService<SalesOrderLine> {

	/**
	 * Find by reference: order
	 */
	public List<SalesOrderLine> findByOrder(SalesOrder order);

	/**
	 * Find by ID of reference: order.id
	 */
	public List<SalesOrderLine> findByOrderId(String orderId);

	/**
	 * Find by reference: productAccount
	 */
	public List<SalesOrderLine> findByProductAccount(
			ProductAccount productAccount);

	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<SalesOrderLine> findByProductAccountId(String productAccountId);

	/**
	 * Find by reference: uom
	 */
	public List<SalesOrderLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<SalesOrderLine> findByUomId(String uomId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderLine> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderLine> findByTaxId(String taxId);

	/**
	 * Find by reference: lineTaxes
	 */
	public List<SalesOrderLine> findByLineTaxes(SalesOrderLineTax lineTaxes);

	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<SalesOrderLine> findByLineTaxesId(String lineTaxesId);
}
