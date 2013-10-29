/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
	
Ext.define(Dnet.ns.tx + "StockComponent_Ds", {
	extend: 'Ext.data.Model',
	
	fields: [
		{name:"stockId", type:"string"},
		{name:"quantity", type:"float", useNull:true},
		{name:"priceLoc", type:"float", useNull:true},
		{name:"priceRef", type:"float", useNull:true},
		{name:"valueLoc", type:"float", useNull:true},
		{name:"valueRef", type:"float", useNull:true},
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
