/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceTax;

/**
 * Interface to expose business functions specific for {@link PurchaseInvoiceTax} domain
 * entity.
 */
public interface IPurchaseInvoiceTaxService
		extends
			IEntityService<PurchaseInvoiceTax> {

	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceTax findByTax(PurchaseInvoice invoice, Tax tax);

	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceTax findByTax(Long invoiceId, Long taxId);

	/**
	 * Find by reference: invoice
	 */
	public List<PurchaseInvoiceTax> findByInvoice(PurchaseInvoice invoice);

	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<PurchaseInvoiceTax> findByInvoiceId(String invoiceId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceTax> findByTaxId(String taxId);
}
