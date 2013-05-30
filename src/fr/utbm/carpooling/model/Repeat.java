package fr.utbm.carpooling.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Repeat extends JSONParsable {

	private boolean sunday;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private int periodicity;
	private Date endRepeat;
	private boolean standby;
	private Date endStandby;

	public Repeat(JSONObject object) {
		super(object);
	}
	
	public boolean isSunday() {
		return sunday;
	}
	
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}
	
	public boolean isMonday() {
		return monday;
	}
	
	public void setMonday(boolean monday) {
		this.monday = monday;
	}
	
	public boolean isTuesday() {
		return tuesday;
	}
	
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}
	
	public boolean isWednesday() {
		return wednesday;
	}
	
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}
	
	public boolean isThursday() {
		return thursday;
	}
	
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}
	
	public boolean isFriday() {
		return friday;
	}
	
	public void setFriday(boolean friday) {
		this.friday = friday;
	}
	
	public boolean isSaturday() {
		return saturday;
	}
	
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	public Date getEndRepeat() {
		return endRepeat;
	}

	public void setEndRepeat(Date endRepeat) {
		this.endRepeat = endRepeat;
	}

	public boolean isStandby() {
		return standby;
	}

	public void setStandby(boolean standby) {
		this.standby = standby;
	}

	public Date getEndStandby() {
		return endStandby;
	}

	public void setEndStandby(Date endStandby) {
		this.endStandby = endStandby;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setMonday(object.getBoolean("monday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setTuesday(object.getBoolean("thuesday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setWednesday(object.getBoolean("wednesday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setThursday(object.getBoolean("thursday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setFriday(object.getBoolean("friday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setSaturday(object.getBoolean("saturday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setSunday(object.getBoolean("sunday"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPeriodicity(object.getInt("periodicity"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
		try {
			setEndRepeat(df.parse((object.getString("endRepeat"))));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setStandby(object.getBoolean("standby"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if (isStandby()) {
			try {
				setEndStandby(df.parse((object.getString("endStandby"))));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			setEndStandby(null);
		}
	}

}