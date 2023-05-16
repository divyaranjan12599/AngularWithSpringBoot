package lms.dto;

import lms.entities.RequestBookDetails.IsActive;

public class BookRequestDto {
    private long requestId;
    private String bookName;
    private String authorName;
    private String isActive;

    public BookRequestDto() {

    }

    public BookRequestDto(long requestId, String bookName, String authorName, IsActive isActive) {

        this.requestId = requestId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isActive = isActive.toString();
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(IsActive isActive) {
        this.isActive = isActive.toString();
    }

}