package lms.dto;


public class RequestEnddatedto {

    private Long issueId;

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    private String username;
    private String booktitle;
    private String requestExtension;
    private String issueReturnDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public RequestEnddatedto() {

    }

    public String getRequestExtension() {
        return requestExtension;
    }

    public void setRequestExtension(String requestExtension) {
        this.requestExtension = requestExtension;
    }

    public String getIssueReturnDate() {
        return issueReturnDate;
    }

    public void setIssueReturnDate(String issueReturnDate) {
        this.issueReturnDate = issueReturnDate;
    }

    public RequestEnddatedto(String username, String booktitle, String requestExtension, String issueReturnDate, Long issueId) {

        this.username = username;
        this.booktitle = booktitle;
        this.requestExtension = requestExtension;
        this.issueReturnDate = issueReturnDate;
        this.issueId = issueId;
    }

}