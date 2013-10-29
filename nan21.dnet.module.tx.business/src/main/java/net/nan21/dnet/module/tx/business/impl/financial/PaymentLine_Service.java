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
import net.nan21.dnet.module.bd.domain.impl.uom.Uom;
import net.nan21.dnet.module.md.domain.impl.mm.ProductAccount;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.financial.PaymentLine;

/**
 * Repository functionality for {@link PaymentLine} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class PaymentLine_Service extends AbstractEntityService<PaymentLine> {

	public PaymentLine_Service() {
		super();
	}

	public PaymentLine_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<PaymentLine> getEntityClass() {
		return PaymentLine.class;
	}
	/**
	 * Find by reference: payment
	 */
	public List<PaymentLine> findByPayment(Payment payment) {
		return this.findByPaymentId(payment.getId());
	}
	/**
	 * Find by ID of reference: payment.id
	 */
	public List<PaymentLine> findByPaymentId(String paymentId) {
		return (List<PaymentLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentLine e where e.clientId = :clientId and e.payment.id = :paymentId",
						PaymentLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("paymentId", paymentId).getResultList();
	}
	/**
	 * Find by reference: productAccount
	 */
	public List<PaymentLine> findByProductAccount(ProductAccount productAccount) {
		return this.findByProductAccountId(productAccount.getId());
	}
	/**
	 * Find by ID of reference: productAccount.id
	 */
	public List<PaymentLine> findByProductAccountId(String productAccountId) {
		return (List<PaymentLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentLine e where e.clientId = :clientId and e.productAccount.id = :productAccountId",
						PaymentLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("productAccountId", productAccountId)
				.getResultList();
	}
	/**
	 * Find by reference: uom
	 */
	public List<PaymentLine> findByUom(Uom uom) {
		return this.findByUomId(uom.getId());
	}
	/**
	 * Find by ID of reference: uom.id
	 */
	public List<PaymentLine> findByUomId(String uomId) {
		return (List<PaymentLine>) this
				.getEntityManager()
				.createQuery(
						"select e from PaymentLine e where e.clientId = :clientId and e.uom.id = :uomId",
						PaymentLine.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("uomId", uomId).getResultList();
	}
}
