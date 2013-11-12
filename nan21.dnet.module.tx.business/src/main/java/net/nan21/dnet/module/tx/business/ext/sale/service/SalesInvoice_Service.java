/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.sale.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.base.PaymentTerm;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.financial.IAmountOwedService;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceService;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesInvoiceCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesInvoice_Bd;
import net.nan21.dnet.module.tx.domain.impl.financial.AmountOwed;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;

import org.joda.time.DateTime;

/**
 * Business extensions specific for {@link SalesInvoice} domain entity.
 * 
 */
public class SalesInvoice_Service extends
		net.nan21.dnet.module.tx.business.impl.sale.SalesInvoice_Service
		implements ISalesInvoiceService {

	/**
	 * Confirm document
	 */
	@Override
	public void doConfirm(SalesInvoice invoice) throws BusinessException {
		// validate invoice before confirm
		String msg = "Cannot confirm invoice `" + invoice.getDocNo() + "`.";
		Assert.notNull(invoice.getPaymentMethod(), msg
				+ "No payment method specified.");
		Assert.notNull(invoice.getPaymentTerm(), msg
				+ "No payment term specified.");

		IAmountOwedService amountSrv = (IAmountOwedService) this
				.findEntityService(AmountOwed.class);
		if (amountSrv.findBySalesInvoice(invoice).size() == 0) {
			List<AmountOwed> amounts = this.createOwedAmounts(invoice);
			amountSrv.insert(amounts);
		}
		invoice.setConfirmed(true);
		this.getEntityManager().merge(invoice);
	}

	/**
	 * Un-confirm ocument
	 */
	@Override
	public void doUnConfirm(SalesInvoice invoice) throws BusinessException {
		IAmountOwedService amountSrv = (IAmountOwedService) this
				.findEntityService(AmountOwed.class);
		List<AmountOwed> amounts = amountSrv.findBySalesInvoiceId(invoice
				.getId());
		amountSrv.deleteByIds(this.collectIds(amounts));
		invoice.setConfirmed(false);
		this.getEntityManager().merge(invoice);
	}

	/**
	 * Generate owed amounts
	 * 
	 * @param invoice
	 * @return
	 */
	protected List<AmountOwed> createOwedAmounts(SalesInvoice invoice) {
		PaymentTerm paymentTerm = invoice.getPaymentTerm();
		List<AmountOwed> result = new ArrayList<AmountOwed>();
		if (paymentTerm != null) {
			DateTime dueDate = new DateTime(invoice.getDocDate())
					.plusDays(paymentTerm.getDays());

			AmountOwed amount = new AmountOwed();
			amount.setBpAccount(invoice.getBpAccount());
			// amount.setCompany(invoice.getCompany());
			amount.setSalesInvoice(invoice);
			amount.setSale(true);
			amount.setCurrency(invoice.getCurrency());
			amount.setPaymentMethod(invoice.getPaymentMethod());
			amount.setDueDate(dueDate.toDate());
			amount.setAmountInitial(invoice.getAmount());

			amount.setAmountDue(invoice.getAmount());
			amount.setAmountPayed(new BigDecimal("0"));

			result.add(amount);
		}
		return result;
	}

	@Override
	public void doPost(SalesInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnPost(SalesInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doCopyLines(SalesInvoice target, String sourceId)
			throws BusinessException {
		this.getBusinessDelegate(SalesInvoiceCopyLines_Bd.class).copyLines(
				target, sourceId);
	}

	/**
	 * Calculate the document level amounts for the given invoice id.
	 */
	@Override
	public void calculateAmounts(String invoiceId) throws BusinessException {
		this.getBusinessDelegate(SalesInvoice_Bd.class)
				.calculateDocumentAmounts(invoiceId);
	}

	/**
	 * Pre-insert. Acquire a document number.
	 */
	@Override
	protected void preInsert(SalesInvoice e) throws BusinessException {
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

}
