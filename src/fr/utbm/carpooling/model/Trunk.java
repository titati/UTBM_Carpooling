package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Trunk implements Serializable {

	private int id;
	private String name;

    public Trunk(int id, String name) {
        this.id = id;
        this.name = name;
    }


	public int getId() {
		return id;
	}
	public void setId(int id) {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}