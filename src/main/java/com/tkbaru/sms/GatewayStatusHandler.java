package com.tkbaru.sms;

import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;
import org.smslib.AGateway.GatewayStatuses;

public class GatewayStatusHandler implements IGatewayStatusNotification {

	@Override
	public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
		

	}

}
