/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.inventory;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;

@Entity
@Table(name = InventoryTransactionLine.TABLE_NAME)
public class InventoryTransactionLine extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_IVT_TX_LINE";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@Column(name = "PRICE", precision = 21, scale = 6)
	private BigDecimal price;

	@NotNull
	@Column(name = "PRICELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceLoc;

	@NotNull
	@Column(name = "PRICEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceRef;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InventoryTransaction.class)
	@JoinColumn(name = "INVENTORYTRANSACTION_ID", referencedColumnName = "ID")
	private InventoryTransaction inventoryTransaction;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Inventory.class)
	@JoinColumn(name = "FROMINVENTORY_ID", referencedColumnName = "ID")
	private Inventory fromInventory;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InventoryLocation.class)
	@JoinColumn(name = "FROMLOCATION_ID", referencedColumnName = "ID")
	private InventoryLocation fromLocation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Inventory.class)
	@JoinColumn(name = "TOINVENTORY_ID", referencedColumnName = "ID")
	private Inventory toInventory;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InventoryLocation.class)
	@JoinColumn(name = "TOLOCATION_ID", referencedColumnName = "ID")
	private InventoryLocation toLocation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Uom.class)
	@JoinColumn(name = "UOM_ID", referencedColumnName = "ID")
	private Uom uom;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
	@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
	private Currency currency;

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceLoc() {
		return this.priceLoc;
	}

	public void setPriceLoc(BigDecimal priceLoc) {
		this.priceLoc = priceLoc;
	}

	public BigDecimal getPriceRef() {
		return this.priceRef;
	}

	public void setPriceRef(BigDecimal priceRef) {
		this.priceRef = priceRef;
	}

	public InventoryTransaction getInventoryTransaction() {
		return this.inventoryTransaction;
	}

	public void setInventoryTransaction(
			InventoryTransaction inventoryTransaction) {
		if (inventoryTransaction != null) {
			this.__validate_client_context__(inventoryTransaction.getClientId());
		}
		this.inventoryTransaction = inventoryTransaction;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		if (product != null) {
			this.__validate_client_context__(product.getClientId());
		}
		this.product = product;
	}

	public Inventory getFromInventory() {
		return this.fromInventory;
	}

	public void setFromInventory(Inventory fromInventory) {
		if (fromInventory != null) {
			this.__validate_client_context__(fromInventory.getClientId());
		}
		this.fromInventory = fromInventory;
	}

	public InventoryLocation getFromLocation() {
		return this.fromLocation;
	}

	public void setFromLocation(InventoryLocation fromLocation) {
		if (fromLocation != null) {
			this.__validate_client_context__(fromLocation.getClientId());
		}
		this.fromLocation = fromLocation;
	}

	public Inventory getToInventory() {
		return this.toInventory;
	}

	public void setToInventory(Inventory toInventory) {
		if (toInventory != null) {
			this.__validate_client_context__(toInventory.getClientId());
		}
		this.toInventory = toInventory;
	}

	public InventoryLocation getToLocation() {
		return this.toLocation;
	}

	public void setToLocation(InventoryLocation toLocation) {
		if (toLocation != null) {
			this.__validate_client_context__(toLocation.getClientId());
		}
		this.toLocation = toLocation;
	}

	public Uom getUom() {
		return this.uom;
	}

	public void setUom(Uom uom) {
		if (uom != null) {
			this.__validate_client_context__(uom.getClientId());
		}
		this.uom = uom;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		if (currency != null) {
			this.__validate_client_context__(currency.getClientId());
		}
		this.currency = currency;
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.quantity == null) {
			this.quantity = new BigDecimal("0");
		}
		if (this.priceLoc == null) {
			this.priceLoc = new BigDecimal("0");
		}
		if (this.priceRef == null) {
			this.priceRef = new BigDecimal("0");
		}
	}
}
