/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.purchase.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.util.Assert;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.financial.IAmountOwedService;
import net.nan21.dnet.module.tx.business.api.financial.IPaymentService;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceService;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseInvoiceCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseInvoice_Bd;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;

/**
 * Business extensions specific for {@link PurchaseInvoice} domain entity.
 */
public class PurchaseInvoice_Service extends
		net.nan21.dnet.module.tx.business.impl.purchase.PurchaseInvoice_Service
		implements IPurchaseInvoiceService {

	@Override
	public void doConfirm(PurchaseInvoice invoice) throws BusinessException {

		String msg = "Cannot confirm invoice `" + invoice.getDocNo() + "`.";
		Assert.notNull(invoice.getPaymentMethod(), msg
				+ "No payment method specified.");
		Assert.notNull(invoice.getPaymentTerm(), msg
				+ "No payment term specified.");

		IAmountOwedService amountSrv = (IAmountOwedService) this
				.findEntityService(AmountOwed.class);

		List<AmountOwed> amounts = null;

		// create tx-amounts
		if (amountSrv.findByPurchaseInvoice(invoice).size() == 0) {
			amounts = this.createOwedAmounts(invoice);
			amountSrv.insert(amounts);
		}

		// create payment
		if (invoice.getSelfPayed().booleanValue() == true) {

			// if (invoice.getFromAccount() == null) {
			// throw new BusinessException(
			// "Financial account must be specified for self-payed document `"
			// + invoice.getCode() + "`");
			// }
			//
			// IPaymentService paymentService = (IPaymentService) this
			// .findEntityService(Payment.class);
			//
			// IPaymentOutAmountService paymentAmountsService =
			// (IPaymentOutAmountService) this
			// .findEntityService(PaymentOutAmount.class);
			//
			// List<PaymentOutAmount> paymentAmounts = new
			// ArrayList<PaymentOutAmount>();
			//
			// PaymentOut payment = this.createPayment(invoice);
			// paymentService.insert(payment);
			//
			// PaymentOutAmount pa = new PaymentOutAmount();
			// pa.setPayment(payment);
			// pa.setAmount(payment.getAmount());
			// pa.setTxAmount(txAmounts.get(0));
			// paymentAmounts.add(pa);
			//
			// paymentAmountsService.insert(paymentAmounts);
			//
			// payment.setConfirmed(true);
			// paymentService.update(payment);

		}
		invoice.setConfirmed(true);
		this.getEntityManager().merge(invoice);
	}

	/**
	 * Un-confirm document. Undo generated operations on confirm.
	 */
	@Override
	public void doUnConfirm(PurchaseInvoice invoice) throws BusinessException {
		IAmountOwedService amountSrv = (IAmountOwedService) this
				.findEntityService(AmountOwed.class);
		List<AmountOwed> amounts = amountSrv.findByPurchaseInvoiceId(invoice
				.getId());
		List<Object> amountIds = this.collectIds(amounts);
		amountSrv.deleteByIds(amountIds);

		// remove generated payment
		if (invoice.getSelfPayed().booleanValue() == true) {
			IPaymentService paymentService = (IPaymentService) this
					.findEntityService(Payment.class);
			List<Payment> payments = this
					.getEntityManager()
					.createQuery(
							"select p from Payment p where p.id in (select e.payment.id from PaymentAmount e where e.amount.id in :pIds ) ",
							Payment.class).setParameter("pIds", amountIds)
					.getResultList();

			for (Payment payment : payments) {
				paymentService.doUnConfirm(payment);
			}
			paymentService.deleteByIds(this.collectIds(payments));
		}

		invoice.setConfirmed(false);
		this.getEntityManager().merge(invoice);
	}

	@Override
	public void doPost(PurchaseInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doUnPost(PurchaseInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doCopyLines(PurchaseInvoice target, String sourceId)
			throws BusinessException {
		this.getBusinessDelegate(PurchaseInvoiceCopyLines_Bd.class).copyLines(
				target, sourceId);
	}

	@Override
	public void calculateAmounts(String invoiceId) throws BusinessException {
		this.getBusinessDelegate(PurchaseInvoice_Bd.class)
				.calculateDocumentAmounts(invoiceId);
	}

	/**
	 * Pre-insert. Acquire a document number.
	 */
	@Override
	protected void preInsert(PurchaseInvoice e) throws BusinessException {
		Assert.notNull(e.getCompany(), "An invoice must belong to a company!");
		Assert.notNull(e.getDocType(), "Specify a document type for invoice!");
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

	protected List<AmountOwed> createOwedAmounts(PurchaseInvoice invoice) {
		PaymentTerm paymentTerm = invoice.getPaymentTerm();
		List<AmountOwed> result = new ArrayList<AmountOwed>();

		AmountOwed amount = new AmountOwed();

		amount.setBpAccount(invoice.getBpAccount());
		amount.setPurchaseInvoice(invoice);
		amount.setCurrency(invoice.getCurrency());
		amount.setSale(false);
		amount.setAmountInitial(invoice.getAmount());
		amount.setAmountDue(invoice.getAmount());
		amount.setAmountPayed(new BigDecimal("0"));

		if (paymentTerm != null) {
			DateTime dueDate = new DateTime(invoice.getDocDate())
					.plusDays(paymentTerm.getDays());
			amount.setPaymentMethod(invoice.getPaymentMethod());
			amount.setDueDate(dueDate.toDate());
		} else {
			amount.setDueDate(invoice.getDocDate());
		}

		result.add(amount);
		return result;
	}

}
