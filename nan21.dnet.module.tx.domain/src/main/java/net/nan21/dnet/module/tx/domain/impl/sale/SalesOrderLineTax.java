/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.domain.impl.sale;

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
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

@NamedQueries({
		@NamedQuery(name = SalesOrderLineTax.NQ_FIND_BY_TAX, query = "SELECT e FROM SalesOrderLineTax e WHERE e.clientId = :clientId and e.line = :line and e.tax = :tax", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE)),
		@NamedQuery(name = SalesOrderLineTax.NQ_FIND_BY_TAX_PRIMITIVE, query = "SELECT e FROM SalesOrderLineTax e WHERE e.clientId = :clientId and e.line.id = :lineId and e.tax.id = :taxId", hints = @QueryHint(name = QueryHints.BIND_PARAMETERS, value = HintValues.TRUE))})
@Entity
@Table(name = SalesOrderLineTax.TABLE_NAME, uniqueConstraints = {@UniqueConstraint(name = SalesOrderLineTax.TABLE_NAME
		+ "_UK1", columnNames = {"CLIENTID", "LINE_ID", "TAX_ID"})})
public class SalesOrderLineTax extends AbstractAuditable {

	public static final String TABLE_NAME = "TX_SO_LINE_TAX";

	private static final long serialVersionUID = -8865917134914502125L;
	/**
	 * Named query find by unique key: Tax.
	 */
	public static final String NQ_FIND_BY_TAX = "SalesOrderLineTax.findByTax";
	/**
	 * Named query find by unique key: Tax using the ID field for references.
	 */
	public static final String NQ_FIND_BY_TAX_PRIMITIVE = "SalesOrderLineTax.findByTax_PRIMITIVE";

	@NotNull
	@Column(name = "BASEAMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal baseAmount;

	@NotNull
	@Column(name = "TAXAMOUNT", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmount;

	@NotNull
	@Column(name = "BASEAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal baseAmountLoc;

	@NotNull
	@Column(name = "TAXAMOUNTLOC", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountLoc;

	@NotNull
	@Column(name = "BASEAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal baseAmountRef;

	@NotNull
	@Column(name = "TAXAMOUNTREF", nullable = false, precision = 21, scale = 6)
	private BigDecimal taxAmountRef;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SalesOrderLine.class)
	@JoinColumn(name = "LINE_ID", referencedColumnName = "ID")
	private SalesOrderLine line;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tax.class)
	@JoinColumn(name = "TAX_ID", referencedColumnName = "ID")
	private Tax tax;

	public BigDecimal getBaseAmount() {
		return this.baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getBaseAmountLoc() {
		return this.baseAmountLoc;
	}

	public void setBaseAmountLoc(BigDecimal baseAmountLoc) {
		this.baseAmountLoc = baseAmountLoc;
	}

	public BigDecimal getTaxAmountLoc() {
		return this.taxAmountLoc;
	}

	public void setTaxAmountLoc(BigDecimal taxAmountLoc) {
		this.taxAmountLoc = taxAmountLoc;
	}

	public BigDecimal getBaseAmountRef() {
		return this.baseAmountRef;
	}

	public void setBaseAmountRef(BigDecimal baseAmountRef) {
		this.baseAmountRef = baseAmountRef;
	}

	public BigDecimal getTaxAmountRef() {
		return this.taxAmountRef;
	}

	public void setTaxAmountRef(BigDecimal taxAmountRef) {
		this.taxAmountRef = taxAmountRef;
	}

	public SalesOrderLine getLine() {
		return this.line;
	}

	public void setLine(SalesOrderLine line) {
		if (line != null) {
			this.__validate_client_context__(line.getClientId());
		}
		this.line = line;
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

	@PrePersist
	public void prePersist() {
		super.prePersist();
		if (this.baseAmount == null) {
			this.baseAmount = new BigDecimal("0");
		}
		if (this.taxAmount == null) {
			this.taxAmount = new BigDecimal("0");
		}
		if (this.baseAmountLoc == null) {
			this.baseAmountLoc = new BigDecimal("0");
		}
		if (this.taxAmountLoc == null) {
			this.taxAmountLoc = new BigDecimal("0");
		}
		if (this.baseAmountRef == null) {
			this.baseAmountRef = new BigDecimal("0");
		}
		if (this.taxAmountRef == null) {
			this.taxAmountRef = new BigDecimal("0");
		}
	}
}
