package lms.dto;

import java.util.List;

/*
 THIS CLASS IS USED TO SEND THE BOOK DETAILS
 */

public class BookDetailssenddto {

	public long book_id;

	public String book_title;

	public long quantity;

	public List<String> authors;

	public List<String> category;

	public double avg_rating;
	
	public int rating_count;

	public int getRating_count() {
		return rating_count;
	}

	public void setRating_count(int rating_count) {
		this.rating_count = rating_count;
	}

	public BookDetailssenddto() {

	}

	public double getAvg_rating() {
		return avg_rating;
	}

	public void setAvg_rating(double avg_rating) {
		this.avg_rating = avg_rating;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}
	

	public BookDetailssenddto(long book_id, String book_title, long quantity, List<String> authors,
			List<String> category, double avg_rating, int rating_count) {
		super();
		this.book_id = book_id;
		this.book_title = book_title;
		this.quantity = quantity;
		this.authors = authors;
		this.category = category;
		this.avg_rating = avg_rating;
		this.rating_count = rating_count;
	}

	@Override
	public String toString() {
		return "BookDetailssenddto [book_id=" + book_id + ", book_title=" + book_title + ", quantity=" + quantity
				+ ", authors=" + authors + ", category=" + category + "]";
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

}