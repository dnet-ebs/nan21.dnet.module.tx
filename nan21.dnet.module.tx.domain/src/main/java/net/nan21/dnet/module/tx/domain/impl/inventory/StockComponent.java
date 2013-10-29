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
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;

@Entity
@Table(name = StockComponent.TABLE_NAME)
public class StockComponent extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_STOCK_CMP";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@NotNull
	@Column(name = "PRICELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceLoc;

	@NotNull
	@Column(name = "PRICEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal priceRef;

	@NotNull
	@Column(name = "VALUELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal valueLoc;

	@NotNull
	@Column(name = "VALUEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal valueRef;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Stock.class)
	@JoinColumn(name = "STOCK_ID", referencedColumnName = "ID")
	private Stock stock;

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

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		if (stock != null) {
			this.__validate_client_context__(stock.getClientId());
		}
		this.stock = stock;
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
		if (this.valueLoc == null) {
			this.valueLoc = new BigDecimal("0");
		}
		if (this.valueRef == null) {
			this.valueRef = new BigDecimal("0");
		}
	}
}
