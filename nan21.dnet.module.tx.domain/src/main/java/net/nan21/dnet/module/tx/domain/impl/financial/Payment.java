/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.financial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.BankAccount;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.FinancialAccount;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Payment document. 
 * A payment can be made in exchange of products/services as described in PaymentLine 
 * or to cover owed amounts as described in PaymentAmount. 
 */
@NamedQueries({@NamedQuery(name = Payment.NQ_FIND_BY_DOCNO, query = "SELECT e FROM Payment e WHERE e.clientId = :clientId and e.docNo = :docNo", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE))})
@Entity
@Table(name = Payment.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(name = Payment.TABLE_NAME
		+ "_UK1", columnNames = {"CLIENTID", "DOCNO"})})
public class Payment extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_PAY";

	private static final long serialVersionUID = -8865917134914502125L;
	/**
	 * Named query find by unique key: Docno.
	 */
	public static final String NQ_FIND_BY_DOCNO = "Payment.findByDocno";

	@NotBlank
	@Column(name = "DIRECTION", nullable = false, length = 8)
	private String direction;

	@NotBlank
	@Column(name = "USAGE", nullable = false, length = 16)
	private String usage;

	@NotBlank
	@Column(name = "DOCNO", nullable = false, length = 255)
	private String docNo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCDATE", nullable = false)
	private Date docDate;

	@Column(name = "SOURCEDOCNO", length = 255)
	private String sourceDocNo;

	@Column(name = "AMOUNT", precision = 21, scale = 6)
	private BigDecimal amount;

	@Column(name = "AMOUNTLOC", precision = 21, scale = 6)
	private BigDecimal amountLoc;

	@Column(name = "AMOUNTREF", precision = 21, scale = 6)
	private BigDecimal amountRef;

	@NotNull
	@Column(name = "XRATELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal xrateLoc;

	@NotNull
	@Column(name = "XRATEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal xrateRef;

	@NotNull
	@Column(name = "GENERATED", nullable = false)
	private Boolean generated;

	@NotNull
	@Column(name = "CONFIRMED", nullable = false)
	private Boolean confirmed;

	@NotNull
	@Column(name = "APPROVED", nullable = false)
	private Boolean approved;

	@NotNull
	@Column(name = "POSTED", nullable = false)
	private Boolean posted;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DocType.class)
	@JoinColumn(name = "DOCTYPE_ID", referencedColumnName = "ID")
	private DocType docType;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
	@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Org.class)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
	private Org company;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = FinancialAccount.class)
	@JoinColumn(name = "FINACCOUNT_ID", referencedColumnName = "ID")
	private FinancialAccount finAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpAccount.class)
	@JoinColumn(name = "BPACCOUNT_ID", referencedColumnName = "ID")
	private BpAccount bpAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BankAccount.class)
	@JoinColumn(name = "BPBANKACCOUNT_ID", referencedColumnName = "ID")
	private BankAccount bpBankAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpContact.class)
	@JoinColumn(name = "BPCONTACT_ID", referencedColumnName = "ID")
	private BpContact bpContact;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PaymentLine.class, mappedBy = "payment", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Collection<PaymentLine> lines;

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
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

	public String getSourceDocNo() {
		return this.sourceDocNo;
	}

	public void setSourceDocNo(String sourceDocNo) {
		this.sourceDocNo = sourceDocNo;
	}

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

	public BigDecimal getXrateLoc() {
		return this.xrateLoc;
	}

	public void setXrateLoc(BigDecimal xrateLoc) {
		this.xrateLoc = xrateLoc;
	}

	public BigDecimal getXrateRef() {
		return this.xrateRef;
	}

	public void setXrateRef(BigDecimal xrateRef) {
		this.xrateRef = xrateRef;
	}

	public Boolean getGenerated() {
		return this.generated;
	}

	public void setGenerated(Boolean generated) {
		this.generated = generated;
	}

	public Boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getApproved() {
		return this.approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getPosted() {
		return this.posted;
	}

	public void setPosted(Boolean posted) {
		this.posted = posted;
	}

	public DocType getDocType() {
		return this.docType;
	}

	public void setDocType(DocType docType) {
		if (docType != null) {
			this.__validate_client_context__(docType.getClientId());
		}
		this.docType = docType;
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

	public Org getCompany() {
		return this.company;
	}

	public void setCompany(Org company) {
		if (company != null) {
			this.__validate_client_context__(company.getClientId());
		}
		this.company = company;
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

	public BpAccount getBpAccount() {
		return this.bpAccount;
	}

	public void setBpAccount(BpAccount bpAccount) {
		if (bpAccount != null) {
			this.__validate_client_context__(bpAccount.getClientId());
		}
		this.bpAccount = bpAccount;
	}

	public BankAccount getBpBankAccount() {
		return this.bpBankAccount;
	}

	public void setBpBankAccount(BankAccount bpBankAccount) {
		if (bpBankAccount != null) {
			this.__validate_client_context__(bpBankAccount.getClientId());
		}
		this.bpBankAccount = bpBankAccount;
	}

	public BpContact getBpContact() {
		return this.bpContact;
	}

	public void setBpContact(BpContact bpContact) {
		if (bpContact != null) {
			this.__validate_client_context__(bpContact.getClientId());
		}
		this.bpContact = bpContact;
	}

	public Collection<PaymentLine> getLines() {
		return this.lines;
	}

	public void setLines(Collection<PaymentLine> lines) {
		this.lines = lines;
	}

	public void addToLines(PaymentLine e) {
		if (this.lines == null) {
			this.lines = new ArrayList<PaymentLine>();
		}
		e.setPayment(this);
		this.lines.add(e);
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.xrateLoc == null) {
			this.xrateLoc = new BigDecimal("1");
		}
		if (this.xrateRef == null) {
			this.xrateRef = new BigDecimal("1");
		}
		if (this.generated == null) {
			this.generated = new Boolean(false);
		}
		if (this.confirmed == null) {
			this.confirmed = new Boolean(false);
		}
		if (this.approved == null) {
			this.approved = new Boolean(false);
		}
		if (this.posted == null) {
			this.posted = new Boolean(false);
		}
	}
}
