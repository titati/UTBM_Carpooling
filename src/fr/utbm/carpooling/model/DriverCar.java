package fr.utbm.carpooling.model;

import java.io.Serializable;

public class DriverCar extends Car implements Serializable {

	private boolean defaultCar;

	public boolean isDefaultCar() {
		return defaultCar;
	}

	public void setDefaultCar(boolean defaultCar) {
		this.defaultCar = defaultCar;
	}
}