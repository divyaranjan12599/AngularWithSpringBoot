package lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lms.dto.BookRequestDto;
import lms.entities.RequestBookDetails;
import lms.services.BookRequestService;

/**
 * This controller class is the final destination that a web request can reach.
 * Ater being invoked the controller method start process the web request by
 * interacting with BookRequestService.
 *
 * @author pragya.singh
 */

@RestController
public class RequestBookController {

	@Autowired
	private BookRequestService bookRequestService;

	@PostMapping("/requestbook/{userId}")
	public ResponseEntity<RequestBookDetails> addRequestBookDetails(@RequestBody RequestBookDetails requestBookDetails,
			@PathVariable("userId") int id) {
		try {
			return ResponseEntity.ok(bookRequestService.addrequestBookDetails(requestBookDetails, id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@GetMapping("/requestBookdto")
	public List<BookRequestDto> getAllData() {
		List<BookRequestDto> bookRequestDtos = bookRequestService.getAllRequestBook();
		return bookRequestDtos;
	}
	
	@GetMapping("/requestBookdto/{userId}")
	public List<BookRequestDto> getRequestData(@PathVariable("userId") long id) {
		List<BookRequestDto> bookRequestDtos = bookRequestService.getRequestByUserId(id);
		return bookRequestDtos;
	}

	@GetMapping("/isAccepted/admin/{requestId}/{isActive}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public BookRequestDto getStatus(@PathVariable("requestId") long requestId,
			@PathVariable(name = "isActive") int isActive) {
		BookRequestDto bookRequestDto = bookRequestService.updatestatus(requestId, isActive);
		return bookRequestDto;
	}

	@GetMapping("/requestbook")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<RequestBookDetails> getBookRequestDetails() {
		return bookRequestService.getallbookRequest();
	}

	@GetMapping("/requestbookdetail/{userId}")
	public List<RequestBookDetails> getUserDetails(@PathVariable("userId") long id) {
		return bookRequestService.getAllRequestDetails(id);
	}

	@DeleteMapping("/deleterequestbook/{requestbookid}/{userid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteBookRequest(@PathVariable("userid") long id, @PathVariable("requestbookid") long requestBookId) {
		bookRequestService.deleteBookRequest(id, requestBookId);
	}

}