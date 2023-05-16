package lms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RequestExtension {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestextensionId;


    @ManyToOne(targetEntity = BookIssueDetails.class)
    @JoinColumn(name = "Issue_id", referencedColumnName = "id")
    private BookIssueDetails issueId;

    public long getRequestextensionId() {
        return requestextensionId;
    }

    public void setRequestextensionId(long requestextensionId) {
        this.requestextensionId = requestextensionId;
    }

    public RequestExtension(long requestextensionId, BookIssueDetails issueId) {
        this.requestextensionId = requestextensionId;
        this.issueId = issueId;
    }

    public BookIssueDetails getIssueId() {
        return issueId;
    }

    public void setIssueId(BookIssueDetails issueId) {
        this.issueId = issueId;
    }

    public RequestExtension() {

    }

	@Override
	public String toString() {
		return "RequestExtension [requestextensionId=" + requestextensionId + ", issueId=" + issueId + "]";
	}
    


}
