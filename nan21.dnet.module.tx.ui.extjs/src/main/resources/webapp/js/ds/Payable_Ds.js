/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
	
Ext.define(Dnet.ns.tx + "Payable_Ds", {
	extend: 'Ext.data.Model',
	
	fields: [
		{name:"dueDate", type:"date", dateFormat:Dnet.MODEL_DATE_FORMAT},
		{name:"dueInDays", type:"int", useNull:true},
		{name:"amount", type:"float", useNull:true},
		{name:"amountPayed", type:"float", useNull:true},
		{name:"amountRemained", type:"float", useNull:true},
		{name:"companyId", type:"string"},
		{name:"company", type:"string"},
		{name:"bpAccountId", type:"string"},
		{name:"vendorId", type:"string"},
		{name:"vendor", type:"string"},
		{name:"vendorName", type:"string"},
		{name:"currencyId", type:"string"},
		{name:"currency", type:"string"},
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
