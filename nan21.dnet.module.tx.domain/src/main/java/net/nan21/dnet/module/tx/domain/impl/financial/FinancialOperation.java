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
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.md.domain.impl.org.FinancialAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = FinancialOperation.TABLE_NAME)
public class FinancialOperation extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_FIN_OPR";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EVENTDATE", nullable = false)
	private Date eventDate;

	@NotBlank
	@Column(name = "DIRECTION", nullable = false, length = 8)
	private String direction;

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = FinancialAccount.class)
	@JoinColumn(name = "FINACCOUNT_ID", referencedColumnName = "ID")
	private FinancialAccount finAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Payment.class)
	@JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
	private Payment payment;

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public FinancialAccount getFinAccount() {
		return this.finAccount;
	}

	public void setFinAccount(FinancialAccount finAccount) {
		if (finAccount != null) {
			this.__validate_client_context__(finAccount.getClientId());
		}
		this.finAccount = finAccount;
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

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
	}
}
