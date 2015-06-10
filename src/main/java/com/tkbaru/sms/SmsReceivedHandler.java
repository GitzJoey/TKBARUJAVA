package com.tkbaru.sms;

import java.io.IOException;

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;

public class SmsReceivedHandler implements IInboundMessageNotification {

	@Override
	public void process(AGateway gateway, MessageTypes msgType, InboundMessage inboundMessage) {

		System.out.println(inboundMessage.getText());
		System.out.println(inboundMessage.getSmscNumber());
		System.out.println(inboundMessage.getPduUserData());
		System.out.println(inboundMessage.getEncoding().name());
		System.out.println(inboundMessage.getGatewayId());
		System.out.println(inboundMessage.getDate().getTime());

		// AUTO RESPONSE Message

		if (inboundMessage.getText().equalsIgnoreCase("PRICE RICE")) {

			OutboundMessage sentMessage = new OutboundMessage(inboundMessage.getSmscNumber(), "AUTO RESPONSE Message");

			try {
				Service.getInstance().sendMessage(sentMessage);
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
