/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.financial;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentLine;

/**
 * Interface to expose business functions specific for {@link PaymentLine} domain
 * entity.
 */
public interface IPaymentLineService extends IEntityService<PaymentLine> {

	/**
	 * Find by reference: payment
	 */
	public List<PaymentLine> findByPayment(Payment payment);

	/**
	 * Find by ID of reference: payment.id
	 */
	public List<PaymentLine> findByPaymentId(String paymentId);

	/**
	 * Find by reference: productAccount
	 */
	public List<PaymentLine> findByProductAccount(ProductAccount productAccount);

	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PaymentLine> findByProductAccountId(String productAccountId);

	/**
	 * Find by reference: uom
	 */
	public List<PaymentLine> findByUom(Uom uom);

	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PaymentLine> findByUomId(String uomId);
}
