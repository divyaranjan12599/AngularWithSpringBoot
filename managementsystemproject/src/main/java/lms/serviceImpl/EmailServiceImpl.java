package lms.serviceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lms.entities.BookIssueDetails;
import lms.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * This class will override the method of {@link EmailService} and also used to
 * create body for Accept End Date , Reject End Date and Issue Book.
 * 
 * @author ashutosh.baranwal , sparsh.gupta
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private BookIssueDetails bookIssueDetails;
	
	
	public EmailServiceImpl(JavaMailSender mailSender1) {
		this.mailSender = mailSender1;
	}

	StringBuilder message;

	@Override
	public void sendEmail(String message, String subject, String to) throws MessagingException {
		MimeMessage emailmMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(emailmMessage, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(message, true);
		mailSender.send(emailmMessage);
	}

	public void acceptEndDateEmailSender() throws MessagingException {
		String subject = "Regarding End Date Request";
		String to = bookIssueDetails.getUserDetail().getEmail();
		message=new StringBuilder();
		message.append("Dear, "+"<br />");
		message.append(bookIssueDetails.getUserDetail().getUserName());
		message.append(" your request for extending end date of ");
		message.append(bookIssueDetails.getBookDetails().getBookName());
		message.append(" is accepted!");
		sendEmail(message.toString(), subject, to);
	}

	public void rejectEndDateEmailSender() throws MessagingException {
		String subject = "Regarding End Date Request";
		String to = bookIssueDetails.getUserDetail().getEmail();
		message=new StringBuilder();
		message.append("Dear, "+"<br />");
		message.append(bookIssueDetails.getUserDetail().getUserName());
		message.append(" your request for extending end date of ");
		message.append(bookIssueDetails.getBookDetails().getBookName());
		message.append(" is rejected!");
		sendEmail(message.toString(), subject, to);
	}

	public void issueBookEmailSender() {
		String subject = "Regarding Book Issue Request";
		String to = bookIssueDetails.getUserDetail().getEmail();
		message=new StringBuilder();
		message.append("Dear, "+"<br />");
		message.append(bookIssueDetails.getUserDetail().getUserName());
		message.append(" your request for issuing ");
		message.append(" has been completed . Enjoy Reading! And kindly return the book before ");
		message.append(bookIssueDetails.getIssueEndDate() + " . ThankYou!");
		try {
			sendEmail(message.toString(), subject, to);
		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	public void setBookIssueDetails(BookIssueDetails bookIssueDetails) {
		this.bookIssueDetails = bookIssueDetails;
	}

	@Override
	public void forgetPasswordSendEMail(String email, String resetPasswordLink) throws MessagingException {

		String subject = "Here is your Reset Password Link";
		message=new StringBuilder();
		message.append("Dear, "+"<br />");
		message.append("You have requested to reset your password. ");
		message.append("Click the link below to change your password: "+"<br />");
		message.append(resetPasswordLink+"<br />");
		message.append("<b> Note </b>" +":- This link is valid only for 10 minutes"+"<br/>");
		message.append(" Ignore this email if you do remember your password, or you have not made the request.");
		sendEmail(message.toString(), subject, email);
	}

}
