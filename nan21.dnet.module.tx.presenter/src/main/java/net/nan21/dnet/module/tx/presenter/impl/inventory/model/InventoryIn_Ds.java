/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.presenter.impl.inventory.model;

import java.util.Date;
import net.nan21.dnet.core.api.annotation.Ds;
import net.nan21.dnet.core.api.annotation.DsField;
import net.nan21.dnet.core.presenter.model.AbstractAuditableDs;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;

@Ds(entity = InventoryTransaction.class)
public class InventoryIn_Ds extends AbstractAuditableDs<InventoryTransaction> {

	public static final String f_docNo = "docNo";
	public static final String f_docDate = "docDate";
	public static final String f_eventDate = "eventDate";
	public static final String f_confirmed = "confirmed";
	public static final String f_posted = "posted";

	@DsField
	private String docNo;

	@DsField
	private Date docDate;

	@DsField
	private Date eventDate;

	@DsField
	private Boolean confirmed;

	@DsField
	private Boolean posted;

	public InventoryIn_Ds() {
		super();
	}

	public InventoryIn_Ds(InventoryTransaction e) {
		super(e);
	}

	public String getDocNo() {
		return this.docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getPosted() {
		return this.posted;
	}

	public void setPosted(Boolean posted) {
		this.posted = posted;
	}
}
