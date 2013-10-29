/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
Ext.define(Dnet.ns.tx + "Stock_Ui" , {
	extend: "dnet.core.ui.AbstractUi",
	alias: "widget.Stock_Ui",
	
	/**
	 * Data-controls definition
	 */
	_defineDcs_: function() {
		this._getBuilder_()	
		.addDc("stock", Ext.create(Dnet.ns.tx + "Stock_Dc" ,{}))
		;
	},

	/**
	 * Components definition
	 */
	_defineElements_: function() {
		this._getBuilder_()
		.addDcFilterFormView("stock", {name:"stockFilter", xtype:"tx_Stock_Dc$Filter"})
		.addDcGridView("stock", {name:"stockList", xtype:"tx_Stock_Dc$List"})
		.addPanel({name:"main", layout:"border", defaults:{split:true}});
	},
	
	/**
	 * Combine the components
	 */
	_linkElements_: function() {
		this._getBuilder_()
		.addChildrenTo("main", ["stockFilter", "stockList"], ["north", "center"])
		.addToolbarTo("main", "tlbStockList");
	},
	
	/**
	 * Create toolbars
	 */
	_defineToolbars_: function() {
		this._getBuilder_()
		.beginToolbar("tlbStockList", {dc: "stock"})
			.addTitle().addSeparator().addSeparator()
			.addQuery()
			.addReports()
		.end();
	}

});
