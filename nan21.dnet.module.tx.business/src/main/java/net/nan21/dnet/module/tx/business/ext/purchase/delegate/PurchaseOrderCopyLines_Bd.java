package net.nan21.dnet.module.tx.business.ext.purchase.delegate;

import java.util.ArrayList;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderLineService;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseOrderService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrder;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;

public class PurchaseOrderCopyLines_Bd extends AbstractBusinessDelegate {

	IPurchaseOrderLineService lineService;
	IPurchaseOrderService service;

	/**
	 * Create lines for the target order by copying them from the source order
	 * with the given ID
	 * 
	 * @param target
	 * @param sourceCode
	 * @throws BusinessException
	 */
	public void copyLines(PurchaseOrder target, String sourceId)
			throws BusinessException {
		if (sourceId == null) {
			throw new BusinessException(
					"Cannot copy lines. No source order specified.");
		}
		PurchaseOrder source = this.getPurchaseOrderService()
				.findById(sourceId);
		copyLines(target, source);
	}

	/**
	 * Create lines for the target order by copying them from the source
	 * 
	 * @param target
	 * @param source
	 * @throws BusinessException
	 */
	public void copyLines(PurchaseOrder target, PurchaseOrder source)
			throws BusinessException {
		if (source == null) {
			throw new BusinessException(
					"Cannot copy lines. No source order specified.");
		}
		List<PurchaseOrderLine> targetLines = new ArrayList<PurchaseOrderLine>();
		List<PurchaseOrderLine> sourceLines = this
				.getPurchaseOrderLineService().findByOrderId(source.getId());

		for (PurchaseOrderLine si : sourceLines) {
			PurchaseOrderLine ti = new PurchaseOrderLine();
			ti.setOrder(target);

			ti.setAmount(si.getAmount());
			ti.setNetAmount(si.getNetAmount());
			ti.setUnitPrice(si.getUnitPrice());
			ti.setQuantity(si.getQuantity());
			ti.setUom(si.getUom());

			ti.setProductAccount(si.getProductAccount());
			ti.setTax(si.getTax());
			targetLines.add(ti);
		}

		this.getPurchaseOrderLineService().insert(targetLines);
	}

	/**
	 * Get the sales order lines service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected IPurchaseOrderLineService getPurchaseOrderLineService()
			throws BusinessException {

		if (this.lineService == null) {
			this.lineService = (IPurchaseOrderLineService) this
					.findEntityService(PurchaseOrderLine.class);
		}
		return this.lineService;
	}

	/**
	 * Get the sales order service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected IPurchaseOrderService getPurchaseOrderService()
			throws BusinessException {

		if (this.service == null) {
			this.service = (IPurchaseOrderService) this
					.findEntityService(PurchaseOrder.class);
		}
		return this.service;
	}
}
