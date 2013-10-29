/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.inventory.service;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.inventory.IInventoryTransactionService;
import net.nan21.dnet.module.tx.domain.impl.inventory.InventoryTransaction;

/**
 * Business extensions specific for {@link InventoryTransaction} domain entity.
 * 
 */
public class InventoryTransaction_Service
		extends
		net.nan21.dnet.module.tx.business.impl.inventory.InventoryTransaction_Service
		implements IInventoryTransactionService {

	@Override
	public void doConfirm(String transactionId) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnConfirm(String transactionId) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
