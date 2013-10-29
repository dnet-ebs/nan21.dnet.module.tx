/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.financial;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.bd.domain.impl.currency.Currency;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.tx.business.api.financial.IAmountBalanceService;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountBalance;

/**
 * Repository functionality for {@link AmountBalance} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class AmountBalance_Service extends AbstractEntityService<AmountBalance>
		implements
			IAmountBalanceService {

	public AmountBalance_Service() {
		super();
	}

	public AmountBalance_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<AmountBalance> getEntityClass() {
		return AmountBalance.class;
	}
	/**
	 * Find by unique key
	 */
	public AmountBalance findByBalance(BpAccount bpAccount, Currency currency) {
		return (AmountBalance) this
				.getEntityManager()
				.createNamedQuery(AmountBalance.NQ_FIND_BY_BALANCE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccount", bpAccount)
				.setParameter("currency", currency).getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public AmountBalance findByBalance(Long bpAccountId, Long currencyId) {
		return (AmountBalance) this
				.getEntityManager()
				.createNamedQuery(AmountBalance.NQ_FIND_BY_BALANCE_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId)
				.setParameter("currencyId", currencyId).getSingleResult();
	}
	/**
	 * Find by reference: bpAccount
	 */
	public List<AmountBalance> findByBpAccount(BpAccount bpAccount) {
		return this.findByBpAccountId(bpAccount.getId());
	}
	/**
	 * Find by ID of reference: bpAccount.id
	 */
	public List<AmountBalance> findByBpAccountId(String bpAccountId) {
		return (List<AmountBalance>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountBalance e where e.clientId = :clientId and e.bpAccount.id = :bpAccountId",
						AmountBalance.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("bpAccountId", bpAccountId).getResultList();
	}
	/**
	 * Find by reference: currency
	 */
	public List<AmountBalance> findByCurrency(Currency currency) {
		return this.findByCurrencyId(currency.getId());
	}
	/**
	 * Find by ID of reference: currency.id
	 */
	public List<AmountBalance> findByCurrencyId(String currencyId) {
		return (List<AmountBalance>) this
				.getEntityManager()
				.createQuery(
						"select e from AmountBalance e where e.clientId = :clientId and e.currency.id = :currencyId",
						AmountBalance.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("currencyId", currencyId).getResultList();
	}
}
