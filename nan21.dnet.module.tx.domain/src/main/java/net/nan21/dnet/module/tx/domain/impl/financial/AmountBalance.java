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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 * Current business-partner account balance with respect to the specified company.
 * If the amount is positive, the business partner owes it to the company organization.
 * If the amount is positive, the company organization owes it to the business partner.
 */
@NamedQueries({
		@NamedQuery(name = AmountBalance.NQ_FIND_BY_BALANCE, query = "SELECT e FROM AmountBalance e WHERE e.clientId = :clientId and e.bpAccount = :bpAccount and e.currency = :currency", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE)),
		@NamedQuery(name = AmountBalance.NQ_FIND_BY_BALANCE_PRIMITIVE, query = "SELECT e FROM AmountBalance e WHERE e.clientId = :clientId and e.bpAccount.id = :bpAccountId and e.currency.id = :currencyId", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE))})
@Entity
@Table(name = AmountBalance.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(name = AmountBalance.TABLE_NAME
		+ "_UK1", columnNames = {"CLIENTID", "BPACCOUNT_ID", "CURRENCY_ID"})})
public class AmountBalance extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_AMNT_BALANCE";

	private static final long serialVersionUID = -8865917134914502125L;
	/**
	 * Named query find by unique key: Balance.
	 */
	public static final String NQ_FIND_BY_BALANCE = "AmountBalance.findByBalance";
	/**
	 * Named query find by unique key: Balance using the ID field for references.
	 */
	public static final String NQ_FIND_BY_BALANCE_PRIMITIVE = "AmountBalance.findByBalance_PRIMITIVE";

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpAccount.class)
	@JoinColumn(name = "BPACCOUNT_ID", referencedColumnName = "ID")
	private BpAccount bpAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
	@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
	private Currency currency;

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
	}
}
