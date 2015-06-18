package com.tkbaru.sms;

public interface SmsService {
	
	void startService() throws Exception;
	void stopService() throws Exception;
	void send(String recepientNo, String message) throws Exception;

}
