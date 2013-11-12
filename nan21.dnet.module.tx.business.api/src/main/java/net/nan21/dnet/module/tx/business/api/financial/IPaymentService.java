/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.base.BankAccount;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.BpContact;
import net.nan21.dnet.module.md.domain.impl.org.FinancialAccount;
import net.nan21.dnet.module.md.domain.impl.org.Org;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentLine;

/**
 * Interface to expose business functions specific for {@link Payment} domain
 * entity.
 */
public interface IPaymentService extends IEntityService<Payment> {

	public void doConfirm(Payment payment) throws BusinessException;

	public void doUnConfirm(Payment payment) throws BusinessException;

	public void doPost(Payment payment) throws BusinessException;

	public void doUnPost(Payment payment) throws BusinessException;

	public void doRemoveAmounts(Payment payment) throws BusinessException;

	/**
	 * Find by unique key
	 */
	public Payment findByDocno(String docNo);

	/**
	 * Find by reference: docType
	 */
	public List<Payment> findByDocType(DocType docType);

	/**
	 * Find by ID of reference: docType.id
	 */
	public List<Payment> findByDocTypeId(String docTypeId);

	/**
	 * Find by reference: currency
	 */
	public List<Payment> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<Payment> findByCurrencyId(String currencyId);

	/**
	 * Find by reference: company
	 */
	public List<Payment> findByCompany(Org company);

	/**
	 * Find by ID of reference: company.id
	 */
	public List<Payment> findByCompanyId(String companyId);

	/**
	 * Find by reference: finAccount
	 */
	public List<Payment> findByFinAccount(FinancialAccount finAccount);

	/**
	 * Find by ID of reference: finAccount.id
	 */
	public List<Payment> findByFinAccountId(String finAccountId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<Payment> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<Payment> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: bpBankAccount
	 */
	public List<Payment> findByBpBankAccount(BankAccount bpBankAccount);

	/**
	 * Find by ID of reference: bpBankAccount.id
	 */
	public List<Payment> findByBpBankAccountId(String bpBankAccountId);

	/**
	 * Find by reference: bpContact
	 */
	public List<Payment> findByBpContact(BpContact bpContact);

	/**
	 * Find by ID of reference: bpContact.id
	 */
	public List<Payment> findByBpContactId(String bpContactId);

	/**
	 * Find by reference: lines
	 */
	public List<Payment> findByLines(PaymentLine lines);

	/**
	 * Find by ID of reference: lines.id
	 */
	public List<Payment> findByLinesId(String linesId);
}
