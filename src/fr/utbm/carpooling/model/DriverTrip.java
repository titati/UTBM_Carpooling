package fr.utbm.carpooling.model;


import java.io.Serializable;

public class DriverTrip extends BaseDriverTrip implements Serializable {

	private Repeat repeat;

	public Repeat getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}

}