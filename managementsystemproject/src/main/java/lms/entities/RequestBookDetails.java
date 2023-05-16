package lms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RequestBookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestBookId;
    private String authorName;
    private String bookName;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserDetails userDetail;

    @Enumerated
    private IsActive isActive;

    public enum IsActive {
        Cancelled,
        Pending,
        Pending_Purchase,
        Approved

    }

    public long getRequestBookId() {
        return requestBookId;
    }

    public void setRequestBookId(long requestBookId) {
        this.requestBookId = requestBookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public UserDetails getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetails userDetail) {
        this.userDetail = userDetail;
    }

    public IsActive getIsActive() {
        return isActive;
    }

    public void setIsActive(IsActive isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "RequestBookDetails [requestBookId=" + requestBookId + ", authorName=" + authorName + ", bookName="
                + bookName + ", userDetail=" + userDetail + ", isActive=" + isActive + "]";
    }

}