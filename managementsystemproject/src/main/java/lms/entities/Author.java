package lms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Author{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorId;

	private String authorName;
	public Author() {
		
	}

	public Author(long authorId, String authorName) {
		this.authorId = authorId;
		this.authorName = authorName;
		
	}

	public long getAuthorId(){
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
	}

}