package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Comment extends JSONParsable {
	
	private String mName;
	private String mComment;
	
	public Comment() {
		
	}
	
	public Comment(JSONObject object) {
		super(object);
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getComment() {
		return mComment;
	}

	public void setComment(String comment) {
		this.mComment = comment;
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
