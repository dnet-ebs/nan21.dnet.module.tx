/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
Ext.define(Dnet.ns.tx + "InventoryOperation_Dc" , {
	extend: "dnet.core.dc.AbstractDc",
	filterModel: Dnet.ns.tx + "InventoryOperation_DsFilter",
	recordModel: Dnet.ns.tx + "InventoryOperation_Ds"
});

/* ================= FILTER FORM: Filter ================= */			

Ext.define(Dnet.ns.tx + "InventoryOperation_Dc$Filter" , {
	extend: "dnet.core.dc.view.AbstractDcvFilterForm",
	alias: "widget.tx_InventoryOperation_Dc$Filter",

	/**
	 * Components definition
	 */	
	_defineElements_: function() {
		this._getBuilder_()
		
		/* =========== controls =========== */
		.addDateField({name:"eventDate_From", dataIndex:"eventDate_From", emptyText:"From" })
		.addDateField({name:"eventDate_To", dataIndex:"eventDate_To", emptyText:"To" })
		.addFieldContainer({name: "eventDate"})
			.addChildrenTo("eventDate",["eventDate_From", "eventDate_To"])
		.addLov({name:"product", dataIndex:"product", xtype:"md_Products_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "productId"} ]})
		.addLov({name:"org", dataIndex:"org", xtype:"md_Orgs_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "orgId"} ]})
		.addLov({name:"inventory", dataIndex:"inventory", xtype:"md_Inventories_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "inventoryId"} ]})
		.addLov({name:"locator", dataIndex:"locator", xtype:"md_InventoryLocations_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "locatorId"} ]})
		.addCombo({ xtype:"combo", name:"direction", dataIndex:"direction", store:[ "in", "out"]})
		.addNumberField({name:"quantity", dataIndex:"quantity", decimals:6})
		
		/* =========== containers =========== */
		.addPanel({ name:"main", autoScroll:true, layout: {type:"hbox", align:'top', pack:'start', defaultMargins: {right:5, left:5}},
		autoScroll:true, padding:"0 30 5 0"})
		.addPanel({ name:"col1", width:210, layout:"form"})
		.addPanel({ name:"col2", width:250, layout:"form"})
		.addPanel({ name:"col3", width:300, layout:"form"})
		.addPanel({ name:"col4", width:210, layout:"form"});
	},

	/**
	 * Combine the components
	 */				
	_linkElements_: function() {
		this._getBuilder_()
		.addChildrenTo("main", ["col1", "col2", "col3", "col4"])
		.addChildrenTo("col1", ["org"])
		.addChildrenTo("col2", ["inventory", "locator"])
		.addChildrenTo("col3", ["product", "eventDate"])
		.addChildrenTo("col4", ["direction"]);
	}
});

/* ================= GRID: List ================= */

Ext.define(Dnet.ns.tx + "InventoryOperation_Dc$List" , {
	extend: "dnet.core.dc.view.AbstractDcvGrid",
	alias: "widget.tx_InventoryOperation_Dc$List",

	/**
	 * Columns definition
	 */
	_defineColumns_: function() {
		this._getBuilder_()
		.addDateColumn({ name:"eventDate", dataIndex:"eventDate", _mask_: Masks.DATETIME})
		.addTextColumn({ name:"productId", dataIndex:"productId", hidden:true, width:100})
		.addTextColumn({ name:"product", dataIndex:"product", width:120})
		.addTextColumn({ name:"productName", dataIndex:"productName", hidden:true, width:200})
		.addTextColumn({ name:"inventoryId", dataIndex:"inventoryId", hidden:true, width:100})
		.addTextColumn({ name:"inventory", dataIndex:"inventory", width:120})
		.addTextColumn({ name:"inventoryName", dataIndex:"inventoryName", hidden:true, width:200})
		.addTextColumn({ name:"locatorId", dataIndex:"locatorId", hidden:true, width:100})
		.addTextColumn({ name:"locator", dataIndex:"locator", width:120})
		.addTextColumn({ name:"direction", dataIndex:"direction", width:60})
		.addNumberColumn({ name:"quantity", dataIndex:"quantity", decimals:6})
		.addTextColumn({ name:"uom", dataIndex:"uom", width:80})
		.addTextColumn({ name:"uomId", dataIndex:"uomId", hidden:true, width:100})
		.addDefaults();
	}
});
