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
 * Repository functionality for {@link InventoryTransaction} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class InventoryTransaction_Service
		extends
			AbstractEntityService<InventoryTransaction> {

	public InventoryTransaction_Service() {
		super();
	}

	public InventoryTransaction_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<InventoryTransaction> getEntityClass() {
		return InventoryTransaction.class;
	}
	/**
	 * Find by reference: docType
	 */
	public List<InventoryTransaction> findByDocType(DocType docType) {
		return this.findByDocTypeId(docType.getId());
	}
	/**
	 * Find by ID of reference: docType.id
	 */
	public List<InventoryTransaction> findByDocTypeId(String docTypeId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.docType.id = :docTypeId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("docTypeId", docTypeId).getResultList();
	}
	/**
	 * Find by reference: fromInventory
	 */
	public List<InventoryTransaction> findByFromInventory(Org fromInventory) {
		return this.findByFromInventoryId(fromInventory.getId());
	}
	/**
	 * Find by ID of reference: fromInventory.id
	 */
	public List<InventoryTransaction> findByFromInventoryId(
			String fromInventoryId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.fromInventory.id = :fromInventoryId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("fromInventoryId", fromInventoryId)
				.getResultList();
	}
	/**
	 * Find by reference: toInventory
	 */
	public List<InventoryTransaction> findByToInventory(Org toInventory) {
		return this.findByToInventoryId(toInventory.getId());
	}
	/**
	 * Find by ID of reference: toInventory.id
	 */
	public List<InventoryTransaction> findByToInventoryId(String toInventoryId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.toInventory.id = :toInventoryId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("toInventoryId", toInventoryId).getResultList();
	}
	/**
	 * Find by reference: salesOrder
	 */
	public List<InventoryTransaction> findBySalesOrder(SalesOrder salesOrder) {
		return this.findBySalesOrderId(salesOrder.getId());
	}
	/**
	 * Find by ID of reference: salesOrder.id
	 */
	public List<InventoryTransaction> findBySalesOrderId(String salesOrderId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.salesOrder.id = :salesOrderId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("salesOrderId", salesOrderId).getResultList();
	}
	/**
	 * Find by reference: salesInvoice
	 */
	public List<InventoryTransaction> findBySalesInvoice(
			SalesInvoice salesInvoice) {
		return this.findBySalesInvoiceId(salesInvoice.getId());
	}
	/**
	 * Find by ID of reference: salesInvoice.id
	 */
	public List<InventoryTransaction> findBySalesInvoiceId(String salesInvoiceId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.salesInvoice.id = :salesInvoiceId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("salesInvoiceId", salesInvoiceId).getResultList();
	}
	/**
	 * Find by reference: purchaseOrder
	 */
	public List<InventoryTransaction> findByPurchaseOrder(
			PurchaseOrder purchaseOrder) {
		return this.findByPurchaseOrderId(purchaseOrder.getId());
	}
	/**
	 * Find by ID of reference: purchaseOrder.id
	 */
	public List<InventoryTransaction> findByPurchaseOrderId(
			String purchaseOrderId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.purchaseOrder.id = :purchaseOrderId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("purchaseOrderId", purchaseOrderId)
				.getResultList();
	}
	/**
	 * Find by reference: purchaseInvoice
	 */
	public List<InventoryTransaction> findByPurchaseInvoice(
			PurchaseInvoice purchaseInvoice) {
		return this.findByPurchaseInvoiceId(purchaseInvoice.getId());
	}
	/**
	 * Find by ID of reference: purchaseInvoice.id
	 */
	public List<InventoryTransaction> findByPurchaseInvoiceId(
			String purchaseInvoiceId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.purchaseInvoice.id = :purchaseInvoiceId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("purchaseInvoiceId", purchaseInvoiceId)
				.getResultList();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<InventoryTransaction> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<InventoryTransaction> findByBpAccountId(String bpAccountId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: deliveryLocation
	 */
	public List<InventoryTransaction> findByDeliveryLocation(
			Location deliveryLocation) {
		return this.findByDeliveryLocationId(deliveryLocation.getId());
	}
	/**
	 * Find by ID of reference: deliveryLocation.id
	 */
	public List<InventoryTransaction> findByDeliveryLocationId(
			String deliveryLocationId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.deliveryLocation.id = :deliveryLocationId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("deliveryLocationId", deliveryLocationId)
				.getResultList();
	}
	/**
	 * Find by reference: deliveryContact
	 */
	public List<InventoryTransaction> findByDeliveryContact(
			BpContact deliveryContact) {
		return this.findByDeliveryContactId(deliveryContact.getId());
	}
	/**
	 * Find by ID of reference: deliveryContact.id
	 */
	public List<InventoryTransaction> findByDeliveryContactId(
			String deliveryContactId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select e from InventoryTransaction e where e.clientId = :clientId and e.deliveryContact.id = :deliveryContactId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("deliveryContactId", deliveryContactId)
				.getResultList();
	}
	/**
	 * Find by reference: lines
	 */
	public List<InventoryTransaction> findByLines(InventoryTransactionLine lines) {
		return this.findByLinesId(lines.getId());
	}
	/**
	 * Find by ID of reference: lines.id
	 */
	public List<InventoryTransaction> findByLinesId(String linesId) {
		return (List<InventoryTransaction>) this
				.getEntityManager()
				.createQuery(
						"select distinct e from InventoryTransaction e, IN (e.lines) c where e.clientId = :clientId and c.id = :linesId",
						InventoryTransaction.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("linesId", linesId).getResultList();
	}
}
