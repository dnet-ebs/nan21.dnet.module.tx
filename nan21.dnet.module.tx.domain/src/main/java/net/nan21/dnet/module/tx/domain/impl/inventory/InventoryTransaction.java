/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = InventoryTransaction.TABLE_NAME)
public class InventoryTransaction extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_IVT_TX";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotBlank
	@Column(name = "DOCNO", nullable = false, length = 255)
	private String docNo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCDATE", nullable = false)
	private Date docDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENTDATE")
	private Date eventDate;

	@NotNull
	@Column(name = "CONFIRMED", nullable = false)
	private Boolean confirmed;

	@NotNull
	@Column(name = "POSTED", nullable = false)
	private Boolean posted;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DocType.class)
	@JoinColumn(name = "DOCTYPE_ID", referencedColumnName = "ID")
	private DocType docType;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Org.class)
	@JoinColumn(name = "FROMINVENTORY_ID", referencedColumnName = "ID")
	private Org fromInventory;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Org.class)
	@JoinColumn(name = "TOINVENTORY_ID", referencedColumnName = "ID")
	private Org toInventory;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SalesOrder.class)
	@JoinColumn(name = "SALESORDER_ID", referencedColumnName = "ID")
	private SalesOrder salesOrder;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SalesInvoice.class)
	@JoinColumn(name = "SALESINVOICE_ID", referencedColumnName = "ID")
	private SalesInvoice salesInvoice;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "PURCHASEORDER_ID", referencedColumnName = "ID")
	private PurchaseOrder purchaseOrder;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PurchaseInvoice.class)
	@JoinColumn(name = "PURCHASEINVOICE_ID", referencedColumnName = "ID")
	private PurchaseInvoice purchaseInvoice;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpAccount.class)
	@JoinColumn(name = "BPACCOUNT_ID", referencedColumnName = "ID")
	private BpAccount bpAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
	@JoinColumn(name = "DELIVERYLOCATION_ID", referencedColumnName = "ID")
	private Location deliveryLocation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpContact.class)
	@JoinColumn(name = "DELIVERYCONTACT_ID", referencedColumnName = "ID")
	private BpContact deliveryContact;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = InventoryTransactionLine.class, mappedBy = "inventoryTransaction", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Collection<InventoryTransactionLine> lines;

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getPosted() {
		return this.posted;
	}

	public void setPosted(Boolean posted) {
		this.posted = posted;
	}

	public DocType getDocType() {
		return this.docType;
	}

	public void setDocType(DocType docType) {
		if (docType != null) {
			this.__validate_client_context__(docType.getClientId());
		}
		this.docType = docType;
	}

	public Org getFromInventory() {
		return this.fromInventory;
	}

	public void setFromInventory(Org fromInventory) {
		if (fromInventory != null) {
			this.__validate_client_context__(fromInventory.getClientId());
		}
		this.fromInventory = fromInventory;
	}

	public Org getToInventory() {
		return this.toInventory;
	}

	public void setToInventory(Org toInventory) {
		if (toInventory != null) {
			this.__validate_client_context__(toInventory.getClientId());
		}
		this.toInventory = toInventory;
	}

	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		if (salesOrder != null) {
			this.__validate_client_context__(salesOrder.getClientId());
		}
		this.salesOrder = salesOrder;
	}

	public SalesInvoice getSalesInvoice() {
		return this.salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		if (salesInvoice != null) {
			this.__validate_client_context__(salesInvoice.getClientId());
		}
		this.salesInvoice = salesInvoice;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		if (purchaseOrder != null) {
			this.__validate_client_context__(purchaseOrder.getClientId());
		}
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseInvoice getPurchaseInvoice() {
		return this.purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		if (purchaseInvoice != null) {
			this.__validate_client_context__(purchaseInvoice.getClientId());
		}
		this.purchaseInvoice = purchaseInvoice;
	}

	public BpAccount getBpAccount() {
		return this.bpAccount;
	}

	public void setBpAccount(BpAccount bpAccount) {
		if (bpAccount != null) {
			this.__validate_client_context__(bpAccount.getClientId());
		}
		this.bpAccount = bpAccount;
	}

	public Location getDeliveryLocation() {
		return this.deliveryLocation;
	}

	public void setDeliveryLocation(Location deliveryLocation) {
		if (deliveryLocation != null) {
			this.__validate_client_context__(deliveryLocation.getClientId());
		}
		this.deliveryLocation = deliveryLocation;
	}

	public BpContact getDeliveryContact() {
		return this.deliveryContact;
	}

	public void setDeliveryContact(BpContact deliveryContact) {
		if (deliveryContact != null) {
			this.__validate_client_context__(deliveryContact.getClientId());
		}
		this.deliveryContact = deliveryContact;
	}

	public Collection<InventoryTransactionLine> getLines() {
		return this.lines;
	}

	public void setLines(Collection<InventoryTransactionLine> lines) {
		this.lines = lines;
	}

	public void addToLines(InventoryTransactionLine e) {
		if (this.lines == null) {
			this.lines = new ArrayList<InventoryTransactionLine>();
		}
		e.setInventoryTransaction(this);
		this.lines.add(e);
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.confirmed == null) {
			this.confirmed = new Boolean(false);
		}
		if (this.posted == null) {
			this.posted = new Boolean(false);
		}
	}
}
