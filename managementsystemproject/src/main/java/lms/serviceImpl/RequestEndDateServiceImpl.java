package lms.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import lms.dto.RequestEnddatedto;
import lms.entities.BookIssueDetails;
import lms.entities.RequestExtension;
import lms.repositories.BookIssueRepository;
import lms.repositories.RequestExtensionRepository;
import lms.services.RequestEndDateService;

@Service
public class RequestEndDateServiceImpl implements RequestEndDateService {

	private BookIssueRepository bookIssueRepository;

	private EmailServiceImpl emailServiceImpl;

	private RequestExtensionRepository requestExtensionRepository;

	@Autowired
	public RequestEndDateServiceImpl(BookIssueRepository bookIssueRepository, EmailServiceImpl emailServiceImpl,
			RequestExtensionRepository requestExtensionRepository) {
		super();
		this.bookIssueRepository = bookIssueRepository;
		this.emailServiceImpl = emailServiceImpl;
		this.requestExtensionRepository = requestExtensionRepository;
	}

	@Override
	public List<RequestEnddatedto> getbookextensions() 
	{

		List<RequestEnddatedto> requestEnddatedtos = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(requestExtensionRepository.findAll());
		requestExtensionRepository.findAll().forEach(requestextension -> {
			BookIssueDetails bookIssueDetails = requestextension.getIssueId();
			RequestEnddatedto requestEnddatedto = new RequestEnddatedto(bookIssueDetails.getUserDetail().getUserName(),
					bookIssueDetails.getBookDetails().getBookName(),
					formatter.format(convertDate(bookIssueDetails.getIssueEndDate()).getTime()),
					formatter.format(bookIssueDetails.getIssueEndDate()), bookIssueDetails.getId());
			requestEnddatedtos.add(requestEnddatedto);
		});

		return requestEnddatedtos;
	}

	@Override
	public String acceptandreject(long id, int value) throws MessagingException {
		BookIssueDetails bookIssueDetails = bookIssueRepository.findById(id).orElse(null);
		emailServiceImpl.setBookIssueDetails(bookIssueDetails);
		if (value == 0) {
			//emailServiceImpl.rejectEndDateEmailSender();
			deletetheExtension(bookIssueDetails);
			bookIssueDetails.setIsExtendable(false);
			bookIssueDetails.setIsWithDraw(false);
			bookIssueRepository.save(bookIssueDetails);
			return "You have Rejected the Request";
		}
		
		else {
			Date issueenddate = bookIssueDetails.getIssueEndDate();
			bookIssueDetails.setIssueEndDate(convertDate(issueenddate).getTime());
			bookIssueDetails.setIsWithDraw(false);
			bookIssueDetails.setIsExtendable(true);
			bookIssueRepository.save(bookIssueDetails);
			//emailServiceImpl.acceptEndDateEmailSender();
			deletetheExtension(bookIssueDetails);
			return "You have Accept the Request";
		}

	}

	@Override
	public String addRequestEndExtension(long issueId) {
		BookIssueDetails bookIssueDetails = bookIssueRepository.findById(issueId).orElse(null);
		if (bookIssueDetails == null) {
			return "bookIssue details was not";
		} else {
			List<RequestExtension> requestExtensions = requestExtensionRepository.findByissueId(bookIssueDetails);
			if (requestExtensions.size() != 0) {
				return "request was already submitted";
			} else {
				RequestExtension requestExtension = new RequestExtension();
				bookIssueDetails.setIsWithDraw(true);
				bookIssueDetails.setIsExtendable(false);
				bookIssueRepository.save(bookIssueDetails);
				requestExtension.setIssueId(bookIssueDetails);
				requestExtensionRepository.save(requestExtension);
				return "request was submitted for the extension of 10 days";

			}
		}

	}

	public void deletetheExtension(BookIssueDetails bookIssueDetails) {
		
		List<RequestExtension> requestExtensions = requestExtensionRepository.findByissueId(bookIssueDetails);
		requestExtensions.forEach(reqeusteExtension -> {
			requestExtensionRepository.deleteById(reqeusteExtension.getRequestextensionId());
		});

	}

	public Calendar convertDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 10);
		return c;
	}

	@Override
	public String withdrawRequestExtension(long issueId) {
		BookIssueDetails bookIssueDetails = bookIssueRepository.findById(issueId).orElse(null);
		if (bookIssueDetails == null) {
			return "bookIssue details was not";
		}
		else {
			List<RequestExtension> requestExtensions = requestExtensionRepository.findByissueId(bookIssueDetails);
			if (requestExtensions.size() != 0) {
				bookIssueDetails.setIsWithDraw(false);
				bookIssueDetails.setIsExtendable(false);
				bookIssueRepository.save(bookIssueDetails);
				deletetheExtension(bookIssueDetails);
				return "with draw was done completely";
			} else {
				return "first take the book";
			}
		}
		
	}
	
	
}
