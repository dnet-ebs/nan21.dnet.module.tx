/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
	
Ext.define(Dnet.ns.tx + "Stock_Ds", {
	extend: 'Ext.data.Model',
	
	fields: [
		{name:"quantity", type:"float", useNull:true},
		{name:"reserved", type:"float", useNull:true},
		{name:"available", type:"float", useNull:true},
		{name:"valueLoc", type:"float", useNull:true},
		{name:"valueRef", type:"float", useNull:true},
		{name:"orgId", type:"string"},
		{name:"org", type:"string"},
		{name:"inventoryId", type:"string"},
		{name:"inventory", type:"string"},
		{name:"inventoryName", type:"string"},
		{name:"locatorId", type:"string"},
		{name:"locator", type:"string"},
		{name:"locatorName", type:"string"},
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
