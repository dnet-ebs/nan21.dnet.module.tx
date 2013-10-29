/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.impl.sale;

import java.util.List;
import javax.persistence.EntityManager;
import net.nan21.dnet.core.api.session.Session;
import net.nan21.dnet.core.business.service.entity.AbstractEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.business.api.sale.ISalesInvoiceLineTaxService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesInvoiceLineTax;

/**
 * Repository functionality for {@link SalesInvoiceLineTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesInvoiceLineTax_Service
		extends
			AbstractEntityService<SalesInvoiceLineTax>
		implements
			ISalesInvoiceLineTaxService {

	public SalesInvoiceLineTax_Service() {
		super();
	}

	public SalesInvoiceLineTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesInvoiceLineTax> getEntityClass() {
		return SalesInvoiceLineTax.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesInvoiceLineTax findByTax(SalesInvoiceLine line, Tax tax) {
		return (SalesInvoiceLineTax) this
				.getEntityManager()
				.createNamedQuery(SalesInvoiceLineTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("line", line).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public SalesInvoiceLineTax findByTax(Long lineId, Long taxId) {
		return (SalesInvoiceLineTax) this
				.getEntityManager()
				.createNamedQuery(SalesInvoiceLineTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: line
	 */
	public List<SalesInvoiceLineTax> findByLine(SalesInvoiceLine line) {
		return this.findByLineId(line.getId());
	}
	/**
	 * Find by ID of reference: line.id
	 */
	public List<SalesInvoiceLineTax> findByLineId(String lineId) {
		return (List<SalesInvoiceLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLineTax e where e.clientId = :clientId and e.line.id = :lineId",
						SalesInvoiceLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesInvoiceLineTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesInvoiceLineTax> findByTaxId(String taxId) {
		return (List<SalesInvoiceLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesInvoiceLineTax e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesInvoiceLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
