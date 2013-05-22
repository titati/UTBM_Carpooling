package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.Date;


public class Repeat implements Serializable {

	private boolean sunday;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private int periodicity;
	private Date endRepeat;
	private boolean standby;
	private Date endStandby;
	
	public boolean isSunday() {
		return sunday;
	}
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}
	public boolean isMonday() {
		return monday;
	}
	public void setMonday(boolean monday) {
		this.monday = monday;
	}
	public boolean isTuesday() {
		return tuesday;
	}
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}
	public boolean isWednesday() {
		return wednesday;
	}
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}
	public boolean isThursday() {
		return thursday;
	}
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}
	public boolean isFriday() {
		return friday;
	}
	public void setFriday(boolean friday) {
		this.friday = friday;
	}
	public boolean isSaturday() {
		return saturday;
	}
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}
	public int getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	public Date getEndRepeat() {
		return endRepeat;
	}
	public void setEndRepeat(Date endRepeat) {
		this.endRepeat = endRepeat;
	}
	public boolean isStandby() {
		return standby;
	}
	public void setStandby(boolean standby) {
		this.standby = standby;
	}
	public Date getEndStandby() {
		return endStandby;
	}
	public void setEndStandby(Date endStandby) {
		this.endStandby = endStandby;
	}

}