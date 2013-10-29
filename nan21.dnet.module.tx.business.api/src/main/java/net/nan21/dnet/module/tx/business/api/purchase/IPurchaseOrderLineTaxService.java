/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.api.purchase;

import java.util.List;
import net.nan21.dnet.core.api.service.business.IEntityService;
import net.nan21.dnet.module.md.domain.impl.base.Tax;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLine;
import net.nan21.dnet.module.tx.domain.impl.purchase.PurchaseOrderLineTax;

/**
 * Interface to expose business functions specific for {@link PurchaseOrderLineTax} domain
 * entity.
 */
public interface IPurchaseOrderLineTaxService
		extends
			IEntityService<PurchaseOrderLineTax> {

	/**
	 * Find by unique key
	 */
	public PurchaseOrderLineTax findByTax(PurchaseOrderLine line, Tax tax);

	/**
	 * Find by unique key
	 */
	public PurchaseOrderLineTax findByTax(Long lineId, Long taxId);

	/**
	 * Find by reference: line
	 */
	public List<PurchaseOrderLineTax> findByLine(PurchaseOrderLine line);

	/**
	 * Find by ID of reference: line.id
	 */
	public List<PurchaseOrderLineTax> findByLineId(String lineId);

	/**
	 * Find by reference: tax
	 */
	public List<PurchaseOrderLineTax> findByTax(Tax tax);

	/**
	 * Find by ID of reference: tax.id
	 */
	public List<PurchaseOrderLineTax> findByTaxId(String taxId);
}
