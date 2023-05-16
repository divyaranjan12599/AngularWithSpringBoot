package lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lms.dto.BookReviewdto;
import lms.entities.BookReview;
import lms.entities.StarRating;
import lms.services.Bookreviewservices;

/*
 this controller is used for book review urls
 */

@RestController
public class BookReviewController {
	@Autowired
	private Bookreviewservices bookReviewService;

	@PostMapping("/addreview/")
	public BookReview addreviewdetails(@RequestBody BookReview bookReview) {
		System.out.println(bookReview);
		return bookReviewService.addreviewdetails(bookReview);
	}

	@PostMapping("/addreviewbyids/{User_id}/{Book_id}")
	public ResponseEntity<BookReview> addreviewdetailsbybookid(@RequestBody Map<Object, Object> map1,
			@PathVariable("User_id") long uid, @PathVariable("Book_id") long bid) {

		BookReview bookReview = new BookReview();
		bookReview.setStarRating((StarRating.values()[(int) map1.get("star")]));
		bookReview.setComments((String) map1.get("comments"));
		return new ResponseEntity<>(bookReviewService.addreviewbyids(bookReview, uid, bid), HttpStatus.OK);

	}

	@GetMapping("/getreviewbybookid/{id}")
	public List<BookReviewdto> getreviewbybookid(@PathVariable("id") long id) {
		return bookReviewService.getreviewbybookid(id);
	}

}