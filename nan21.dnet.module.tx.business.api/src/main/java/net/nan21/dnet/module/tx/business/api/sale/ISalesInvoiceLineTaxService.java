/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;

/**
 * Interface to expose business functions specific for {@link SalesInvoiceLineTax} domain
 * entity.
 */
public interface ISalesInvoiceLineTaxService
		extends
			IEntityService<SalesInvoiceLineTax> {

	/**
	 * Find by unique key
	 */
	public SalesInvoiceLineTax findByTax(SalesInvoiceLine line, Tax tax);

	/**
	 * Find by unique key
	 */
	public SalesInvoiceLineTax findByTax(Long lineId, Long taxId);

	/**
	 * Find by reference: line
	 */
	public List<SalesInvoiceLineTax> findByLine(SalesInvoiceLine line);

	/**
	 * Find by ID of reference: line.id
	 */
	public List<SalesInvoiceLineTax> findByLineId(String lineId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceLineTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceLineTax> findByTaxId(String taxId);
}
