/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;

/**
 * Interface to expose business functions specific for {@link SalesInvoiceTax} domain
 * entity.
 */
public interface ISalesInvoiceTaxService
		extends
			IEntityService<SalesInvoiceTax> {

	/**
	 * Find by unique key
	 */
	public SalesInvoiceTax findByTax(SalesInvoice invoice, Tax tax);

	/**
	 * Find by unique key
	 */
	public SalesInvoiceTax findByTax(Long invoiceId, Long taxId);

	/**
	 * Find by reference: invoice
	 */
	public List<SalesInvoiceTax> findByInvoice(SalesInvoice invoice);

	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<SalesInvoiceTax> findByInvoiceId(String invoiceId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceTax> findByTaxId(String taxId);
}
