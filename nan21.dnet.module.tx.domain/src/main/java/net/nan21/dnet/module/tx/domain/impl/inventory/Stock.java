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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;

@Entity
@Table(name = Stock.TABLE_NAME)
public class Stock extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_STOCK";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@NotNull
	@Column(name = "RESERVED", nullable = false, precision = 21, scale = 6)
	private BigDecimal reserved;

	@NotNull
	@Column(name = "AVAILABLE", nullable = false, precision = 21, scale = 6)
	private BigDecimal available;

	@NotNull
	@Column(name = "VALUELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal valueLoc;

	@NotNull
	@Column(name = "VALUEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal valueRef;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Inventory.class)
	@JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
	private Inventory inventory;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InventoryLocation.class)
	@JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
	private InventoryLocation location;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Uom.class)
	@JoinColumn(name = "UOM_ID", referencedColumnName = "ID")
	private Uom uom;

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getReserved() {
		return this.reserved;
	}

	public void setReserved(BigDecimal reserved) {
		this.reserved = reserved;
	}

	public BigDecimal getAvailable() {
		return this.available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getValueLoc() {
		return this.valueLoc;
	}

	public void setValueLoc(BigDecimal valueLoc) {
		this.valueLoc = valueLoc;
	}

	public BigDecimal getValueRef() {
		return this.valueRef;
	}

	public void setValueRef(BigDecimal valueRef) {
		this.valueRef = valueRef;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		if (inventory != null) {
			this.__validate_client_context__(inventory.getClientId());
		}
		this.inventory = inventory;
	}

	public InventoryLocation getLocation() {
		return this.location;
	}

	public void setLocation(InventoryLocation location) {
		if (location != null) {
			this.__validate_client_context__(location.getClientId());
		}
		this.location = location;
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

	public Uom getUom() {
		return this.uom;
	}

	public void setUom(Uom uom) {
		if (uom != null) {
			this.__validate_client_context__(uom.getClientId());
		}
		this.uom = uom;
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.quantity == null) {
			this.quantity = new BigDecimal("0");
		}
		if (this.reserved == null) {
			this.reserved = new BigDecimal("0");
		}
		if (this.available == null) {
			this.available = new BigDecimal("0");
		}
		if (this.valueLoc == null) {
			this.valueLoc = new BigDecimal("0");
		}
		if (this.valueRef == null) {
			this.valueRef = new BigDecimal("0");
		}
	}
}
