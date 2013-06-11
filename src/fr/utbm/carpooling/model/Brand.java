package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Brand extends JSONParsable {

	private int id;
	private String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

	public Brand(JSONObject object) {
		super(object);
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
	
	public String serializeJSON() {
		return  "\"id\" : \"" + getId() +
				"\", \"name\" : \"" + getName() + "\"";
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setId(object.getInt("id"));
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