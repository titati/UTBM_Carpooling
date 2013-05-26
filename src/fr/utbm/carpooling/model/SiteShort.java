package fr.utbm.carpooling.model;


import java.io.Serializable;

public class SiteShort implements Serializable {

	private int id;
	private String name;


    public SiteShort(int id, String name) {
        setId(id);
        setName(name);
    }

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

}