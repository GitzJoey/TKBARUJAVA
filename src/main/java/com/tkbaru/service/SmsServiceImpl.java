package com.tkbaru.service;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@org.springframework.stereotype.Service
public class SmsServiceImpl implements SmsService {
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	SmsOutService smsOutService;

	@Autowired
	SmsInService smsInService;
	
	@Autowired
	ModemService modemService;

	public void startService() throws Exception {
		logger.info("[startService] " + "");
		
		logger.info(Library.getLibraryDescription());
		logger.info("Version: " + Library.getLibraryVersion());
		
		Modem modem = modemService.getModemById(1);
		SerialModemGateway gateway = null;
		try {
			gateway = new SerialModemGateway("modem1", modem.getPort(), modem.getBaudRate(), modem.getManufacturer(), modem.getModel());
			gateway.setInbound(true);
			gateway.setOutbound(true);
			gateway.setSimPin("0000");
			gateway.setSmscNumber(modem.getSmsCenter());

			Service.getInstance().setInboundMessageNotification(new SmsReceivedHandler());
			Service.getInstance().setOutboundMessageNotification(new SmsSentHandler());
			Service.getInstance().setGatewayStatusNotification(new GatewayStatusHandler());

			Service.getInstance().addGateway(gateway);

			Service.getInstance().startService();

			logger.info("");
			logger.info("Modem Information:");
			logger.info("  Manufacturer: " + gateway.getManufacturer());
			logger.info("  Model: " + gateway.getModel());
			logger.info("  Serial No: " + gateway.getSerialNo());
			logger.info("  SIM IMSI: " + gateway.getImsi());
			logger.info("  Signal Level: " + gateway.getSignalLevel() + " dBm");
			logger.info("  Battery Level: " + gateway.getBatteryLevel() + "%");

		} catch (Exception err) {
			logger.info("[startService] " + "Unable to create SerialModemGateway: " + err.getMessage());
			System.out.println("[startService] " + "Unable to create SerialModemGateway: " + err.getMessage());
		}
	}

	public void send(String recepientNo, String message) throws Exception {
		logger.info("[send] " + "recepientNo: " + recepientNo + ", message: " + message);
		
		Service.getInstance().createGroup("mygroup");
		Service.getInstance().addToGroup("mygroup", recepientNo);

		OutboundMessage msg = new OutboundMessage("mygroup", message);

		Service.getInstance().sendMessage(msg);

	}

	public void stopService() throws Exception {
		logger.info("[stopService] " + "");
		
		Service.getInstance().stopService();

	}

	private class SmsReceivedHandler implements IInboundMessageNotification {
		
		@Override
		public void process(AGateway gateway, MessageTypes msgType, InboundMessage inboundMessage) {
			logger.info("[SmsReceivedHandler - process] " + "");
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
			logger.info("[SmsSentHandler - process] " + "");
			
			logger.info("Sms SentHandler handler called from Gateway: " + gateway.getGatewayId());
		
			logger.info(msg.toString());

		}
	}

	private class GatewayStatusHandler implements IGatewayStatusNotification {

		@Override
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
			logger.info("[GatewayStatusHandler - process] " + "");

		}

	}

}
