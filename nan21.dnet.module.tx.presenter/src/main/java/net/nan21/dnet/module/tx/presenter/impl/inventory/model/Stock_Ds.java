/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.presenter.impl.inventory.model;

import java.math.BigDecimal;
import net.nan21.dnet.core.api.annotation.Ds;
import net.nan21.dnet.core.api.annotation.DsField;
import net.nan21.dnet.core.api.annotation.SortField;
import net.nan21.dnet.core.presenter.model.AbstractAuditableDs;
import net.nan21.dnet.module.tx.domain.impl.inventory.Stock;

@Ds(entity = Stock.class, sort = {@SortField(field = Stock_Ds.f_inventory),
		@SortField(field = Stock_Ds.f_product)})
public class Stock_Ds extends AbstractAuditableDs<Stock> {

	public static final String f_quantity = "quantity";
	public static final String f_reserved = "reserved";
	public static final String f_available = "available";
	public static final String f_valueLoc = "valueLoc";
	public static final String f_valueRef = "valueRef";
	public static final String f_orgId = "orgId";
	public static final String f_org = "org";
	public static final String f_inventoryId = "inventoryId";
	public static final String f_inventory = "inventory";
	public static final String f_inventoryName = "inventoryName";
	public static final String f_locatorId = "locatorId";
	public static final String f_locator = "locator";
	public static final String f_locatorName = "locatorName";
	public static final String f_productId = "productId";
	public static final String f_product = "product";
	public static final String f_productName = "productName";
	public static final String f_uomId = "uomId";
	public static final String f_uom = "uom";

	@DsField
	private BigDecimal quantity;

	@DsField
	private BigDecimal reserved;

	@DsField
	private BigDecimal available;

	@DsField
	private BigDecimal valueLoc;

	@DsField
	private BigDecimal valueRef;

	@DsField(join = "left", path = "inventory.org.id")
	private String orgId;

	@DsField(join = "left", path = "inventory.org.code")
	private String org;

	@DsField(join = "left", path = "inventory.id")
	private String inventoryId;

	@DsField(join = "left", path = "inventory.code")
	private String inventory;

	@DsField(join = "left", path = "inventory.name")
	private String inventoryName;

	@DsField(join = "left", path = "location.id")
	private String locatorId;

	@DsField(join = "left", path = "location.code")
	private String locator;

	@DsField(join = "left", path = "location.name")
	private String locatorName;

	@DsField(join = "left", path = "product.id")
	private String productId;

	@DsField(join = "left", path = "product.code")
	private String product;

	@DsField(join = "left", path = "product.name")
	private String productName;

	@DsField(join = "left", path = "uom.id")
	private String uomId;

	@DsField(join = "left", path = "uom.code")
	private String uom;

	public Stock_Ds() {
		super();
	}

	public Stock_Ds(Stock e) {
		super(e);
	}

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

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getInventory() {
		return this.inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getInventoryName() {
		return this.inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getLocatorId() {
		return this.locatorId;
	}

	public void setLocatorId(String locatorId) {
		this.locatorId = locatorId;
	}

	public String getLocator() {
		return this.locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getLocatorName() {
		return this.locatorName;
	}

	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUomId() {
		return this.uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
}
