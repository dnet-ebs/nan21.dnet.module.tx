/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.presenter.impl.inventory.model;

import java.math.BigDecimal;
import net.nan21.dnet.core.api.annotation.Ds;
import net.nan21.dnet.core.api.annotation.DsField;
import net.nan21.dnet.core.presenter.model.AbstractAuditableDs;
import net.nan21.dnet.module.tx.domain.impl.inventory.StockComponent;

@Ds(entity = StockComponent.class)
public class StockComponent_Ds extends AbstractAuditableDs<StockComponent> {

	public static final String f_stockId = "stockId";
	public static final String f_quantity = "quantity";
	public static final String f_priceLoc = "priceLoc";
	public static final String f_priceRef = "priceRef";
	public static final String f_valueLoc = "valueLoc";
	public static final String f_valueRef = "valueRef";

	@DsField(join = "left", path = "stock.id")
	private String stockId;

	@DsField
	private BigDecimal quantity;

	@DsField
	private BigDecimal priceLoc;

	@DsField
	private BigDecimal priceRef;

	@DsField
	private BigDecimal valueLoc;

	@DsField
	private BigDecimal valueRef;

	public StockComponent_Ds() {
		super();
	}

	public StockComponent_Ds(StockComponent e) {
		super(e);
	}

	public String getStockId() {
		return this.stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
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
}
