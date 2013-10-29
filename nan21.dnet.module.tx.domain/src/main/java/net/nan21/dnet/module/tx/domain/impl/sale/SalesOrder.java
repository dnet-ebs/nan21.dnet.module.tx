/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.sale;

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
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.hibernate.validator.constraints.NotBlank;

@NamedQueries({@NamedQuery(name = SalesOrder.NQ_FIND_BY_DOCNO, query = "SELECT e FROM SalesOrder e WHERE e.clientId = :clientId and e.docNo = :docNo", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE))})
@Entity
@Table(name = SalesOrder.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(name = SalesOrder.TABLE_NAME
		+ "_UK1", columnNames = {"CLIENTID", "DOCNO"})})
public class SalesOrder extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_SO";

	private static final long serialVersionUID = -8865917134914502125L;
	/**
	 * Named query find by unique key: Docno.
	 */
	public static final String NQ_FIND_BY_DOCNO = "SalesOrder.findByDocno";

	@NotBlank
	@Column(name = "DOCNO", nullable = false, length = 255)
	private String docNo;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DOCDATE", nullable = false)
	private Date docDate;

	@NotNull
	@Column(name = "NETAMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal netAmount;

	@NotNull
	@Column(name = "TAXAMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmount;

	@NotNull
	@Column(name = "AMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal amount;

	@NotNull
	@Column(name = "XRATELOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal xrateLoc;

	@NotNull
	@Column(name = "NETAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal netAmountLoc;

	@NotNull
	@Column(name = "TAXAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountLoc;

	@NotNull
	@Column(name = "AMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountLoc;

	@NotNull
	@Column(name = "XRATEREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal xrateRef;

	@NotNull
	@Column(name = "NETAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal netAmountRef;

	@NotNull
	@Column(name = "TAXAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountRef;

	@NotNull
	@Column(name = "AMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountRef;

	@NotNull
	@Column(name = "CONFIRMED", nullable = false)
	private Boolean confirmed;

	@NotNull
	@Column(name = "INVOICED", nullable = false)
	private Boolean invoiced;

	@NotNull
	@Column(name = "DELIVERED", nullable = false)
	private Boolean delivered;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DocType.class)
	@JoinColumn(name = "DOCTYPE_ID", referencedColumnName = "ID")
	private DocType docType;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Org.class)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
	private Org company;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpAccount.class)
	@JoinColumn(name = "BPACCOUNT_ID", referencedColumnName = "ID")
	private BpAccount bpAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
	@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID")
	private Currency currency;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Org.class)
	@JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
	private Org org;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
	@JoinColumn(name = "BILLTOLOCATION_ID", referencedColumnName = "ID")
	private Location billToLocation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpContact.class)
	@JoinColumn(name = "BILLTOCONTACT_ID", referencedColumnName = "ID")
	private BpContact billToContact;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
	@JoinColumn(name = "SHIPTOLOCATION_ID", referencedColumnName = "ID")
	private Location shipToLocation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = BpContact.class)
	@JoinColumn(name = "SHIPTOCONTACT_ID", referencedColumnName = "ID")
	private BpContact shipToContact;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DocType.class)
	@JoinColumn(name = "PAYMENTMETHOD_ID", referencedColumnName = "ID")
	private DocType paymentMethod;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PaymentTerm.class)
	@JoinColumn(name = "PAYMENTTERM_ID", referencedColumnName = "ID")
	private PaymentTerm paymentTerm;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = SalesOrderLine.class, mappedBy = "order", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Collection<SalesOrderLine> lines;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = SalesOrderTax.class, mappedBy = "order", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Collection<SalesOrderTax> taxes;

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

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getXrateLoc() {
		return this.xrateLoc;
	}

	public void setXrateLoc(BigDecimal xrateLoc) {
		this.xrateLoc = xrateLoc;
	}

	public BigDecimal getNetAmountLoc() {
		return this.netAmountLoc;
	}

	public void setNetAmountLoc(BigDecimal netAmountLoc) {
		this.netAmountLoc = netAmountLoc;
	}

	public BigDecimal getTaxAmountLoc() {
		return this.taxAmountLoc;
	}

	public void setTaxAmountLoc(BigDecimal taxAmountLoc) {
		this.taxAmountLoc = taxAmountLoc;
	}

	public BigDecimal getAmountLoc() {
		return this.amountLoc;
	}

	public void setAmountLoc(BigDecimal amountLoc) {
		this.amountLoc = amountLoc;
	}

	public BigDecimal getXrateRef() {
		return this.xrateRef;
	}

	public void setXrateRef(BigDecimal xrateRef) {
		this.xrateRef = xrateRef;
	}

	public BigDecimal getNetAmountRef() {
		return this.netAmountRef;
	}

	public void setNetAmountRef(BigDecimal netAmountRef) {
		this.netAmountRef = netAmountRef;
	}

	public BigDecimal getTaxAmountRef() {
		return this.taxAmountRef;
	}

	public void setTaxAmountRef(BigDecimal taxAmountRef) {
		this.taxAmountRef = taxAmountRef;
	}

	public BigDecimal getAmountRef() {
		return this.amountRef;
	}

	public void setAmountRef(BigDecimal amountRef) {
		this.amountRef = amountRef;
	}

	public Boolean getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getInvoiced() {
		return this.invoiced;
	}

	public void setInvoiced(Boolean invoiced) {
		this.invoiced = invoiced;
	}

	public Boolean getDelivered() {
		return this.delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
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

	public Org getCompany() {
		return this.company;
	}

	public void setCompany(Org company) {
		if (company != null) {
			this.__validate_client_context__(company.getClientId());
		}
		this.company = company;
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

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		if (org != null) {
			this.__validate_client_context__(org.getClientId());
		}
		this.org = org;
	}

	public Location getBillToLocation() {
		return this.billToLocation;
	}

	public void setBillToLocation(Location billToLocation) {
		if (billToLocation != null) {
			this.__validate_client_context__(billToLocation.getClientId());
		}
		this.billToLocation = billToLocation;
	}

	public BpContact getBillToContact() {
		return this.billToContact;
	}

	public void setBillToContact(BpContact billToContact) {
		if (billToContact != null) {
			this.__validate_client_context__(billToContact.getClientId());
		}
		this.billToContact = billToContact;
	}

	public Location getShipToLocation() {
		return this.shipToLocation;
	}

	public void setShipToLocation(Location shipToLocation) {
		if (shipToLocation != null) {
			this.__validate_client_context__(shipToLocation.getClientId());
		}
		this.shipToLocation = shipToLocation;
	}

	public BpContact getShipToContact() {
		return this.shipToContact;
	}

	public void setShipToContact(BpContact shipToContact) {
		if (shipToContact != null) {
			this.__validate_client_context__(shipToContact.getClientId());
		}
		this.shipToContact = shipToContact;
	}

	public DocType getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(DocType paymentMethod) {
		if (paymentMethod != null) {
			this.__validate_client_context__(paymentMethod.getClientId());
		}
		this.paymentMethod = paymentMethod;
	}

	public PaymentTerm getPaymentTerm() {
		return this.paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		if (paymentTerm != null) {
			this.__validate_client_context__(paymentTerm.getClientId());
		}
		this.paymentTerm = paymentTerm;
	}

	public Collection<SalesOrderLine> getLines() {
		return this.lines;
	}

	public void setLines(Collection<SalesOrderLine> lines) {
		this.lines = lines;
	}

	public void addToLines(SalesOrderLine e) {
		if (this.lines == null) {
			this.lines = new ArrayList<SalesOrderLine>();
		}
		e.setOrder(this);
		this.lines.add(e);
	}

	public Collection<SalesOrderTax> getTaxes() {
		return this.taxes;
	}

	public void setTaxes(Collection<SalesOrderTax> taxes) {
		this.taxes = taxes;
	}

	public void addToTaxes(SalesOrderTax e) {
		if (this.taxes == null) {
			this.taxes = new ArrayList<SalesOrderTax>();
		}
		e.setOrder(this);
		this.taxes.add(e);
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.netAmount == null) {
			this.netAmount = new BigDecimal("0");
		}
		if (this.taxAmount == null) {
			this.taxAmount = new BigDecimal("0");
		}
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
		}
		if (this.xrateLoc == null) {
			this.xrateLoc = new BigDecimal("1");
		}
		if (this.netAmountLoc == null) {
			this.netAmountLoc = new BigDecimal("0");
		}
		if (this.taxAmountLoc == null) {
			this.taxAmountLoc = new BigDecimal("0");
		}
		if (this.amountLoc == null) {
			this.amountLoc = new BigDecimal("0");
		}
		if (this.xrateRef == null) {
			this.xrateRef = new BigDecimal("1");
		}
		if (this.netAmountRef == null) {
			this.netAmountRef = new BigDecimal("0");
		}
		if (this.taxAmountRef == null) {
			this.taxAmountRef = new BigDecimal("0");
		}
		if (this.amountRef == null) {
			this.amountRef = new BigDecimal("0");
		}
		if (this.confirmed == null) {
			this.confirmed = new Boolean(false);
		}
		if (this.invoiced == null) {
			this.invoiced = new Boolean(false);
		}
		if (this.delivered == null) {
			this.delivered = new Boolean(false);
		}
	}
}
