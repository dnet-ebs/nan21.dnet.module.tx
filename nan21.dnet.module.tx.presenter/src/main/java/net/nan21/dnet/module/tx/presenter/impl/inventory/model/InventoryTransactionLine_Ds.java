/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.presenter.impl.inventory.model;

import java.math.BigDecimal;
import net.nan21.dnet.core.api.annotation.Ds;
import net.nan21.dnet.core.api.annotation.DsField;
import net.nan21.dnet.core.api.annotation.Param;
import net.nan21.dnet.core.api.annotation.RefLookup;
import net.nan21.dnet.core.api.annotation.RefLookups;
import net.nan21.dnet.core.presenter.model.AbstractAuditableDs;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.Product;
import net.nan21.dnet.module.md.domain.impl.org.Inventory;
import net.nan21.dnet.module.md.domain.impl.org.InventoryLocation;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransactionLine;

@Ds(entity = InventoryTransactionLine.class)
@RefLookups({
		@RefLookup(refId = InventoryTransactionLine_Ds.f_productId, namedQuery = Product.NQ_FIND_BY_CODE, params = {@Param(name = "code", field = InventoryTransactionLine_Ds.f_product)}),
		@RefLookup(refId = InventoryTransactionLine_Ds.f_uomId, namedQuery = Uom.NQ_FIND_BY_CODE, params = {@Param(name = "code", field = InventoryTransactionLine_Ds.f_uom)}),
		@RefLookup(refId = InventoryTransactionLine_Ds.f_fromInventoryId, namedQuery = Inventory.NQ_FIND_BY_CODE_PRIMITIVE, params = {@Param(name = "orgId", field = InventoryTransactionLine_Ds.f_fromInventory)}),
		@RefLookup(refId = InventoryTransactionLine_Ds.f_toInventoryId, namedQuery = Inventory.NQ_FIND_BY_CODE_PRIMITIVE, params = {@Param(name = "orgId", field = InventoryTransactionLine_Ds.f_toInventory)}),
		@RefLookup(refId = InventoryTransactionLine_Ds.f_fromLocationId, namedQuery = InventoryLocation.NQ_FIND_BY_CODE_PRIMITIVE, params = {@Param(name = "inventoryId", field = InventoryTransactionLine_Ds.f_fromLocation)}),
		@RefLookup(refId = InventoryTransactionLine_Ds.f_toLocationId, namedQuery = InventoryLocation.NQ_FIND_BY_CODE_PRIMITIVE, params = {@Param(name = "inventoryId", field = InventoryTransactionLine_Ds.f_toLocation)})})
public class InventoryTransactionLine_Ds
		extends
			AbstractAuditableDs<InventoryTransactionLine> {

	public static final String f_quantity = "quantity";
	public static final String f_price = "price";
	public static final String f_priceLoc = "priceLoc";
	public static final String f_priceRef = "priceRef";
	public static final String f_fromInventoryId = "fromInventoryId";
	public static final String f_fromInventory = "fromInventory";
	public static final String f_fromInventoryName = "fromInventoryName";
	public static final String f_toInventoryId = "toInventoryId";
	public static final String f_toInventory = "toInventory";
	public static final String f_toInventoryName = "toInventoryName";
	public static final String f_fromLocationId = "fromLocationId";
	public static final String f_fromLocation = "fromLocation";
	public static final String f_fromLocationName = "fromLocationName";
	public static final String f_toLocationId = "toLocationId";
	public static final String f_toLocation = "toLocation";
	public static final String f_toLocationName = "toLocationName";
	public static final String f_productId = "productId";
	public static final String f_product = "product";
	public static final String f_productName = "productName";
	public static final String f_uomId = "uomId";
	public static final String f_uom = "uom";

	@DsField
	private BigDecimal quantity;

	@DsField
	private BigDecimal price;

	@DsField
	private BigDecimal priceLoc;

	@DsField
	private BigDecimal priceRef;

	@DsField(join = "left", path = "fromInventory.id")
	private String fromInventoryId;

	@DsField(join = "left", path = "fromInventory.code")
	private String fromInventory;

	@DsField(join = "left", path = "fromInventory.name")
	private String fromInventoryName;

	@DsField(join = "left", path = "toInventory.id")
	private String toInventoryId;

	@DsField(join = "left", path = "toInventory.code")
	private String toInventory;

	@DsField(join = "left", path = "toInventory.name")
	private String toInventoryName;

	@DsField(join = "left", path = "fromLocation.id")
	private String fromLocationId;

	@DsField(join = "left", path = "fromLocation.code")
	private String fromLocation;

	@DsField(join = "left", path = "fromLocation.name")
	private String fromLocationName;

	@DsField(join = "left", path = "toLocation.id")
	private String toLocationId;

	@DsField(join = "left", path = "toLocation.code")
	private String toLocation;

	@DsField(join = "left", path = "toLocation.name")
	private String toLocationName;

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

	public InventoryTransactionLine_Ds() {
		super();
	}

	public InventoryTransactionLine_Ds(InventoryTransactionLine e) {
		super(e);
	}

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

	public String getFromInventoryId() {
		return this.fromInventoryId;
	}

	public void setFromInventoryId(String fromInventoryId) {
		this.fromInventoryId = fromInventoryId;
	}

	public String getFromInventory() {
		return this.fromInventory;
	}

	public void setFromInventory(String fromInventory) {
		this.fromInventory = fromInventory;
	}

	public String getFromInventoryName() {
		return this.fromInventoryName;
	}

	public void setFromInventoryName(String fromInventoryName) {
		this.fromInventoryName = fromInventoryName;
	}

	public String getToInventoryId() {
		return this.toInventoryId;
	}

	public void setToInventoryId(String toInventoryId) {
		this.toInventoryId = toInventoryId;
	}

	public String getToInventory() {
		return this.toInventory;
	}

	public void setToInventory(String toInventory) {
		this.toInventory = toInventory;
	}

	public String getToInventoryName() {
		return this.toInventoryName;
	}

	public void setToInventoryName(String toInventoryName) {
		this.toInventoryName = toInventoryName;
	}

	public String getFromLocationId() {
		return this.fromLocationId;
	}

	public void setFromLocationId(String fromLocationId) {
		this.fromLocationId = fromLocationId;
	}

	public String getFromLocation() {
		return this.fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getFromLocationName() {
		return this.fromLocationName;
	}

	public void setFromLocationName(String fromLocationName) {
		this.fromLocationName = fromLocationName;
	}

	public String getToLocationId() {
		return this.toLocationId;
	}

	public void setToLocationId(String toLocationId) {
		this.toLocationId = toLocationId;
	}

	public String getToLocation() {
		return this.toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getToLocationName() {
		return this.toLocationName;
	}

	public void setToLocationName(String toLocationName) {
		this.toLocationName = toLocationName;
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
