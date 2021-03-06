package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.utils.JSONSerializable;

public class Model extends JSONParsableObject implements JSONSerializable {

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

	public String serializeJSON() {
		return  "\"id\" : \"" + getId() +
				"\", \"brandid\" : \"" + getBrandId() +
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
			setBrandId(object.getInt("brandid"));
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