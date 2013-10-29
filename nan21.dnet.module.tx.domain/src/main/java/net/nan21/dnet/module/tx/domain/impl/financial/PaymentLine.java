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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;

/**
 * Payment details, payed products. 
 */
@Entity
@Table(name = PaymentLine.TABLE_NAME)
public class PaymentLine extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_PAY_LINE";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@NotNull
	@Column(name = "UNITPRICE", nullable = false, precision = 21, scale = 6)
	private BigDecimal unitPrice;

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Payment.class)
	@JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
	private Payment payment;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ProductAccount.class)
	@JoinColumn(name = "PRODUCTACCOUNT_ID", referencedColumnName = "ID")
	private ProductAccount productAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Uom.class)
	@JoinColumn(name = "UOM_ID", referencedColumnName = "ID")
	private Uom uom;

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public ProductAccount getProductAccount() {
		return this.productAccount;
	}

	public void setProductAccount(ProductAccount productAccount) {
		if (productAccount != null) {
			this.__validate_client_context__(productAccount.getClientId());
		}
		this.productAccount = productAccount;
	}

	public Uom getUom() {
		return this.uom;
	}

	public void setUom(Uom uom) {
		if (uom != null) {
			this.__validate_client_context__(uom.getClientId());
		}
		this.uom = uom;
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.quantity == null) {
			this.quantity = new BigDecimal("0");
		}
		if (this.unitPrice == null) {
			this.unitPrice = new BigDecimal("0");
		}
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
	}
}
