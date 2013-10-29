/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import net.nan21.dnet.core.domain.impl.AbstractAuditable;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = PurchaseInvoiceLine.TABLE_NAME)
public class PurchaseInvoiceLine extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_PI_LINE";

	private static final long serialVersionUID = -8865917134914502125L;

	@NotNull
	@Column(name = "QUANTITY", nullable = false, precision = 21, scale = 6)
	private BigDecimal quantity;

	@NotNull
	@Column(name = "UNITPRICE", nullable = false, precision = 21, scale = 6)
	private BigDecimal unitPrice;

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
	@Column(name = "NETAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal netAmountLoc;

	@NotNull
	@Column(name = "TAXAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountLoc;

	@NotNull
	@Column(name = "AMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountLoc;

	@NotNull
	@Column(name = "NETAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal netAmountRef;

	@NotNull
	@Column(name = "TAXAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountRef;

	@NotNull
	@Column(name = "AMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal amountRef;

	@NotBlank
	@Column(name = "ENTRYMODE", nullable = false, length = 16)
	private String entryMode;

	@NotNull
	@Column(name = "USEGIVENTAX", nullable = false)
	private Boolean useGivenTax;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PurchaseInvoice.class)
	@JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID")
	private PurchaseInvoice invoice;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ProductAccount.class)
	@JoinColumn(name = "PRODUCTACCOUNT_ID", referencedColumnName = "ID")
	private ProductAccount productAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Uom.class)
	@JoinColumn(name = "UOM_ID", referencedColumnName = "ID")
	private Uom uom;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tax.class)
	@JoinColumn(name = "TAX_ID", referencedColumnName = "ID")
	private Tax tax;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PurchaseInvoiceLineTax.class, mappedBy = "line", cascade = CascadeType.ALL)
	@CascadeOnDelete
	private Collection<PurchaseInvoiceLineTax> lineTaxes;

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

	public String getEntryMode() {
		return this.entryMode;
	}

	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}

	public Boolean getUseGivenTax() {
		return this.useGivenTax;
	}

	public void setUseGivenTax(Boolean useGivenTax) {
		this.useGivenTax = useGivenTax;
	}

	public PurchaseInvoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(PurchaseInvoice invoice) {
		if (invoice != null) {
			this.__validate_client_context__(invoice.getClientId());
		}
		this.invoice = invoice;
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

	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		if (tax != null) {
			this.__validate_client_context__(tax.getClientId());
		}
		this.tax = tax;
	}

	public Collection<PurchaseInvoiceLineTax> getLineTaxes() {
		return this.lineTaxes;
	}

	public void setLineTaxes(Collection<PurchaseInvoiceLineTax> lineTaxes) {
		this.lineTaxes = lineTaxes;
	}

	public void addToLineTaxes(PurchaseInvoiceLineTax e) {
		if (this.lineTaxes == null) {
			this.lineTaxes = new ArrayList<PurchaseInvoiceLineTax>();
		}
		e.setLine(this);
		this.lineTaxes.add(e);
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
		if (this.netAmount == null) {
			this.netAmount = new BigDecimal("0");
		}
		if (this.taxAmount == null) {
			this.taxAmount = new BigDecimal("0");
		}
		if (this.amount == null) {
			this.amount = new BigDecimal("0");
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
		if (this.netAmountRef == null) {
			this.netAmountRef = new BigDecimal("0");
		}
		if (this.taxAmountRef == null) {
			this.taxAmountRef = new BigDecimal("0");
		}
		if (this.amountRef == null) {
			this.amountRef = new BigDecimal("0");
		}
		if (this.useGivenTax == null) {
			this.useGivenTax = new Boolean(false);
		}
	}
}
