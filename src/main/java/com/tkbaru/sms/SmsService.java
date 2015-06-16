package com.tkbaru.sms;

import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SmsService {

	public void startService() throws Exception {
		SmsSentHandler outgoingMessage = new SmsSentHandler();
		SmsReceivedHandler pesanMasuk = new SmsReceivedHandler();

		GatewayStatusHandler statusGateway = new GatewayStatusHandler();

		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		SerialModemGateway gateway = new SerialModemGateway("modem.com6", "COM6", 115200, "Huawei", "E153");

		gateway.setInbound(true);

		gateway.setOutbound(true);

		gateway.setSimPin("0000");

		Service.getInstance().setInboundMessageNotification(pesanMasuk);
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

	}

	public void send(String recepientNo, String message) throws Exception {

		Service.getInstance().createGroup("mygroup");
		Service.getInstance().addToGroup("mygroup", recepientNo);

		OutboundMessage msg = new OutboundMessage("mygroup", message);

		Service.getInstance().sendMessage(msg);

	}

	public void stopService() throws Exception {

		Service.getInstance().stopService();

	}

}
