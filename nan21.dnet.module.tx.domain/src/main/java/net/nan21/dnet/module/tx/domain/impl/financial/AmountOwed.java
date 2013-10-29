/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.financial;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;

/**
 * Owed amounts generated by sales or purchase transaction documents.
 */
@Entity
@Table(name = AmountOwed.TABLE_NAME)
public class AmountOwed extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_AMNT_OWED";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DUEDATE", nullable = false)
	private Date dueDate;

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	/**Total amount payed until now */
	@NotNull
	@Column(name = "AMOUNTPAYED", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountPayed;

	/**The amount remained to be paid. */
	@NotNull
	@Column(name = "AMOUNTREMAINED", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountRemained;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpAccount.class)
	@JoinColumn(name = "BPACCOUNT_ID", referencedColumnName = "ID")
	private BpAccount bpAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
	@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SalesOrder.class)
	@JoinColumn(name = "SALESORDER_ID", referencedColumnName = "ID")
	private SalesOrder salesOrder;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SalesInvoice.class)
	@JoinColumn(name = "SALESINVOICE_ID", referencedColumnName = "ID")
	private SalesInvoice salesInvoice;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "PURCHASEORDER_ID", referencedColumnName = "ID")
	private PurchaseOrder purchaseOrder;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PurchaseInvoice.class)
	@JoinColumn(name = "PURCHASEINVOICE_ID", referencedColumnName = "ID")
	private PurchaseInvoice purchaseInvoice;

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Transient
	public Integer getDueInDays() {
		return (int) ((this.dueDate.getTime() - (new Date()).getTime()) / 86400000);
	}

	public void setDueInDays(Integer dueInDays) {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountPayed() {
		return this.amountPayed;
	}

	public void setAmountPayed(BigDecimal amountPayed) {
		this.amountPayed = amountPayed;
	}

	public BigDecimal getAmountRemained() {
		return this.amountRemained;
	}

	public void setAmountRemained(BigDecimal amountRemained) {
		this.amountRemained = amountRemained;
	}

	public BpAccount getBpAccount() {
		return this.bpAccount;
	}

	public void setBpAccount(BpAccount bpAccount) {
		if (bpAccount != null) {
			this.__validate_client_context__(bpAccount.getClientId());
		}
		this.bpAccount = bpAccount;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		if (currency != null) {
			this.__validate_client_context__(currency.getClientId());
		}
		this.currency = currency;
	}

	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		if (salesOrder != null) {
			this.__validate_client_context__(salesOrder.getClientId());
		}
		this.salesOrder = salesOrder;
	}

	public SalesInvoice getSalesInvoice() {
		return this.salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		if (salesInvoice != null) {
			this.__validate_client_context__(salesInvoice.getClientId());
		}
		this.salesInvoice = salesInvoice;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		if (purchaseOrder != null) {
			this.__validate_client_context__(purchaseOrder.getClientId());
		}
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseInvoice getPurchaseInvoice() {
		return this.purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		if (purchaseInvoice != null) {
			this.__validate_client_context__(purchaseInvoice.getClientId());
		}
		this.purchaseInvoice = purchaseInvoice;
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
		if (this.amountPayed == null) {
			this.amountPayed = new BigDecimal("0");
		}
		if (this.amountRemained == null) {
			this.amountRemained = new BigDecimal("0");
		}
	}
}