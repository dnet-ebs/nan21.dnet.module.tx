/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.sale;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLine;
import net.nan21.dnet.module.tx.domain.impl.sale.SalesOrderLineTax;

/**
 * Interface to expose business functions specific for {@link SalesOrderLineTax} domain
 * entity.
 */
public interface ISalesOrderLineTaxService
		extends
			IEntityService<SalesOrderLineTax> {

	/**
	 * Find by unique key
	 */
	public SalesOrderLineTax findByTax(SalesOrderLine line, Tax tax);

	/**
	 * Find by unique key
	 */
	public SalesOrderLineTax findByTax(Long lineId, Long taxId);

	/**
	 * Find by reference: line
	 */
	public List<SalesOrderLineTax> findByLine(SalesOrderLine line);

	/**
	 * Find by ID of reference: line.id
	 */
	public List<SalesOrderLineTax> findByLineId(String lineId);

	/**
	 * Find by reference: tax
	 */
	public List<SalesOrderLineTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<SalesOrderLineTax> findByTaxId(String taxId);
}
