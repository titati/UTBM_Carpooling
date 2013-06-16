package fr.utbm.carpooling.model.wrapper;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Trunk extends JSONParsable {

	private int id;
	private String name;

    public Trunk(int id, String name) {
        setId(id);
        setName(name);
    }

	public Trunk(JSONObject object) {
		super(object);
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