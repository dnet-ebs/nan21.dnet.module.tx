/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.financial.service;

import java.util.List;

import org.springframework.util.Assert;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.financial.IPaymentService;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentAmount;

/**
 * Business extensions specific for {@link Payment} domain entity.
 * 
 */
public class Payment_Service extends
		net.nan21.dnet.module.tx.business.impl.financial.Payment_Service
		implements IPaymentService {

	@Override
	public void doConfirm(Payment payment) throws BusinessException {
		payment.setConfirmed(true);
		this.getEntityManager().merge(payment);
	}

	@Override
	public void doUnConfirm(Payment payment) throws BusinessException {

		payment.setConfirmed(false);
		this.getEntityManager().merge(payment);
	}

	@Override
	public void doPost(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnPost(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doRemoveAmounts(Payment payment) throws BusinessException {
		List<PaymentAmount> paymentAmounts = (List<PaymentAmount>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentAmount e left join fetch e.amountOwed where e.clientId = :pClientId and e.payment.id = :pPaymentId",
						PaymentAmount.class)
				.setParameter("pClientId", Session.user.get().getClientId())
				.setParameter("pPaymentId", payment.getId()).getResultList();

		this.findEntityService(PaymentAmount.class).delete(paymentAmounts);
	}

	/**
	 * Pre-insert. Acquire a document number.
	 */
	@Override
	protected void preInsert(Payment e) throws BusinessException {
		Assert.notNull(e.getCompany(), "A payment must belong to a company!");
		Assert.notNull(e.getDocType(), "Specify a document type for payment!");
		if (e.getDocNo() == null || "".equals(e.getDocNo())) {
			IDocSequenceValueService srv = (IDocSequenceValueService) this
					.findEntityService(DocSequenceValue.class);
			DocSequenceValue seqVal = srv.getNextValue(e.getCompany().getId(),
					e.getDocType().getId());
			e.setDocNo(seqVal.getValue());
			seqVal.setUsed(true);
			this.getEntityManager().merge(seqVal);
		}
		super.preInsert(e);
	}

}
