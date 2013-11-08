/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;

/**
 * Interface to expose business functions specific for {@link AmountOwed} domain
 * entity.
 */
public interface IAmountOwedService extends IEntityService<AmountOwed> {

	/**
	 * Find by reference: bpAccount
	 */
	public List<AmountOwed> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<AmountOwed> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<AmountOwed> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<AmountOwed> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: salesOrder
	 */
	public List<AmountOwed> findBySalesOrder(SalesOrder salesOrder);

	/**
	 * Find by ID of reference: salesOrder.id
	 */
	public List<AmountOwed> findBySalesOrderId(String salesOrderId);

	/**
	 * Find by reference: salesInvoice
	 */
	public List<AmountOwed> findBySalesInvoice(SalesInvoice salesInvoice);

	/**
	 * Find by ID of reference: salesInvoice.id
	 */
	public List<AmountOwed> findBySalesInvoiceId(String salesInvoiceId);

	/**
	 * Find by reference: purchaseOrder
	 */
	public List<AmountOwed> findByPurchaseOrder(PurchaseOrder purchaseOrder);

	/**
	 * Find by ID of reference: purchaseOrder.id
	 */
	public List<AmountOwed> findByPurchaseOrderId(String purchaseOrderId);

	/**
	 * Find by reference: purchaseInvoice
	 */
	public List<AmountOwed> findByPurchaseInvoice(
			PurchaseInvoice purchaseInvoice);

	/**
	 * Find by ID of reference: purchaseInvoice.id
	 */
	public List<AmountOwed> findByPurchaseInvoiceId(String purchaseInvoiceId);

	/**
	 * Find by reference: paymentMethod
	 */
	public List<AmountOwed> findByPaymentMethod(DocType paymentMethod);

	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<AmountOwed> findByPaymentMethodId(String paymentMethodId);
}
