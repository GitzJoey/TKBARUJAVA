package com.tkbaru.sms;

import java.io.IOException;
import java.util.Date;

import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Library;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.model.Modem;
import com.tkbaru.model.SmsIn;
import com.tkbaru.model.SmsOut;
import com.tkbaru.service.ModemService;
import com.tkbaru.service.SmsInService;
import com.tkbaru.service.SmsOutService;

@org.springframework.stereotype.Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	SmsOutService smsOutService;

	@Autowired
	SmsInService smsInService;
	
	@Autowired
	ModemService modemService;

	public void startService() throws Exception {

		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		Modem modem = modemService.getModemById(1);
		SerialModemGateway gateway = new SerialModemGateway("modem1", modem.getPort(), modem.getBaudRate(), modem.getManufacturer(), modem.getModel());

		gateway.setInbound(true);
		gateway.setOutbound(true);
		gateway.setSimPin("0000");
		gateway.setSmscNumber(modem.getSmsCenter());

		Service.getInstance().setInboundMessageNotification(new SmsReceivedHandler());
		Service.getInstance().setOutboundMessageNotification(new SmsSentHandler());
		Service.getInstance().setGatewayStatusNotification(new GatewayStatusHandler());

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

	private class SmsReceivedHandler implements IInboundMessageNotification {

		@Override
		public void process(AGateway gateway, MessageTypes msgType, InboundMessage inboundMessage) {
							
			// AUTO RESPONSE Message

			if (inboundMessage.getText().equalsIgnoreCase("ASK PRICE")) {

				OutboundMessage msg = new OutboundMessage("+" + inboundMessage.getOriginator(), inboundMessage.getText() + " AUTO REPLY");
                
				try {
					Service.getInstance().sendMessage(msg);
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GatewayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SmsOut smsOut = new SmsOut();
				smsOut.setRecipient(inboundMessage.getOriginator());
				smsOut.setMessage("AUTO RESPONSE Message");
				smsOut.setCreatedDate(new Date());
				smsOutService.addSmsOutbox(smsOut);

			}
			
			SmsIn newSms = new SmsIn();
			newSms.setSender(inboundMessage.getOriginator());
			newSms.setMessage(inboundMessage.getText());
			newSms.setCreatedDate(inboundMessage.getDate());
			smsInService.addSmsIn(newSms);
			
			try {
				Service.getInstance().deleteMessage(inboundMessage);
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GatewayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private class SmsSentHandler implements IOutboundMessageNotification {
		public void process(AGateway gateway, OutboundMessage msg) {
			System.out.println("Sms SentHandler handler called from Gateway: " + gateway.getGatewayId());
		
			System.out.println(msg);

		}
	}

	private class GatewayStatusHandler implements IGatewayStatusNotification {

		@Override
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {

		}

	}

}
