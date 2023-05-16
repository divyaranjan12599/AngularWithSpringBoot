package lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import lms.dto.BookDetailssenddto;
import lms.entities.BookDetails;
import lms.serviceImpl.BookDetailsServiceImpl;
import lms.services.BookDetailsService;

/*
  THIS CLASS CONTAINS THE URLS FOR CONTROLLER OF 
  ADD BOOK DETAILS ,LEND AND FOR COMMON SEARCH OF THE BOOK
 
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookDetailsController {

	public BookDetailsService bookDetailsService;
	
	@Autowired
	public BookDetailsServiceImpl bookDetailsServiceImpl;

	@Autowired
	public BookDetailsController(BookDetailsService bookDetailsService) {
		this.bookDetailsService = bookDetailsService;
	}
	

  @PostConstruct
  public void updatebookdetails() {
      bookDetailsServiceImpl.updatebookdetails();
  }
	

	public BookDetailsController() {

	}

	@PostMapping("/addbookDetails")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<BookDetails> addbookdetails(@RequestBody BookDetails bookDetails) {

		try {
			return new ResponseEntity<>(bookDetailsService.addbookdetails(bookDetails), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@RequestMapping(value = "/getbookdetails/{name}", method = RequestMethod.GET)
	// @PreAuthorize("hasAuthority('ADMIN')")
	public List<BookDetailssenddto> getbookdetails(@PathVariable("name") String name) {
		return bookDetailsService.getallbookdetails(name);
	}

	@GetMapping("/getbookdetailsbyid/{id}")
	public BookDetailssenddto getbookdetailsbyid(@PathVariable("id") long id) {
		return bookDetailsService.getbookdetailsbyid(id);

	}

	@RequestMapping(value = "/getevrybooks", method = RequestMethod.GET)
	public List<BookDetailssenddto> geteverybookdetails() {
		return bookDetailsService.geteverybookdetails();

	}
	
	@RequestMapping(value = "/getcategories/{name}", method = RequestMethod.GET)
	public List<String> getcategories(@PathVariable("name") String name){
		//System.out.println(name);
		return bookDetailsService.getcategories(name);

	}
	

}
