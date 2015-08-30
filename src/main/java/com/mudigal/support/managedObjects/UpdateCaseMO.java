package com.mudigal.support.managedObjects;

public class UpdateCaseMO {

	private Integer startId;
	private Integer endId;
	private String field;
	private String value;
	
	public Integer getStartId() {
		return startId;
	}
	public void setStartId(Integer startId) {
		this.startId = startId;
	}
	public Integer getEndId() {
		return endId;
	}
	public void setEndId(Integer endId) {
		this.endId = endId;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
