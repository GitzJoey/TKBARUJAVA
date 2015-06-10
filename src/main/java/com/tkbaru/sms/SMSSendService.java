package com.tkbaru.sms;

import java.io.IOException;

import org.smslib.AGateway.Protocols;
import org.smslib.GatewayException;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.ModemGateway.IPProtocols;
import org.smslib.modem.SerialModemGateway;

public class SMSSendService {

	private String message;
	private String recepientNo;

	public SMSSendService(String recepientNo, String message) {
		this.message = message;
		this.recepientNo = recepientNo;
	}

	public void startService() throws Exception {
		SmsSentHandler outgoingMessage = new SmsSentHandler();

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

		Service.getInstance().setOutboundMessageNotification(outgoingMessage);
		Service.getInstance().setGatewayStatusNotification(statusGateway);

		Service.getInstance().addGateway(gateway);

		Service.getInstance().startService();

		System.out.println();
		System.out.println("Modem Information:");

		System.out.println("  Manufacturer: " + gateway.getManufacturer());

		System.out.println("  Model: " + gateway.getModel());

		System.out.println("  Serial No: " + gateway.getSerialNo());

		System.out.println("  SIM IMSI: " + gateway.getImsi());

		System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");

		System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");

		Service.getInstance().createGroup("mygroup");
		Service.getInstance().addToGroup("mygroup", recepientNo);

		OutboundMessage msg = new OutboundMessage("mygroup", message);

		Service.getInstance().sendMessage(msg);

		System.out.println(msg);

		Service.getInstance().stopService();

		Service.getInstance().removeGateway(gateway);

	}

}
