package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Color implements Serializable {

	private int id;
	private String name;
	private String hex;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHex() {
		return hex;
	}
	public void setHex(String hex) {
		this.hex = hex;
	}

}