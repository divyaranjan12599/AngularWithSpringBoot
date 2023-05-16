package lms.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class BookDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;

	private long quantity;

	private String bookName;

	@ManyToMany(targetEntity = Category.class)
	//@JoinColumn(name="category_id",referencedColumnName = "cid")
	@JoinTable(name = "Book_Category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "cid"))
	private List<Category> category;

	
	@ManyToMany(targetEntity = Author.class)
	@JoinTable(name = "Book_Author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;

	public long getBookId(){
		return bookId;
	}

	public void setBookId(long bookId){
		this.bookId = bookId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public List<Author> getAuthors(){
		
		return authors;
	}

	public void setAuthors(List<Author> authors){
		
		this.authors = authors;
	}

	

	@Override
	public String toString() {
		return "BookDetails [bookId=" + bookId + ", quantity=" + quantity + ", bookName=" + bookName + ", category="
				+ category + ", authors=" + authors + "]";
	}

	

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	

	public BookDetails(long bookId, long quantity, String bookName, List<Category> category, List<Author> authors) {
		this.bookId = bookId;
		this.quantity = quantity;
		this.bookName = bookName;
		this.category = category;
		this.authors = authors;
	}

	public BookDetails(){
	
	}

}