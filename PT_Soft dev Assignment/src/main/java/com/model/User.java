package com.model;

public class User {
protected int id;
protected String Voltage;
protected String Current;
protected String Distance;
protected String Duration;
protected String Power;
public User(int id, String voltage, String current, String distance, String duration, String power) {
	super();
	this.id = id;
	Voltage = voltage;
	Current = current;
	Distance = distance;
	Duration = duration;
	Power = power;
}
public User(String voltage, String current, String distance, String duration, String power) {
	super();
	Voltage = voltage;
	Current = current;
	Distance = distance;
	Duration = duration;
	Power = power;
}
public User() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getVoltage() {
	return Voltage;
}
public void setVoltage(String voltage) {
	Voltage = voltage;
}
public String getCurrent() {
	return Current;
}
public void setCurrent(String current) {
	Current = current;
}
public String getDistance() {
	return Distance;
}
public void setDistance(String distance) {
	Distance = distance;
}
public String getDuration() {
	return Duration;
}
public void setDuration(String duration) {
	Duration = duration;
}
public String getPower() {
	return Power;
}
public void setPower(String power) {
	Power = power;
}


}
