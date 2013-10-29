/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.Date;
import java.util.List;
import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderTax;

/**
 * Interface to expose business functions specific for {@link SalesOrder} domain
 * entity.
 */
public interface ISalesOrderService extends IEntityService<SalesOrder> {

	public void doConfirm(SalesOrder order) throws BusinessException;

	public void doUnConfirm(SalesOrder order) throws BusinessException;

	public void doGenerateInvoice(String salesOrderId, String invDocTypeId)
			throws BusinessException;

	public void doGenerateDelivery(String salesOrderId,
			String deliveryDocTypeId, Date deliveryDate)
			throws BusinessException;

	public void doCopyLines(SalesOrder target, String sourceId)
			throws BusinessException;

	public void calculateAmounts(String orderId) throws BusinessException;

	/**
	 * Find by unique key
	 */
	public SalesOrder findByDocno(String docNo);

	/**
	 * Find by reference: docType
	 */
	public List<SalesOrder> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<SalesOrder> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: company
	 */
	public List<SalesOrder> findByCompany(Org company);

	/**
	 * Find by ID of reference: company.id
	 */
	public List<SalesOrder> findByCompanyId(String companyId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<SalesOrder> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<SalesOrder> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<SalesOrder> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<SalesOrder> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: org
	 */
	public List<SalesOrder> findByOrg(Org org);

	/**
	 * Find by ID of reference: org.id
	 */
	public List<SalesOrder> findByOrgId(String orgId);

	/**
	 * Find by reference: billToLocation
	 */
	public List<SalesOrder> findByBillToLocation(Location billToLocation);

	/**
	 * Find by ID of reference: billToLocation.id
	 */
	public List<SalesOrder> findByBillToLocationId(String billToLocationId);

	/**
	 * Find by reference: billToContact
	 */
	public List<SalesOrder> findByBillToContact(BpContact billToContact);

	/**
	 * Find by ID of reference: billToContact.id
	 */
	public List<SalesOrder> findByBillToContactId(String billToContactId);

	/**
	 * Find by reference: shipToLocation
	 */
	public List<SalesOrder> findByShipToLocation(Location shipToLocation);

	/**
	 * Find by ID of reference: shipToLocation.id
	 */
	public List<SalesOrder> findByShipToLocationId(String shipToLocationId);

	/**
	 * Find by reference: shipToContact
	 */
	public List<SalesOrder> findByShipToContact(BpContact shipToContact);

	/**
	 * Find by ID of reference: shipToContact.id
	 */
	public List<SalesOrder> findByShipToContactId(String shipToContactId);

	/**
	 * Find by reference: paymentMethod
	 */
	public List<SalesOrder> findByPaymentMethod(DocType paymentMethod);

	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<SalesOrder> findByPaymentMethodId(String paymentMethodId);

	/**
	 * Find by reference: paymentTerm
	 */
	public List<SalesOrder> findByPaymentTerm(PaymentTerm paymentTerm);

	/**
	 * Find by ID of reference: paymentTerm.id
	 */
	public List<SalesOrder> findByPaymentTermId(String paymentTermId);

	/**
	 * Find by reference: lines
	 */
	public List<SalesOrder> findByLines(SalesOrderLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<SalesOrder> findByLinesId(String linesId);

	/**
	 * Find by reference: taxes
	 */
	public List<SalesOrder> findByTaxes(SalesOrderTax taxes);

	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<SalesOrder> findByTaxesId(String taxesId);
}
