package fr.utbm.carpooling.model;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;
import fr.utbm.carpooling.webservices.ResourcesWebServices;

public class TripSearch {

	private int fromSiteId;
	private int toSiteId;
	private Date arrivalDate;
	private int minTrunkId;
	
	public int getMinTrunkId() {
		return minTrunkId;
	}
	
	public void setMinTrunkId(int minTrunk) {
		this.minTrunkId = minTrunk;
	}

	public int getFromSiteId() {
		return fromSiteId;
	}

	public void setFromSiteId(int fromSiteId) {
		this.fromSiteId = fromSiteId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getToSiteId() {
		return toSiteId;
	}

	public void setToSiteId(int toSiteId) {
		this.toSiteId = toSiteId;
	}

	public ArrayList<BasicNameValuePair> toParam() {
		ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

		list.add(new BasicNameValuePair("startSiteId", String.valueOf(getToSiteId())));
		list.add(new BasicNameValuePair("endSiteId", String.valueOf(getFromSiteId())));
		list.add(new BasicNameValuePair("arrivalDate", ResourcesWebServices.getStandardDateFormat().format(getArrivalDate())));
		list.add(new BasicNameValuePair("minTrunk", String.valueOf(getMinTrunkId())));
		
		return list;
	}
}