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
import net.nan21.dnet.module.tx.business.api.sale.ISalesOrderLineTaxService;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;

/**
 * Repository functionality for {@link SalesOrderLineTax} domain entity. It contains
 * finder methods based on unique keys as well as reference fields.
 * 
 */
public class SalesOrderLineTax_Service
		extends
			AbstractEntityService<SalesOrderLineTax>
		implements
			ISalesOrderLineTaxService {

	public SalesOrderLineTax_Service() {
		super();
	}

	public SalesOrderLineTax_Service(EntityManager em) {
		super();
		this.setEntityManager(em);
	}

	@Override
	public Class<SalesOrderLineTax> getEntityClass() {
		return SalesOrderLineTax.class;
	}
	/**
	 * Find by unique key
	 */
	public SalesOrderLineTax findByTax(SalesOrderLine line, Tax tax) {
		return (SalesOrderLineTax) this
				.getEntityManager()
				.createNamedQuery(SalesOrderLineTax.NQ_FIND_BY_TAX)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("line", line).setParameter("tax", tax)
				.getSingleResult();
	}
	/**
	 * Find by unique key
	 */
	public SalesOrderLineTax findByTax(Long lineId, Long taxId) {
		return (SalesOrderLineTax) this
				.getEntityManager()
				.createNamedQuery(SalesOrderLineTax.NQ_FIND_BY_TAX_PRIMITIVE)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).setParameter("taxId", taxId)
				.getSingleResult();
	}
	/**
	 * Find by reference: line
	 */
	public List<SalesOrderLineTax> findByLine(SalesOrderLine line) {
		return this.findByLineId(line.getId());
	}
	/**
	 * Find by ID of reference: line.id
	 */
	public List<SalesOrderLineTax> findByLineId(String lineId) {
		return (List<SalesOrderLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLineTax e where e.clientId = :clientId and e.line.id = :lineId",
						SalesOrderLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("lineId", lineId).getResultList();
	}
	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderLineTax> findByTax(Tax tax) {
		return this.findByTaxId(tax.getId());
	}
	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderLineTax> findByTaxId(String taxId) {
		return (List<SalesOrderLineTax>) this
				.getEntityManager()
				.createQuery(
						"select e from SalesOrderLineTax e where e.clientId = :clientId and e.tax.id = :taxId",
						SalesOrderLineTax.class)
				.setParameter("clientId",
						Session.user.get().getClient().getId())
				.setParameter("taxId", taxId).getResultList();
	}
}
