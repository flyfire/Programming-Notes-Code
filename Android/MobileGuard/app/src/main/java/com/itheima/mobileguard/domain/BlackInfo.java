package com.itheima.mobileguard.domain;

public class BlackInfo {
	private String number;
	/**
	 * ����ģʽ 0������ 1ȫ������ 2�������� 3�绰����
	 */
	private String mode;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		if("1".equals(mode) || "2".equals(mode)  || "3".equals(mode) ){
			this.mode = mode;
		}else{
			this.mode = "0";
		}
	}
	public BlackInfo(String number, String mode) {
		super();
		this.number = number;
		this.mode = mode;
	}
	public BlackInfo() {
		super();
	}
	
}
