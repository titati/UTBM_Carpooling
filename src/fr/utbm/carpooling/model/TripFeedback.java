package fr.utbm.carpooling.model;


import java.io.Serializable;

public class TripFeedback implements Serializable {

	private int rating;
	private String comment;
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}