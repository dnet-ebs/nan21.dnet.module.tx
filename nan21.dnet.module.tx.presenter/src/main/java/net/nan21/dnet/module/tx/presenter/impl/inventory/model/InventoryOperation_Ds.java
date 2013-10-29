/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.presenter.impl.inventory.model;

import java.math.BigDecimal;
import java.util.Date;
import net.nan21.dnet.core.api.annotation.Ds;
import net.nan21.dnet.core.api.annotation.DsField;
import net.nan21.dnet.core.api.annotation.SortField;
import net.nan21.dnet.core.presenter.model.AbstractAuditableDs;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryOperation;

@Ds(entity = InventoryOperation.class, sort = {@SortField(field = InventoryOperation_Ds.f_eventDate, desc = true)})
public class InventoryOperation_Ds
		extends
			AbstractAuditableDs<InventoryOperation> {

	public static final String f_eventDate = "eventDate";
	public static final String f_direction = "direction";
	public static final String f_quantity = "quantity";
	public static final String f_priceLoc = "priceLoc";
	public static final String f_priceRef = "priceRef";
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
	private Date eventDate;

	@DsField
	private String direction;

	@DsField
	private BigDecimal quantity;

	@DsField
	private BigDecimal priceLoc;

	@DsField
	private BigDecimal priceRef;

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

	public InventoryOperation_Ds() {
		super();
	}

	public InventoryOperation_Ds(InventoryOperation e) {
		super(e);
	}

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
