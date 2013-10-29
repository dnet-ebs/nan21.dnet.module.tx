/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountBalance;

/**
 * Interface to expose business functions specific for {@link AmountBalance} domain
 * entity.
 */
public interface IAmountBalanceService extends IEntityService<AmountBalance> {

	/**
	 * Find by unique key
	 */
	public AmountBalance findByBalance(BpAccount bpAccount, Currency currency);

	/**
	 * Find by unique key
	 */
	public AmountBalance findByBalance(Long bpAccountId, Long currencyId);

	/**
	 * Find by reference: bpAccount
	 */
	public List<AmountBalance> findByBpAccount(BpAccount bpAccount);

	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<AmountBalance> findByBpAccountId(String bpAccountId);

	/**
	 * Find by reference: currency
	 */
	public List<AmountBalance> findByCurrency(Currency currency);

	/**
	 * Find by ID of reference: currency.id
	 */
	public List<AmountBalance> findByCurrencyId(String currencyId);
}
