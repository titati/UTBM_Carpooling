package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Color implements Serializable {

	private int id;
	private String name;
	private String HTMLcode;
	
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
	public String getHTMLcode() {
		return HTMLcode;
	}
	public void setHTMLcode(String HTMLcode) {
		this.HTMLcode = HTMLcode;
	}

}