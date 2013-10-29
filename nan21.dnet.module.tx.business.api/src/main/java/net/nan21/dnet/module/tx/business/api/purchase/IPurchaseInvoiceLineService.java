/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLineTax;

/**
 * Interface to expose business functions specific for {@link PurchaseInvoiceLine} domain
 * entity.
 */
public interface IPurchaseInvoiceLineService
		extends
			IEntityService<PurchaseInvoiceLine> {

	/**
	 * Find by reference: invoice
	 */
	public List<PurchaseInvoiceLine> findByInvoice(PurchaseInvoice invoice);

	/**
	 * Find by ID of reference: invoice.id
	 */
	public List<PurchaseInvoiceLine> findByInvoiceId(String invoiceId);

	/**
	 * Find by reference: productAccount
	 */
	public List<PurchaseInvoiceLine> findByProductAccount(
			ProductAccount productAccount);

	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PurchaseInvoiceLine> findByProductAccountId(
			String productAccountId);

	/**
	 * Find by reference: uom
	 */
	public List<PurchaseInvoiceLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PurchaseInvoiceLine> findByUomId(String uomId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseInvoiceLine> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseInvoiceLine> findByTaxId(String taxId);

	/**
	 * Find by reference: lineTaxes
	 */
	public List<PurchaseInvoiceLine> findByLineTaxes(
			PurchaseInvoiceLineTax lineTaxes);

	/**
	 * Find by ID of reference: lineTaxes.id
	 */
	public List<PurchaseInvoiceLine> findByLineTaxesId(String lineTaxesId);
}
