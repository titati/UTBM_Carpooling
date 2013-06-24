package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;

public class Color extends JSONParsableObject implements Comparable<Color> {

    private int id;
    private String hex;


    public Color(int id, String hex) {
        setId(id);
        setHex(hex);
    }

    public Color(JSONObject object) {
		super(object);
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {

        if (!hex.startsWith("#")) {
            hex = "#".concat(hex);
        }

        this.hex = hex;
    }
	
	public String serializeJSON() {
		return  "\"id\" : \"" + getId() +
				"\", \"hex\" : \"" + getHex() + "\"";
	}

    @Override
    public int compareTo(Color color) {

        int C1 = android.graphics.Color.parseColor(getHex());
        int C2 = android.graphics.Color.parseColor(color.getHex());

        float[] hsv1 = new float[3];
        float[] hsv2 = new float[3];

        android.graphics.Color.colorToHSV(C1, hsv1);
        android.graphics.Color.colorToHSV(C2, hsv2);

        if (hsv1[0] < hsv2[0])
            return -1;
        if (hsv1[0] > hsv2[0])
            return 1;
        if (hsv1[1] < hsv2[1])
            return -1;
        if (hsv1[1] > hsv2[1])
            return 1;
        if (hsv1[2] < hsv2[2])
            return -1;
        if (hsv1[2] > hsv2[2])
            return 1;
        return 0;
    }


	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setId(object.getInt("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setHex(object.getString("hex"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
