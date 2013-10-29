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
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderTax;

/**
 * Interface to expose business functions specific for {@link PurchaseOrder} domain
 * entity.
 */
public interface IPurchaseOrderService extends IEntityService<PurchaseOrder> {

	public void doConfirm(PurchaseOrder order) throws BusinessException;

	public void doUnConfirm(PurchaseOrder order) throws BusinessException;

	public void doCopyLines(PurchaseOrder target, String sourceId)
			throws BusinessException;

	public void calculateAmounts(String orderId) throws BusinessException;

	/**
	 * Find by unique key
	 */
	public PurchaseOrder findByDocno(String docNo);

	/**
	 * Find by reference: docType
	 */
	public List<PurchaseOrder> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<PurchaseOrder> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: company
	 */
	public List<PurchaseOrder> findByCompany(Org company);

	/**
	 * Find by ID of reference: company.id
	 */
	public List<PurchaseOrder> findByCompanyId(String companyId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<PurchaseOrder> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<PurchaseOrder> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<PurchaseOrder> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<PurchaseOrder> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: org
	 */
	public List<PurchaseOrder> findByOrg(Org org);

	/**
	 * Find by ID of reference: org.id
	 */
	public List<PurchaseOrder> findByOrgId(String orgId);

	/**
	 * Find by reference: lines
	 */
	public List<PurchaseOrder> findByLines(PurchaseOrderLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<PurchaseOrder> findByLinesId(String linesId);

	/**
	 * Find by reference: taxes
	 */
	public List<PurchaseOrder> findByTaxes(PurchaseOrderTax taxes);

	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<PurchaseOrder> findByTaxesId(String taxesId);
}
