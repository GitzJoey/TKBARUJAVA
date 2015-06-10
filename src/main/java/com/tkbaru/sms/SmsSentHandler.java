package com.tkbaru.sms;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

public class SmsSentHandler implements IOutboundMessageNotification {
    public void process(AGateway gateway, OutboundMessage msg) {
        System.out.println("SmsSentHandler handler called from Gateway: " + gateway.getGatewayId());
        System.out.println(msg);

     }
  }
