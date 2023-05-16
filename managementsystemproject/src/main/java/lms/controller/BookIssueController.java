package lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lms.dto.BookIssueDetailsDto;
import lms.entities.BookIssueDetails;
import lms.serviceImpl.BookIssueServiceImpl;

@RestController
public class BookIssueController {

	@Autowired
	BookIssueServiceImpl bookIssueServiceImpl;

	@GetMapping("/lend/{user_id}/{book_id}")
	public String lend_book(@PathVariable("user_id") long uid, @PathVariable("book_id") long bid) {
		String status = "done";
		status = bookIssueServiceImpl.lend_book(uid, bid);
		return status;
	}

	@GetMapping("/return/{issue_id}")
	public String return_book(@PathVariable("issue_id") long id) {
		String status = bookIssueServiceImpl.return_book(id);
		 //String status="done";
		return status;
	}

	@CrossOrigin(origins = "http://localhost:4200/dashboard")
	@GetMapping("/totalBooks/{user_id}")
	public List<BookIssueDetailsDto> getAllBooks(@PathVariable("user_id") long uid) {
		System.out.println(bookIssueServiceImpl.getIssuedBookDetails("total", uid));
		return bookIssueServiceImpl.getIssuedBookDetails("total", uid);
	}

	@CrossOrigin(origins = "http://localhost:4200/dashboard")
	@GetMapping("/issuedBooks/{user_id}")
	public List<BookIssueDetailsDto> getIssuedBooks(@PathVariable("user_id") long uid) {
		return bookIssueServiceImpl.getIssuedBookDetails("issued", uid);
	}

	@CrossOrigin(origins = "http://localhost:4200/dashboard")
	@GetMapping("/readBooks/{user_id}")
	public List<BookIssueDetailsDto> getReadBooks(@PathVariable("user_id") long uid) {
		return bookIssueServiceImpl.getIssuedBookDetails("read", uid);
	}

	@CrossOrigin(origins = "http://localhost:4200/dashboard")
	@GetMapping("/pendingBooks/{user_id}")
	public List<BookIssueDetailsDto> getPendingBooks(@PathVariable("user_id") long uid) {
		return bookIssueServiceImpl.getIssuedBookDetails("pending", uid);
	}

	@CrossOrigin(origins = "http://localhost:4200/dashboard")
	@GetMapping("/getAllIssued")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<BookIssueDetailsDto> getAllIssues() {
		return bookIssueServiceImpl.getAllIssuedToAdmin();
	}
	
	@CrossOrigin
	@GetMapping("/findByIssueId/{issueId}")
	public BookIssueDetails findExtensionByIssueId(@PathVariable("issueId") Long issuedId){
		return bookIssueServiceImpl.findByIssuedId(issuedId);
	}
	
	@CrossOrigin
	@GetMapping("/findNonExtendable/{userId}")
	public List<BookIssueDetails> findNonExtendableBooks(@PathVariable("userId") Long userId){
		return bookIssueServiceImpl.findAllNotExtendable(userId);
	}

	@CrossOrigin
	@GetMapping("/isBookLended/{userId}/{bookId}")
	public boolean isBookLendedByUser(@PathVariable("userId") long uid,@PathVariable("bookId") long bid){
		return bookIssueServiceImpl.isBookLendedByUser(uid,bid);
	}
}