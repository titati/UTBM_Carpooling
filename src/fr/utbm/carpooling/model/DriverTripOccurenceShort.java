package fr.utbm.carpooling.model;


import java.io.Serializable;

public class DriverTripOccurenceShort extends BaseTrip implements Serializable {

	private boolean repeat;

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

}