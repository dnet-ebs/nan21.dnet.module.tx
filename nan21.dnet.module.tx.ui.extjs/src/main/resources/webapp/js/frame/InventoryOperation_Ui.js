/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
Ext.define(Dnet.ns.tx + "InventoryOperation_Ui" , {
	extend: "dnet.core.ui.AbstractUi",
	alias: "widget.InventoryOperation_Ui",
	
	/**
	 * Data-controls definition
	 */
	_defineDcs_: function() {
		this._getBuilder_()	
		.addDc("invop", Ext.create(Dnet.ns.tx + "InventoryOperation_Dc" ,{}))
		;
	},

	/**
	 * Components definition
	 */
	_defineElements_: function() {
		this._getBuilder_()
		.addDcFilterFormView("invop", {name:"invopFilter", xtype:"tx_InventoryOperation_Dc$Filter"})
		.addDcGridView("invop", {name:"invopList", xtype:"tx_InventoryOperation_Dc$List"})
		.addPanel({name:"main", layout:"border", defaults:{split:true}});
	},
	
	/**
	 * Combine the components
	 */
	_linkElements_: function() {
		this._getBuilder_()
		.addChildrenTo("main", ["invopFilter", "invopList"], ["north", "center"])
		.addToolbarTo("main", "tlbInvopList");
	},
	
	/**
	 * Create toolbars
	 */
	_defineToolbars_: function() {
		this._getBuilder_()
		.beginToolbar("tlbInvopList", {dc: "invop"})
			.addTitle().addSeparator().addSeparator()
			.addQuery()
			.addReports()
		.end();
	}

});
