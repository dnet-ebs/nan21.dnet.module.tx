/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;

/**
 * Interface to expose business functions specific for {@link SalesInvoiceLine} domain
 * entity.
 */
public interface ISalesInvoiceLineService
		extends
			IEntityService<SalesInvoiceLine> {

	/**
	 * Find by reference: invoice
	 */
	public List<SalesInvoiceLine> findByInvoice(SalesInvoice invoice);

	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<SalesInvoiceLine> findByInvoiceId(String invoiceId);

	/**
	 * Find by reference: productAccount
	 */
	public List<SalesInvoiceLine> findByProductAccount(
			ProductAccount productAccount);

	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<SalesInvoiceLine> findByProductAccountId(String productAccountId);

	/**
	 * Find by reference: uom
	 */
	public List<SalesInvoiceLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<SalesInvoiceLine> findByUomId(String uomId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceLine> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceLine> findByTaxId(String taxId);

	/**
	 * Find by reference: lineTaxes
	 */
	public List<SalesInvoiceLine> findByLineTaxes(SalesInvoiceLineTax lineTaxes);

	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<SalesInvoiceLine> findByLineTaxesId(String lineTaxesId);
}
