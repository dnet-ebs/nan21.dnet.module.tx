/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.org.FinancialAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.FinancialOperation;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;

/**
 * Interface to expose business functions specific for {@link FinancialOperation} domain
 * entity.
 */
public interface IFinancialOperationService
		extends
			IEntityService<FinancialOperation> {

	/**
	 * Find by reference: finAccount
	 */
	public List<FinancialOperation> findByFinAccount(FinancialAccount finAccount);

	/**
	 * Find by ID of reference: finAccount.id
	 */
	public List<FinancialOperation> findByFinAccountId(String finAccountId);

	/**
	 * Find by reference: payment
	 */
	public List<FinancialOperation> findByPayment(Payment payment);

	/**
	 * Find by ID of reference: payment.id
	 */
	public List<FinancialOperation> findByPaymentId(String paymentId);
}
