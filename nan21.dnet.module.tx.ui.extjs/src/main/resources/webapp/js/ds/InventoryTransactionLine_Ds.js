/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
	
Ext.define(Dnet.ns.tx + "InventoryTransactionLine_Ds", {
	extend: 'Ext.data.Model',
	
	validations: [
		{field: "product", type: 'presence'},
		{field: "quantity", type: 'presence'},
		{field: "uom", type: 'presence'}
	],
	
	fields: [
		{name:"quantity", type:"float", useNull:true},
		{name:"price", type:"float", useNull:true},
		{name:"priceLoc", type:"float", useNull:true},
		{name:"priceRef", type:"float", useNull:true},
		{name:"fromInventoryId", type:"string"},
		{name:"fromInventory", type:"string"},
		{name:"fromInventoryName", type:"string"},
		{name:"toInventoryId", type:"string"},
		{name:"toInventory", type:"string"},
		{name:"toInventoryName", type:"string"},
		{name:"fromLocationId", type:"string"},
		{name:"fromLocation", type:"string"},
		{name:"fromLocationName", type:"string"},
		{name:"toLocationId", type:"string"},
		{name:"toLocation", type:"string"},
		{name:"toLocationName", type:"string"},
		{name:"productId", type:"string"},
		{name:"product", type:"string"},
		{name:"productName", type:"string"},
		{name:"uomId", type:"string"},
		{name:"uom", type:"string"},
		{name:"id", type:"string"},
		{name:"clientId", type:"string"},
		{name:"createdAt", type:"date", dateFormat:Dnet.MODEL_DATE_FORMAT},
		{name:"modifiedAt", type:"date", dateFormat:Dnet.MODEL_DATE_FORMAT},
		{name:"createdBy", type:"string"},
		{name:"modifiedBy", type:"string"},
		{name:"notes", type:"string"},
		{name:"active", type:"boolean"},
		{name:"version", type:"int", useNull:true},
		{name:"refid", type:"string"},
		{name:"entityAlias", type:"string"},
		{name:"entityFqn", type:"string"}
	]
});
