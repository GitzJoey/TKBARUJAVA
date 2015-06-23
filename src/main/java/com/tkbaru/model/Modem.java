package com.tkbaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_modem")
public class Modem {
	public Modem() {

	}

	@Id
	@GeneratedValue
	@Column(name = "modem_id")
	private int modemId;
	@Column(name = "port")
	private String port;
	@Column(name = "manufacturer")
	private String manufacturer;
	@Column(name = "model")
	private String model;
	@Column(name = "sms_center")
	private String smsCenter;
	@Column(name = "baud_rate")
	private int baudRate;

	public int getModemId() {
		return modemId;
	}

	public void setModemId(int modemId) {
		this.modemId = modemId;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSmsCenter() {
		return smsCenter;
	}

	public void setSmsCenter(String smsCenter) {
		this.smsCenter = smsCenter;
	}

	public int getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	@Override
	public String toString() {
		return "Modem [modemId=" + modemId + "]";
	}

}
