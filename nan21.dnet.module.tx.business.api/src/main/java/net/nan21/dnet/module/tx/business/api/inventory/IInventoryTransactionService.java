/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.inventory;

import java.util.List;
import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;

/**
 * Interface to expose business functions specific for {@link InventoryTransaction} domain
 * entity.
 */
public interface IInventoryTransactionService
		extends
			IEntityService<InventoryTransaction> {

	public void doConfirm(String transactionId) throws BusinessException;

	public void doUnConfirm(String transactionId) throws BusinessException;

	/**
	 * Find by reference: docType
	 */
	public List<InventoryTransaction> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<InventoryTransaction> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: fromInventory
	 */
	public List<InventoryTransaction> findByFromInventory(Org fromInventory);

	/**
	 * Find by ID of reference: fromInventory.id
	 */
	public List<InventoryTransaction> findByFromInventoryId(
			String fromInventoryId);

	/**
	 * Find by reference: toInventory
	 */
	public List<InventoryTransaction> findByToInventory(Org toInventory);

	/**
	 * Find by ID of reference: toInventory.id
	 */
	public List<InventoryTransaction> findByToInventoryId(String toInventoryId);

	/**
	 * Find by reference: salesOrder
	 */
	public List<InventoryTransaction> findBySalesOrder(SalesOrder salesOrder);

	/**
	 * Find by ID of reference: salesOrder.id
	 */
	public List<InventoryTransaction> findBySalesOrderId(String salesOrderId);

	/**
	 * Find by reference: salesInvoice
	 */
	public List<InventoryTransaction> findBySalesInvoice(
			SalesInvoice salesInvoice);

	/**
	 * Find by ID of reference: salesInvoice.id
	 */
	public List<InventoryTransaction> findBySalesInvoiceId(String salesInvoiceId);

	/**
	 * Find by reference: purchaseOrder
	 */
	public List<InventoryTransaction> findByPurchaseOrder(
			PurchaseOrder purchaseOrder);

	/**
	 * Find by ID of reference: purchaseOrder.id
	 */
	public List<InventoryTransaction> findByPurchaseOrderId(
			String purchaseOrderId);

	/**
	 * Find by reference: purchaseInvoice
	 */
	public List<InventoryTransaction> findByPurchaseInvoice(
			PurchaseInvoice purchaseInvoice);

	/**
	 * Find by ID of reference: purchaseInvoice.id
	 */
	public List<InventoryTransaction> findByPurchaseInvoiceId(
			String purchaseInvoiceId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<InventoryTransaction> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<InventoryTransaction> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: deliveryLocation
	 */
	public List<InventoryTransaction> findByDeliveryLocation(
			Location deliveryLocation);

	/**
	 * Find by ID of reference: deliveryLocation.id
	 */
	public List<InventoryTransaction> findByDeliveryLocationId(
			String deliveryLocationId);

	/**
	 * Find by reference: deliveryContact
	 */
	public List<InventoryTransaction> findByDeliveryContact(
			BpContact deliveryContact);

	/**
	 * Find by ID of reference: deliveryContact.id
	 */
	public List<InventoryTransaction> findByDeliveryContactId(
			String deliveryContactId);

	/**
	 * Find by reference: lines
	 */
	public List<InventoryTransaction> findByLines(InventoryTransactionLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<InventoryTransaction> findByLinesId(String linesId);
}
