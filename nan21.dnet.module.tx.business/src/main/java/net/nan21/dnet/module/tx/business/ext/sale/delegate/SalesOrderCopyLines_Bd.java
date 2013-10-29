package net.nan21.dnet.module.tx.business.ext.sale.delegate;

import java.util.ArrayList;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderLineService;
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;

public class SalesOrderCopyLines_Bd extends AbstractBusinessDelegate {

	ISalesOrderLineService lineService;
	ISalesOrderService service;

	/**
	 * Create lines for the target order by copying them from the source order
	 * with the given ID
	 * 
	 * @param target
	 * @param sourceCode
	 * @throws BusinessException
	 */
	public void copyLines(SalesOrder target, String sourceId)
			throws BusinessException {
		if (sourceId == null) {
			throw new BusinessException(
					"Cannot copy lines. No source order specified.");
		}
		SalesOrder source = this.getSalesOrderService().findById(sourceId);
		copyLines(target, source);
	}

	/**
	 * Create lines for the target order by copying them from the source
	 * 
	 * @param target
	 * @param source
	 * @throws BusinessException
	 */
	public void copyLines(SalesOrder target, SalesOrder source)
			throws BusinessException {
		if (source == null) {
			throw new BusinessException(
					"Cannot copy lines. No source order specified.");
		}
		List<SalesOrderLine> targetLines = new ArrayList<SalesOrderLine>();
		List<SalesOrderLine> sourceLines = this.getSalesOrderLineService()
				.findByOrderId(source.getId());

		for (SalesOrderLine si : sourceLines) {
			SalesOrderLine ti = new SalesOrderLine();
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

		this.getSalesOrderLineService().insert(targetLines);
	}

	/**
	 * Get the sales order lines service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected ISalesOrderLineService getSalesOrderLineService()
			throws BusinessException {

		if (this.lineService == null) {
			this.lineService = (ISalesOrderLineService) this
					.findEntityService(SalesOrderLine.class);
		}
		return this.lineService;
	}

	/**
	 * Get the sales order service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected ISalesOrderService getSalesOrderService()
			throws BusinessException {

		if (this.service == null) {
			this.service = (ISalesOrderService) this
					.findEntityService(SalesOrder.class);
		}
		return this.service;
	}
}
