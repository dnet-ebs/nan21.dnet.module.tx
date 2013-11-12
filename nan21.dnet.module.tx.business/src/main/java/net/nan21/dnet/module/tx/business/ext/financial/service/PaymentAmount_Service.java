/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.financial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.financial.IPaymentAmountService;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentAmount;

/**
 * Business extensions specific for {@link PaymentAmount} domain entity.
 * 
 */
public class PaymentAmount_Service extends
		net.nan21.dnet.module.tx.business.impl.financial.PaymentAmount_Service
		implements IPaymentAmountService {

	@Override
	protected void postInsert(List<PaymentAmount> list)
			throws BusinessException {
		this.calculateTxAmounts(list, true);
	}

	@Override
	protected void preDelete(List<PaymentAmount> list) throws BusinessException {
		this.calculateTxAmounts(list, false);
	}

	@Override
	protected void preDeleteByIds(List<Object> ids, Map<String, Object> context)
			throws BusinessException {
		this.calculateTxAmounts(this.findByIds(ids), false);
	}

	/**
	 * Update txAmounts from payment amount.
	 * 
	 * @param list
	 * @param add
	 * @throws BusinessException
	 */
	protected void calculateTxAmounts(List<PaymentAmount> list, boolean add)
			throws BusinessException {

		List<AmountOwed> amounts = new ArrayList<AmountOwed>();

		for (PaymentAmount payAmount : list) {
			AmountOwed amount = payAmount.getAmountOwed();
			if (amount != null) {
				if (add) {
					amount.setAmountPayed(amount.getAmountPayed().add(
							payAmount.getAmount()));
					amount.setAmountDue(amount.getAmountDue().subtract(
							payAmount.getAmount()));
					amounts.add(amount);
				} else {
					amount.setAmountPayed(amount.getAmountPayed().subtract(
							payAmount.getAmount()));
					amount.setAmountDue(amount.getAmountDue().add(
							payAmount.getAmount()));
					amounts.add(amount);
				}

			}
		}
		this.findEntityService(AmountOwed.class).update(amounts);
	}

}
