package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.Date;


public class Checkpoint implements Serializable {

	private SiteShort site;
	private Date date;
	
	public SiteShort getSite() {
		return site;
	}
	public void setSite(SiteShort site) {
		this.site = site;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}