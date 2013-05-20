package fr.utbm.carpooling.model;


public class DriverCar extends Car { 

	private boolean defaultCar;

	public boolean isDefaultCar() {
		return defaultCar;
	}

	public void setDefaultCar(boolean defaultCar) {
		this.defaultCar = defaultCar;
	}

}