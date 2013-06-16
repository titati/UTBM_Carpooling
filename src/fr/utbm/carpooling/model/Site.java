package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Site extends JSONParsable {

	private int id;
	private String name;
	private GpsPosition location;
	private String street;
	private String postalCode;
	private String town;

    public Site(int id, String name) {
        setId(id);
        setName(name);
    }
    
    public Site(JSONObject object) {
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

	public GpsPosition getLocation() {
		return location;
	}

	public void setLocation(GpsPosition location) {
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
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
		
		try {
			setLocation(new GpsPosition(object.getJSONObject("location")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setStreet(object.getString("street"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPostalCode(object.getString("postalcode"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setTown(object.getString("town"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}