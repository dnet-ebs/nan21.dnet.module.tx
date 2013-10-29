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
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentAmount;

/**
 * Repository functionality for {@link PaymentAmount} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PaymentAmount_Service extends AbstractEntityService<PaymentAmount> {

	public PaymentAmount_Service() {
		super();
	}

	public PaymentAmount_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PaymentAmount> getEntityClass() {
		return PaymentAmount.class;
	}
	/**
	 * Find by reference: payment
	 */
	public List<PaymentAmount> findByPayment(Payment payment) {
		return this.findByPaymentId(payment.getId());
	}
	/**
	 * Find by ID of reference: payment.id
	 */
	public List<PaymentAmount> findByPaymentId(String paymentId) {
		return (List<PaymentAmount>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentAmount e where e.clientId = :clientId and e.payment.id = :paymentId",
						PaymentAmount.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentId", paymentId).getResultList();
	}
	/**
	 * Find by reference: amountOwed
	 */
	public List<PaymentAmount> findByAmountOwed(AmountOwed amountOwed) {
		return this.findByAmountOwedId(amountOwed.getId());
	}
	/**
	 * Find by ID of reference: amountOwed.id
	 */
	public List<PaymentAmount> findByAmountOwedId(String amountOwedId) {
		return (List<PaymentAmount>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentAmount e where e.clientId = :clientId and e.amountOwed.id = :amountOwedId",
						PaymentAmount.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("amountOwedId", amountOwedId).getResultList();
	}
}
