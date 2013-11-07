/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.sale.service;

import org.springframework.util.Assert;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceService;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesInvoiceCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesInvoice_Bd;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;

/**
 * Business extensions specific for {@link SalesInvoice} domain entity.
 * 
 */
public class SalesInvoice_Service extends
		net.nan21.dnet.module.tx.business.impl.sale.SalesInvoice_Service
		implements ISalesInvoiceService {

	@Override
	public void doConfirm(SalesInvoice invoice) throws BusinessException {
		invoice.setConfirmed(true);
		this.getEntityManager().merge(invoice);
	}

	@Override
	public void doUnConfirm(SalesInvoice invoice) throws BusinessException {
		invoice.setConfirmed(false);
		this.getEntityManager().merge(invoice);
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
		if (e.getDocNo() == null) {
			Assert.notNull(e.getCompany(),
					"An invoice must belong to a company!");
			Assert.notNull(e.getDocType(),
					"Specify a document type for invoice!");

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
