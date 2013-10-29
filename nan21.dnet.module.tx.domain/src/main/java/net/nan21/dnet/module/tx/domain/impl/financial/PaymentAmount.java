/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.financial;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;

/**
 * Payment details, extinguished owed amounts.
 */
@Entity
@Table(name = PaymentAmount.TABLE_NAME)
public class PaymentAmount extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_PAY_AMNT";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	@Column(name = "AMOUNTLOC", precision = 21, scale = 6)
	private BigDecimal amountLoc;

	@Column(name = "AMOUNTREF", precision = 21, scale = 6)
	private BigDecimal amountRef;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Payment.class)
	@JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
	private Payment payment;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = AmountOwed.class)
	@JoinColumn(name = "AMOUNTOWED_ID", referencedColumnName = "ID")
	private AmountOwed amountOwed;

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountLoc() {
		return this.amountLoc;
	}

	public void setAmountLoc(BigDecimal amountLoc) {
		this.amountLoc = amountLoc;
	}

	public BigDecimal getAmountRef() {
		return this.amountRef;
	}

	public void setAmountRef(BigDecimal amountRef) {
		this.amountRef = amountRef;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public void setPayment(Payment payment) {
		if (payment != null) {
			this.__validate_client_context__(payment.getClientId());
		}
		this.payment = payment;
	}

	public AmountOwed getAmountOwed() {
		return this.amountOwed;
	}

	public void setAmountOwed(AmountOwed amountOwed) {
		if (amountOwed != null) {
			this.__validate_client_context__(amountOwed.getClientId());
		}
		this.amountOwed = amountOwed;
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
	}
}
