package lms.entities;

import jakarta.persistence.*;

@Entity
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private String comments;

    @Enumerated(EnumType.ORDINAL)
    private StarRating starRating;


    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public StarRating getStarRating() {
        return starRating;
    }

    public BookDetails getBookdetails() {
        return bookdetails;
    }

    public void setBookdetails(BookDetails bookdetails) {
        this.bookdetails = bookdetails;
    }

    public void setStarRating(StarRating starRating) {
        this.starRating = starRating;
    }


    @ManyToOne(targetEntity = BookDetails.class)
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    public BookDetails bookdetails;


    @ManyToOne(targetEntity = UserDetails.class)
    @JoinColumn(name = "User_id", referencedColumnName = "userId")
    public UserDetails userdetails;

    public BookReview() {

    }

    public UserDetails getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(UserDetails userdetails) {
        this.userdetails = userdetails;
    }

    public BookReview(Long ratingId, String comments, StarRating starRating, BookDetails bookdetails,
                      UserDetails userdetails) {
        super();
        this.ratingId = ratingId;
        this.comments = comments;
        this.starRating = starRating;
        this.bookdetails = bookdetails;
        this.userdetails = userdetails;
    }

    @Override
    public String toString() {
        return "BookReview [ratingId=" + ratingId + ", comments=" + comments + ", starRating=" + starRating
                + ", bookdetails=" + bookdetails + ", userdetails=" + userdetails + "]";
    }


}
