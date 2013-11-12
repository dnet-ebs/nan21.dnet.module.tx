/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.purchase.service;

import org.springframework.util.Assert;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderService;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseOrderCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.purchase.delegate.PurchaseOrder_Bd;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;

/**
 * Business extensions specific for {@link PurchaseOrder} domain entity.
 * 
 */
public class PurchaseOrder_Service extends
		net.nan21.dnet.module.tx.business.impl.purchase.PurchaseOrder_Service
		implements IPurchaseOrderService {

	@Override
	public void doConfirm(PurchaseOrder order) throws BusinessException {
		order.setConfirmed(true);
		this.getEntityManager().merge(order);
	}

	@Override
	public void doUnConfirm(PurchaseOrder order) throws BusinessException {
		order.setConfirmed(false);
		this.getEntityManager().merge(order);
	}

	@Override
	public void doCopyLines(PurchaseOrder target, String sourceId)
			throws BusinessException {
		this.getBusinessDelegate(PurchaseOrderCopyLines_Bd.class).copyLines(
				target, sourceId);
	}

	@Override
	public void calculateAmounts(String orderId) throws BusinessException {
		this.getBusinessDelegate(PurchaseOrder_Bd.class)
				.calculateDocumentAmounts(orderId);
	}

	/**
	 * Pre-insert. Acquire a document number.
	 */
	@Override
	protected void preInsert(PurchaseOrder e) throws BusinessException {
		Assert.notNull(e.getCompany(), "An order must belong to a company!");
		Assert.notNull(e.getDocType(), "Specify a document type for order!");
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
