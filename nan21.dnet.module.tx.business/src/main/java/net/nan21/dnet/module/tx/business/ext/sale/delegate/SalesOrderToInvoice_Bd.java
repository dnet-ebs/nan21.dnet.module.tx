package net.nan21.dnet.module.tx.business.ext.sale.delegate;

import java.util.Date;
import java.util.List;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.core.business.service.AbstractBusinessDelegate;
import net.nan21.dnet.module.md.domain.impl.base.DocType;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceService;
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderLineService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoice;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrder;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderTax;

public class SalesOrderToInvoice_Bd extends AbstractBusinessDelegate {

	public SalesInvoice generateInvoice(SalesOrder order, String invDocTypeId)
			throws BusinessException {

		List<SalesInvoice> invs = ((ISalesInvoiceService) this
				.findEntityService(SalesInvoice.class))
				.findBySalesOrderId(order.getId());
		if (invs.size() > 0) {
			String invCode = invs.get(0).getDocNo();
			throw new BusinessException(
					"Sales order is already invoiced ! Check invoice "
							+ invCode);
		}

		ISalesInvoiceService srvInvoice = (ISalesInvoiceService) this
				.findEntityService(SalesInvoice.class);
		DocType docType = srvInvoice.findById(invDocTypeId, DocType.class);

		SalesInvoice invoice = new SalesInvoice();

		invoice.setCompany(order.getCompany());
		invoice.setBpAccount(order.getBpAccount());
		invoice.setDocType(docType);
		invoice.setDocDate(new Date());
		invoice.setCurrency(order.getCurrency());
		// invoice.setPriceList(order.getPriceList());
		invoice.setBillToLocation(order.getBillToLocation());
		invoice.setBillToContact(order.getBillToContact());
		invoice.setSalesOrder(order);
		invoice.setAmount(order.getAmount());
		invoice.setNetAmount(order.getNetAmount());
		invoice.setTaxAmount(order.getTaxAmount());

		invoice.setPaymentMethod(order.getPaymentMethod());
		invoice.setPaymentTerm(order.getPaymentTerm());

		for (SalesOrderTax ordTax : order.getTaxes()) {
			SalesInvoiceTax invTax = new SalesInvoiceTax();
			invTax.setInvoice(invoice);
			invTax.setBaseAmount(ordTax.getBaseAmount());
			invTax.setTax(ordTax.getTax());
			invTax.setTaxAmount(ordTax.getTaxAmount());
			invoice.addToTaxes(invTax);
		}

		List<SalesOrderLine> items = ((ISalesOrderLineService) this
				.findEntityService(SalesOrderLine.class)).findByOrderId(order
				.getId());
		for (SalesOrderLine orderLine : items) {
			SalesInvoiceLine invLine = new SalesInvoiceLine();

			invLine.setInvoice(invoice);
			invLine.setProductAccount(orderLine.getProductAccount());
			invLine.setQuantity(orderLine.getQuantity());
			invLine.setUnitPrice(orderLine.getUnitPrice());
			invLine.setNetAmount(orderLine.getNetAmount());
			invLine.setTaxAmount(orderLine.getTaxAmount());
			invLine.setTax(orderLine.getTax());
			invLine.setUom(orderLine.getUom());

			invoice.addToLines(invLine);
			for (SalesOrderLineTax soTax : orderLine.getLineTaxes()) {
				SalesInvoiceLineTax siTax = new SalesInvoiceLineTax();
				siTax.setBaseAmount(soTax.getBaseAmount());
				siTax.setTax(soTax.getTax());
				siTax.setTaxAmount(soTax.getTaxAmount());
				siTax.setLine(invLine);
				invLine.addToLineTaxes(siTax);
			}
		}

		srvInvoice.insert(invoice);
		order.setInvoiced(true);
		srvInvoice.getEntityManager().merge(order);
		return invoice;

	}
}
