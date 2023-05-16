package lms.services;

import java.util.List;

import lms.dto.BookRequestDto;
import lms.entities.RequestBookDetails;

/**
 * this interface will be used to connect BookRequestService
 *
 * @author pragya.singh
 */

public interface BookRequestService {

	RequestBookDetails addrequestBookDetails(RequestBookDetails requestBookDetails , long id);
	
	List<RequestBookDetails> getallbookRequest();
	
	void deleteBookRequest(long userId ,long id);
	
	public List<RequestBookDetails> getAllRequestDetails(long id);
	
	public List<BookRequestDto> getAllRequestBook();
	
	public BookRequestDto updatestatus(long requestId,int isActive);
	
	public List<BookRequestDto> getRequestByUserId(long uid);
	
}