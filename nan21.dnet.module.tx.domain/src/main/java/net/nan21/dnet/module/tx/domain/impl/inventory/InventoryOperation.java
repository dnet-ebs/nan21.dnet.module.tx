/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.inventory;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = InventoryOperation.TABLE_NAME)
public class InventoryOperation extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_IVT_OPR";

	private static final long serialVersionUID = -8865917134914502125L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENTDATE")
	private Date eventDate;

	@NotBlank
	@Column(name = "DIRECTION", nullable = false, length = 8)
	private String direction;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@NotNull
	@Column(name = "PRICELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceLoc;

	@NotNull
	@Column(name = "PRICEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceRef;

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

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = InventoryTransactionLine.class)
	@JoinColumn(name = "TRANSACTIONLINE_ID", referencedColumnName = "ID")
	private InventoryTransactionLine transactionLine;

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public InventoryTransactionLine getTransactionLine() {
		return this.transactionLine;
	}

	public void setTransactionLine(InventoryTransactionLine transactionLine) {
		if (transactionLine != null) {
			this.__validate_client_context__(transactionLine.getClientId());
		}
		this.transactionLine = transactionLine;
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
