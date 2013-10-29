/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentAmount;

/**
 * Interface to expose business functions specific for {@link PaymentAmount} domain
 * entity.
 */
public interface IPaymentAmountService extends IEntityService<PaymentAmount> {

	/**
	 * Find by reference: payment
	 */
	public List<PaymentAmount> findByPayment(Payment payment);

	/**
	 * Find by ID of reference: payment.id
	 */
	public List<PaymentAmount> findByPaymentId(String paymentId);

	/**
	 * Find by reference: amountOwed
	 */
	public List<PaymentAmount> findByAmountOwed(AmountOwed amountOwed);

	/**
	 * Find by ID of reference: amountOwed.id
	 */
	public List<PaymentAmount> findByAmountOwedId(String amountOwedId);
}
