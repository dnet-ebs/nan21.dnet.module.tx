/**
 * DNet eBusiness Suite
 * Copyright: 2010-2013 Nan21 Electronics SRL. All rights reserved.
 * Use is subject to license terms.
 */
package net.nan21.dnet.module.tx.business.ext.financial.service;

import net.nan21.dnet.core.api.exceptions.BusinessException;
import net.nan21.dnet.module.tx.business.api.financial.IPaymentService;
import net.nan21.dnet.module.tx.domain.impl.financial.Payment;

/**
 * Business extensions specific for {@link Payment} domain entity.
 * 
 */
public class Payment_Service extends
		net.nan21.dnet.module.tx.business.impl.financial.Payment_Service
		implements IPaymentService {

	@Override
	public void doConfirm(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnConfirm(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPost(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUnPost(Payment payment) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
