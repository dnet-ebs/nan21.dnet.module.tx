/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.purchase.service;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceService;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseInvoiceCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseInvoice_Bd;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;

/**
 * Business extensions specific for {@link PurchaseInvoice} domain entity.
 * 
 */
public class PurchaseInvoice_Service extends
		net.nan21.dnet.module.tx.business.impl.purchase.PurchaseInvoice_Service
		implements IPurchaseInvoiceService {

	@Override
	public void doConfirm(PurchaseInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnConfirm(PurchaseInvoice invoice) throws BusinessException {
		// TODO Auto-generated method stub

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
		if (e.getDocNo() == null) {
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
