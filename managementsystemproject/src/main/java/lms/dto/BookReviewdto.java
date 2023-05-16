package lms.dto;

import lms.entities.StarRating;

public class BookReviewdto {

    private String username;
    private String comments;
    private int starRating;

    public BookReviewdto(String username, String comments, StarRating starRating) {
        this.username = username;
        this.comments = comments;
        this.starRating = starRating.getRating();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(StarRating starRating) {
        this.starRating = starRating.getRating();
    }

    public BookReviewdto() {
    }

}