package lms.entities;

public enum StarRating {
    zero(0), one(1), two(2), three(3), four(4), five(5);

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private int rating;

    private StarRating(int rating) {
        this.rating = rating;
    }

}
