/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
Ext.define(Dnet.ns.tx + "Stock_Dc" , {
	extend: "dnet.core.dc.AbstractDc",
	recordModel: Dnet.ns.tx + "Stock_Ds"
});

/* ================= FILTER FORM: Filter ================= */			

Ext.define(Dnet.ns.tx + "Stock_Dc$Filter" , {
	extend: "dnet.core.dc.view.AbstractDcvFilterForm",
	alias: "widget.tx_Stock_Dc$Filter",

	/**
	 * Components definition
	 */	
	_defineElements_: function() {
		this._getBuilder_()
		
		/* =========== controls =========== */
		.addLov({name:"product", dataIndex:"product", xtype:"md_Products_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "productId"} ]})
		.addLov({name:"org", dataIndex:"org", xtype:"md_Orgs_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "orgId"} ]})
		.addLov({name:"inventory", dataIndex:"inventory", xtype:"md_Inventories_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "inventoryId"} ]})
		.addLov({name:"locator", dataIndex:"locator", xtype:"md_InventoryLocations_Lov", caseRestriction:"uppercase",
			retFieldMapping: [{lovField:"id", dsField: "locatorId"} ]})
		.addNumberField({name:"quantity", dataIndex:"quantity", decimals:6})
		
		/* =========== containers =========== */
		.addPanel({ name:"main", autoScroll:true, layout: {type:"hbox", align:'top', pack:'start', defaultMargins: {right:5, left:5}},
		autoScroll:true, padding:"0 30 5 0"})
		.addPanel({ name:"col1", width:210, layout:"form"})
		.addPanel({ name:"col2", width:250, layout:"form"})
		.addPanel({ name:"col3", width:300, layout:"form"});
	},

	/**
	 * Combine the components
	 */				
	_linkElements_: function() {
		this._getBuilder_()
		.addChildrenTo("main", ["col1", "col2", "col3"])
		.addChildrenTo("col1", ["org"])
		.addChildrenTo("col2", ["inventory", "locator"])
		.addChildrenTo("col3", ["product"]);
	}
});

/* ================= GRID: List ================= */

Ext.define(Dnet.ns.tx + "Stock_Dc$List" , {
	extend: "dnet.core.dc.view.AbstractDcvGrid",
	alias: "widget.tx_Stock_Dc$List",

	/**
	 * Columns definition
	 */
	_defineColumns_: function() {
		this._getBuilder_()
		.addTextColumn({ name:"productId", dataIndex:"productId", hidden:true, width:100})
		.addTextColumn({ name:"product", dataIndex:"product", width:120})
		.addTextColumn({ name:"productName", dataIndex:"productName", hidden:true, width:200})
		.addTextColumn({ name:"inventoryId", dataIndex:"inventoryId", hidden:true, width:100})
		.addTextColumn({ name:"inventory", dataIndex:"inventory", width:120})
		.addTextColumn({ name:"inventoryName", dataIndex:"inventoryName", hidden:true, width:200})
		.addTextColumn({ name:"locatorId", dataIndex:"locatorId", hidden:true, width:100})
		.addTextColumn({ name:"locator", dataIndex:"locator", width:120})
		.addNumberColumn({ name:"quantity", dataIndex:"quantity", decimals:6})
		.addTextColumn({ name:"uom", dataIndex:"uom", width:80})
		.addNumberColumn({ name:"reserved", dataIndex:"reserved", decimals:6})
		.addNumberColumn({ name:"available", dataIndex:"available", decimals:6})
		.addTextColumn({ name:"uomId", dataIndex:"uomId", hidden:true, width:100})
		.addDefaults();
	}
});
