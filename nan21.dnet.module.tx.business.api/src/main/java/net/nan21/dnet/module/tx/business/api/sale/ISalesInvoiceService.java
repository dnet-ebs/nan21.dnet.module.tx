/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

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
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;

/**
 * Interface to expose business functions specific for {@link SalesInvoice} domain
 * entity.
 */
public interface ISalesInvoiceService extends IEntityService<SalesInvoice> {

	public void doConfirm(SalesInvoice invoice) throws BusinessException;

	public void doUnConfirm(SalesInvoice invoice) throws BusinessException;

	public void doPost(SalesInvoice invoice) throws BusinessException;

	public void doUnPost(SalesInvoice invoice) throws BusinessException;

	public void doCopyLines(SalesInvoice target, String sourceId)
			throws BusinessException;

	public void calculateAmounts(String invoiceId) throws BusinessException;

	/**
	 * Find by unique key
	 */
	public SalesInvoice findByDocno(String docNo);

	/**
	 * Find by reference: docType
	 */
	public List<SalesInvoice> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<SalesInvoice> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: company
	 */
	public List<SalesInvoice> findByCompany(Org company);

	/**
	 * Find by ID of reference: company.id
	 */
	public List<SalesInvoice> findByCompanyId(String companyId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<SalesInvoice> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<SalesInvoice> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<SalesInvoice> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<SalesInvoice> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: org
	 */
	public List<SalesInvoice> findByOrg(Org org);

	/**
	 * Find by ID of reference: org.id
	 */
	public List<SalesInvoice> findByOrgId(String orgId);

	/**
	 * Find by reference: billToLocation
	 */
	public List<SalesInvoice> findByBillToLocation(Location billToLocation);

	/**
	 * Find by ID of reference: billToLocation.id
	 */
	public List<SalesInvoice> findByBillToLocationId(String billToLocationId);

	/**
	 * Find by reference: billToContact
	 */
	public List<SalesInvoice> findByBillToContact(BpContact billToContact);

	/**
	 * Find by ID of reference: billToContact.id
	 */
	public List<SalesInvoice> findByBillToContactId(String billToContactId);

	/**
	 * Find by reference: paymentMethod
	 */
	public List<SalesInvoice> findByPaymentMethod(DocType paymentMethod);

	/**
	 * Find by ID of reference: paymentMethod.id
	 */
	public List<SalesInvoice> findByPaymentMethodId(String paymentMethodId);

	/**
	 * Find by reference: paymentTerm
	 */
	public List<SalesInvoice> findByPaymentTerm(PaymentTerm paymentTerm);

	/**
	 * Find by ID of reference: paymentTerm.id
	 */
	public List<SalesInvoice> findByPaymentTermId(String paymentTermId);

	/**
	 * Find by reference: lines
	 */
	public List<SalesInvoice> findByLines(SalesInvoiceLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<SalesInvoice> findByLinesId(String linesId);

	/**
	 * Find by reference: taxes
	 */
	public List<SalesInvoice> findByTaxes(SalesInvoiceTax taxes);

	/**
	 * Find by ID of reference: taxes.id
	 */
	public List<SalesInvoice> findByTaxesId(String taxesId);
}
