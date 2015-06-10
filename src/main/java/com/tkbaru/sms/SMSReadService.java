package com.tkbaru.sms;

import org.smslib.Library;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SMSReadService {

	public void startService() throws Exception {
		SmsReceivedHandler pesanMasuk = new SmsReceivedHandler();

		GatewayStatusHandler statusGateway = new GatewayStatusHandler();

		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		SerialModemGateway gateway = new SerialModemGateway("modem.com6", "COM6", 115200, "Huawei", "E169");

//		gateway.setIpProtocol(IPProtocols.BINARY);
//
//		gateway.setProtocol(Protocols.PDU);

		gateway.setInbound(true);

		gateway.setOutbound(true);

		gateway.setSimPin("0000");

		Service.getInstance().setInboundMessageNotification(pesanMasuk);
		Service.getInstance().setGatewayStatusNotification(statusGateway);

		Service.getInstance().addGateway(gateway);

		Service.getInstance().startService();

	}

}
