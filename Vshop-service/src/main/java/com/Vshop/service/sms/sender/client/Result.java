package com.Vshop.service.sms.sender.client;


public class Result {
	private long sendDate;
	private int status;
	@SuppressWarnings("unused")
	private boolean success;

	public long getSendDate() {
		return sendDate;
	}

	public void setSendDate(long sendDate) {
		this.sendDate = sendDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getSuccess() {
		return status == 0 ? true : false;
	}
	
	public void setSuccess(){
		this.success = status == 0 ? true : false;
	}
	
}
