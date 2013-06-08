package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Model extends JSONParsable {

	private int id;
    private int brandId;
	private String name;


    public Model(int brandId, int id, String name) {
        this.brandId = brandId;
        this.id = id;
        this.name = name;
    }
    
	public Model(JSONObject object) {
		super(object);
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


	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setId(object.getInt("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setBrandId(object.getInt("brandId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setName(object.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}