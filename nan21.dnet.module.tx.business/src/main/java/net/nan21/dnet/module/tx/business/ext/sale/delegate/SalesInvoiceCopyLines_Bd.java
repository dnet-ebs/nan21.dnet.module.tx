package net.nan21.dnet.module.tx.business.ext.sale.delegate;

import java.util.ArrayList;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceLineService;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;

public class SalesInvoiceCopyLines_Bd extends AbstractBusinessDelegate {

	ISalesInvoiceLineService itemService;
	ISalesInvoiceService service;

	/**
	 * Create lines for the target invoice by copying them from the source
	 * invoice with the given ID
	 * 
	 * @param target
	 * @param sourceCode
	 * @throws BusinessException
	 */
	public void copyLines(SalesInvoice target, String sourceId)
			throws BusinessException {
		if (sourceId == null) {
			throw new BusinessException(
					"Cannot copy lines. No source invoice specified.");
		}
		SalesInvoice source = this.getSalesInvoiceService().findById(sourceId);
		copyLines(target, source);
	}

	/**
	 * Create lines for the target invoice by copying them from the source
	 * 
	 * @param target
	 * @param source
	 * @throws BusinessException
	 */
	public void copyLines(SalesInvoice target, SalesInvoice source)
			throws BusinessException {
		if (source == null) {
			throw new BusinessException(
					"Cannot copy lines. No source invoice specified.");
		}
		List<SalesInvoiceLine> targetItems = new ArrayList<SalesInvoiceLine>();
		List<SalesInvoiceLine> sourceItems = this.getSalesInvoiceLineService()
				.findByInvoiceId(source.getId());

		for (SalesInvoiceLine si : sourceItems) {
			SalesInvoiceLine ti = new SalesInvoiceLine();
			ti.setInvoice(target);

			ti.setAmount(si.getAmount());
			ti.setNetAmount(si.getNetAmount());
			ti.setUnitPrice(si.getUnitPrice());
			ti.setQuantity(si.getQuantity());
			ti.setUom(si.getUom());

			ti.setProductAccount(si.getProductAccount());
			ti.setTax(si.getTax());
			targetItems.add(ti);
		}

		this.getSalesInvoiceLineService().insert(targetItems);
	}

	/**
	 * Get the sales invoice items service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected ISalesInvoiceLineService getSalesInvoiceLineService()
			throws BusinessException {

		if (this.itemService == null) {
			this.itemService = (ISalesInvoiceLineService) this
					.findEntityService(SalesInvoiceLine.class);
		}
		return this.itemService;
	}

	/**
	 * Get the sales invoice service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected ISalesInvoiceService getSalesInvoiceService()
			throws BusinessException {

		if (this.service == null) {
			this.service = (ISalesInvoiceService) this
					.findEntityService(SalesInvoice.class);
		}
		return this.service;
	}

}
