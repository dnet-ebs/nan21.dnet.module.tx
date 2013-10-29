/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.sale.service;

import java.util.Date;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.module.bd.domain.impl.geo.Location;
import net.nan21.dnet.module.md.business.api.org.IDocSequenceValueService;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.md.domain.impl.bp.BpAccount;
import net.nan21.dnet.module.md.domain.impl.bp.CustomerGroup;
import net.nan21.dnet.module.md.domain.impl.org.DocSequenceValue;
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderService;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesOrderCopyLines_Bd;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesOrderToInvoice_Bd;
import net.nan21.dnet.module.tx.business.ext.sale.delegate.SalesOrder_Bd;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;

/**
 * Business extensions specific for {@link SalesOrder} domain entity.
 * 
 */
public class SalesOrder_Service extends
		net.nan21.dnet.module.tx.business.impl.sale.SalesOrder_Service
		implements ISalesOrderService {

	@Override
	public void doConfirm(SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnConfirm(SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doGenerateInvoice(String salesOrderId, String invDocTypeId)
			throws BusinessException {
		SalesOrder ord = this.findById(salesOrderId);
		this.getBusinessDelegate(SalesOrderToInvoice_Bd.class).generateInvoice(
				ord, invDocTypeId);
	}

	@Override
	public void doGenerateDelivery(String salesOrderId,
			String deliveryDocTypeId, Date delivEventDate)
			throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doCopyLines(SalesOrder target, String sourceId)
			throws BusinessException {
		this.getBusinessDelegate(SalesOrderCopyLines_Bd.class).copyLines(
				target, sourceId);
	}

	@Override
	public void calculateAmounts(String orderId) throws BusinessException {
		this.getBusinessDelegate(SalesOrder_Bd.class).calculateDocumentAmounts(
				orderId);
	}

	/**
	 * Pre-insert. Acquire a document number and provide some defaults.
	 */
	@Override
	protected void preInsert(SalesOrder e) throws BusinessException {
		IDocSequenceValueService srv = (IDocSequenceValueService) this
				.findEntityService(DocSequenceValue.class);
		DocSequenceValue seqVal = srv.getNextValue(e.getCompany().getId(), e
				.getDocType().getId());
		e.setDocNo(seqVal.getValue());
		seqVal.setUsed(true);
		this.getEntityManager().merge(seqVal);

		// add default billing / shipping address

		String eql = "select e from " + Location.class.getSimpleName() + " e "
				+ " where e.clientId = :clientId "
				+ "   and e.targetRefid = :bpRefId "
				+ "   and e.active = true "
				+ "   and (e.forBilling = true and e.forShipping = true )";
		List<Location> locs = this
				.getEntityManager()
				.createQuery(eql, Location.class)
				.setParameter("clientId", Session.user.get().getClientId())
				.setParameter("bpRefId",
						e.getBpAccount().getBpartner().getRefid())
				.getResultList();

		for (Location loc : locs) {
			if (e.getBillToLocation() == null && loc.getForBilling()) {
				e.setBillToLocation(loc);
			}
			if (e.getShipToLocation() == null && loc.getForShipping()) {
				e.setShipToLocation(loc);
			}
		}
		BpAccount bpa = e.getBpAccount();
		CustomerGroup cg = bpa.getCustGroup();
		if (e.getPaymentMethod() == null) {
			if (bpa.getCustPaymentMethod() != null) {
				e.setPaymentMethod(bpa.getCustPaymentMethod());
			} else {
				if (cg != null && cg.getPaymentMethod() != null) {
					e.setPaymentMethod(cg.getPaymentMethod());
				}
			}
		}

		if (e.getPaymentTerm() == null) {
			if (bpa.getCustPaymentTerm() != null) {
				e.setPaymentTerm(bpa.getCustPaymentTerm());
			} else {
				if (cg != null && cg.getPaymentTerm() != null) {
					e.setPaymentTerm(cg.getPaymentTerm());
				}
			}
		}
		super.preInsert(e);
	}

}
