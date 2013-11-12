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
import net.nan21.dnet.module.md.domain.impl.org.FinancialAccount;
import net.nan21.dnet.module.tx.business.api.financial.IFinancialOperationService;
import net.nan21.dnet.module.tx.domain.impl.financial.FinancialOperation;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;

/**
 * Repository functionality for {@link FinancialOperation} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class FinancialOperation_Service
		extends
			AbstractEntityService<FinancialOperation>
		implements
			IFinancialOperationService {

	public FinancialOperation_Service() {
		super();
	}

	public FinancialOperation_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<FinancialOperation> getEntityClass() {
		return FinancialOperation.class;
	}
	/**
	 * Find by reference: finAccount
	 */
	public List<FinancialOperation> findByFinAccount(FinancialAccount finAccount) {
		return this.findByFinAccountId(finAccount.getId());
	}
	/**
	 * Find by ID of reference: finAccount.id
	 */
	public List<FinancialOperation> findByFinAccountId(String finAccountId) {
		return (List<FinancialOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from FinancialOperation e where e.clientId = :clientId and e.finAccount.id = :finAccountId",
						FinancialOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("finAccountId", finAccountId).getResultList();
	}
	/**
	 * Find by reference: payment
	 */
	public List<FinancialOperation> findByPayment(Payment payment) {
		return this.findByPaymentId(payment.getId());
	}
	/**
	 * Find by ID of reference: payment.id
	 */
	public List<FinancialOperation> findByPaymentId(String paymentId) {
		return (List<FinancialOperation>) this
				.getEntityManager()
				.createQuery(
						"select e from FinancialOperation e where e.clientId = :clientId and e.payment.id = :paymentId",
						FinancialOperation.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentId", paymentId).getResultList();
	}
}
