package fr.utbm.carpooling.model.wrapper;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.Brand;
import fr.utbm.carpooling.model.Model;
import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.utils.JSONSerializable;

public class CarsReferences extends JSONParsableObject implements JSONSerializable {
	
	private ArrayList<Brand> brands;
	private ArrayList<Model> models;
	
	public CarsReferences(JSONObject object) {
		super(object);
	}
	
	public ArrayList<Brand> getBrands() {
		return brands;
	}
	
	public void setBrands(ArrayList<Brand> brands) {
		this.brands = brands;
	}
	
	public ArrayList<Model> getModels() {
		return models;
	}
	
	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		brands = new ArrayList<Brand>();
		models = new ArrayList<Model>();
		
		try {
			for(int i = 0; i < object.getJSONArray("brands").length(); ++i) {
				brands.add(new Brand((JSONObject) object.getJSONArray("brands").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			for(int i = 0; i < object.getJSONArray("models").length(); ++i) {
				models.add(new Model((JSONObject) object.getJSONArray("models").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String serializeJSON() {
		String str = "\"brands\" : [";
		
		for(int i = 0; i < brands.size(); ++i) {
			str += "{ " + brands.get(i).serializeJSON() + " },";
		}
		
		str = ((brands.size() > 0) ? str.substring(0, str.length() - 1) : str) + " ], \"models\" : [";
		
		for(int i = 0; i < models.size(); ++i) {
			str += "{ " + models.get(i).serializeJSON() + " },";
		}
		
		str = ((models.size() > 0) ? str.substring(0, str.length() - 1) : str) + " ]";
		
		return str;
	}
	
	
}
