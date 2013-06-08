package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Comment extends JSONParsable {
	
	private String name;
	private String comment;
	
	public Comment() {
		
	}
	
	public Comment(JSONObject object) {
		super(object);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setName(object.getString("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setComment(object.getString("comment"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
