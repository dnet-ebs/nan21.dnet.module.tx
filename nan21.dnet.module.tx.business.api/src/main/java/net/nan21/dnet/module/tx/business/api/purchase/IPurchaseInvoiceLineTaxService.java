/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;

/**
 * Interface to expose business functions specific for {@link PurchaseInvoiceLineTax} domain
 * entity.
 */
public interface IPurchaseInvoiceLineTaxService
		extends
			IEntityService<PurchaseInvoiceLineTax> {

	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceLineTax findByTax(PurchaseInvoiceLine line, Tax tax);

	/**
	 * Find by unique key
	 */
	public PurchaseInvoiceLineTax findByTax(Long lineId, Long taxId);

	/**
	 * Find by reference: line
	 */
	public List<PurchaseInvoiceLineTax> findByLine(PurchaseInvoiceLine line);

	/**
	 * Find by ID of reference: line.id
	 */
	public List<PurchaseInvoiceLineTax> findByLineId(String lineId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceLineTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceLineTax> findByTaxId(String taxId);
}
