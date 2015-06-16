package com.tkbaru.sms;

import java.io.IOException;
import java.util.Date;

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;

import com.tkbaru.model.SmsIn;
import com.tkbaru.model.SmsOut;
import com.tkbaru.service.SmsInService;
import com.tkbaru.service.SmsOutService;

public class SmsReceivedHandler implements IInboundMessageNotification {
	
	@Autowired
	SmsOutService smsOutManager;
	
	@Autowired
	SmsInService smsInManager;

	@Override
	public void process(AGateway gateway, MessageTypes msgType, InboundMessage inboundMessage) {

		System.out.println(inboundMessage.getText());
		System.out.println(inboundMessage.getSmscNumber());
		System.out.println(inboundMessage.getPduUserData());
		System.out.println(inboundMessage.getEncoding().name());
		System.out.println(inboundMessage.getGatewayId());
		System.out.println(inboundMessage.getDate().getTime());
		
		SmsIn newSms = new SmsIn();
		newSms.setFrom(inboundMessage.getOriginator());
		newSms.setMessage(inboundMessage.getText());
		newSms.setCreatedDate(inboundMessage.getDate());
		
		smsInManager.addSmsInbox(newSms);

		// AUTO RESPONSE Message

		if (inboundMessage.getText().equalsIgnoreCase("ASK PRICE")) {

			OutboundMessage sentMessage = new OutboundMessage(inboundMessage.getOriginator(), "AUTO RESPONSE Message FOR PRICE");

			try {
				Service.getInstance().sendMessage(sentMessage);
				SmsOut smsOut = new SmsOut();
				smsOut.setTo(inboundMessage.getOriginator());
				smsOut.setMessage("AUTO RESPONSE Message");
				smsOut.setCreatedDate(new Date());
				smsOutManager.addSmsOutbox(smsOut);
				
			} catch (TimeoutException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (GatewayException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
}
