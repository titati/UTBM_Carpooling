package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Model implements Serializable {

	private int id;
    private int brandId;
	private String name;


    public Model(int brandId, int id, String name) {
        this.brandId = brandId;
        this.id = id;
        this.name = name;
    }


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}