/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceTax;

/**
 * Interface to expose business functions specific for {@link PurchaseInvoice} domain
 * entity.
 */
public interface IPurchaseInvoiceService
		extends
			IEntityService<PurchaseInvoice> {

	public void doConfirm(PurchaseInvoice invoice) throws BusinessException;

	public void doUnConfirm(PurchaseInvoice invoice) throws BusinessException;

	public void doPost(PurchaseInvoice invoice) throws BusinessException;

	public void doUnPost(PurchaseInvoice invoice) throws BusinessException;

	public void doCopyLines(PurchaseInvoice target, String sourceId)
			throws BusinessException;

	public void calculateAmounts(String invoiceId) throws BusinessException;

	/**
	 * Find by unique key
	 */
	public PurchaseInvoice findByDocno(String docNo);

	/**
	 * Find by reference: docType
	 */
	public List<PurchaseInvoice> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<PurchaseInvoice> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: company
	 */
	public List<PurchaseInvoice> findByCompany(Org company);

	/**
	 * Find by ID of reference: company.id
	 */
	public List<PurchaseInvoice> findByCompanyId(String companyId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<PurchaseInvoice> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<PurchaseInvoice> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<PurchaseInvoice> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<PurchaseInvoice> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: org
	 */
	public List<PurchaseInvoice> findByOrg(Org org);

	/**
	 * Find by ID of reference: org.id
	 */
	public List<PurchaseInvoice> findByOrgId(String orgId);

	/**
	 * Find by reference: lines
	 */
	public List<PurchaseInvoice> findByLines(PurchaseInvoiceLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<PurchaseInvoice> findByLinesId(String linesId);

	/**
	 * Find by reference: taxes
	 */
	public List<PurchaseInvoice> findByTaxes(PurchaseInvoiceTax taxes);

	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<PurchaseInvoice> findByTaxesId(String taxesId);
}
