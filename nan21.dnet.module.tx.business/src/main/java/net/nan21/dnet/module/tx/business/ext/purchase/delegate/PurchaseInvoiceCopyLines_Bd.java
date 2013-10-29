package net.nan21.dnet.module.tx.business.ext.purchase.delegate;

import java.util.ArrayList;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceLineService;
import net.nan21.dnet.module.tx.business.api.purchase.IPurchaseInvoiceService;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoice;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseInvoiceLine;

public class PurchaseInvoiceCopyLines_Bd extends AbstractBusinessDelegate {

	IPurchaseInvoiceLineService itemService;
	IPurchaseInvoiceService service;

	/**
	 * Create lines for the target invoice by copying them from the source
	 * invoice with the given ID
	 * 
	 * @param target
	 * @param sourceCode
	 * @throws BusinessException
	 */
	public void copyLines(PurchaseInvoice target, String sourceId)
			throws BusinessException {
		if (sourceId == null) {
			throw new BusinessException(
					"Cannot copy lines. No source invoice specified.");
		}
		PurchaseInvoice source = this.getPurchaseInvoiceService().findById(
				sourceId);
		copyLines(target, source);
	}

	/**
	 * Create lines for the target invoice by copying them from the source
	 * 
	 * @param target
	 * @param source
	 * @throws BusinessException
	 */
	public void copyLines(PurchaseInvoice target, PurchaseInvoice source)
			throws BusinessException {
		if (source == null) {
			throw new BusinessException(
					"Cannot copy lines. No source invoice specified.");
		}
		List<PurchaseInvoiceLine> targetLines = new ArrayList<PurchaseInvoiceLine>();
		List<PurchaseInvoiceLine> sourceLines = this
				.getPurchaseInvoiceLineService()
				.findByInvoiceId(source.getId());

		for (PurchaseInvoiceLine si : sourceLines) {
			PurchaseInvoiceLine ti = new PurchaseInvoiceLine();
			ti.setInvoice(target);
			ti.setEntryMode(si.getEntryMode());
			ti.setAmount(si.getAmount());
			ti.setNetAmount(si.getNetAmount());
			ti.setUnitPrice(si.getUnitPrice());
			ti.setQuantity(si.getQuantity());
			ti.setUom(si.getUom());
			ti.setUseGivenTax(si.getUseGivenTax());
			ti.setProductAccount(si.getProductAccount());
			ti.setTax(si.getTax());
			targetLines.add(ti);
		}

		this.getPurchaseInvoiceLineService().insert(targetLines);
	}

	/**
	 * Get the purchase invoice items service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected IPurchaseInvoiceLineService getPurchaseInvoiceLineService()
			throws BusinessException {

		if (this.itemService == null) {
			this.itemService = (IPurchaseInvoiceLineService) this
					.findEntityService(PurchaseInvoiceLine.class);
		}
		return this.itemService;
	}

	/**
	 * Get the purchase invoice service
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected IPurchaseInvoiceService getPurchaseInvoiceService()
			throws BusinessException {

		if (this.service == null) {
			this.service = (IPurchaseInvoiceService) this
					.findEntityService(PurchaseInvoice.class);
		}
		return this.service;
	}
}
