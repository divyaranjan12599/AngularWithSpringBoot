package lms.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import lms.entities.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lms.dto.BookIssueDetailsDto;
import lms.entities.BookDetails;
import lms.entities.BookIssueDetails;
import lms.repositories.BookIssueRepository;
import lms.repositories.BookRepository;
import lms.repositories.UserDetailsRepository;
import lms.services.BookIssueService;

@Service
public class BookIssueServiceImpl implements BookIssueService {

	UserDetailsRepository userDetailsRepository;

	BookRepository bookRepository;

	BookIssueRepository bookIssueRepository;

	EmailServiceImpl emailServiceImpl;

	@Autowired
	RequestEndDateServiceImpl requestEndDateServiceImpl;

	@Autowired
	public BookIssueServiceImpl(UserDetailsRepository userDetailsRepository, BookRepository bookRepository,
			BookIssueRepository bookIssueRepository, EmailServiceImpl emailServiceImpl) {
		this.userDetailsRepository = userDetailsRepository;
		this.bookRepository = bookRepository;
		this.bookIssueRepository = bookIssueRepository;
		this.emailServiceImpl = emailServiceImpl;
	}

	@Override
	public String lend_book(long uid, long bid) {
		UserDetails user = userDetailsRepository.findById(uid).orElse(null);

		BookDetails book = bookRepository.findById(bid).orElse(null);

		synchronized (user) {
			synchronized (book) {
				if (book == null || user == null) {
					return "sorry you can't!!! ";
				}

				else {

					if (user.getLendCount() != 0 && book.getQuantity() != 0) {
						List<BookIssueDetails> bookIssueDetailslist = bookIssueRepository
								.findByBookDetailsAndUserDetail(book, user);

						if (bookIssueDetailslist.size() == 0) {
							BookIssueDetails bookIssueDetails = new BookIssueDetails();

							return issuebook(user, book, bookIssueDetails);
						} else if (bookIssueDetailslist.get(0).getReturnDate() != null) {
							BookIssueDetails bookIssueDetails = bookIssueRepository
									.findById(bookIssueDetailslist.get(0).getId()).orElse(new BookIssueDetails());

							return issuebook(user, book, bookIssueDetails);
						} else {
							return "You already taken the book....";
						}

					} else if (user.getLendCount() == 0) {
						return "Sorry limit exceeded!!!";
					} else if (book.getQuantity() == 0) {
						return "Sorry book is not available!!!";
					} else {
						return null;
					}
				}
			}
		}
	}

	@Override
	public boolean isBookLendedByUser(long uid, long bid) {
		UserDetails user = userDetailsRepository.findById(uid).orElse(null);
		BookDetails book = bookRepository.findById(bid).orElse(null);
		if (book == null || user == null) {
			return false;
		} else {
			List<BookIssueDetails> bookIssueDetailslist = bookIssueRepository.findByBookDetailsAndUserDetail(book,
					user);
			if (bookIssueDetailslist.size() != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String return_book(long issue_id) {
		BookIssueDetails bookIssueDetails = bookIssueRepository.findById(issue_id).orElse(null);
		UserDetails user = bookIssueDetails.getUserDetail();
		BookDetails book = bookIssueDetails.getBookDetails();

		synchronized (user) {
			synchronized (book) {

				user.setLendCount(user.getLendCount() + 1);
				book.setQuantity(book.getQuantity() + 1);
				userDetailsRepository.save(user);
				bookRepository.save(book);
				try {
					LocalDateTime localDateTime = LocalDateTime.now();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					bookIssueDetails.setReturnDate(formatter.parse(localDateTime.toString()));
					bookIssueRepository.save(bookIssueDetails);
					requestEndDateServiceImpl.deletetheExtension(bookIssueDetails);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				emailServiceImpl.setBookIssueDetails(bookIssueDetails);
				return "success";
			}
		}
	}

	@Override
	public List<BookIssueDetails> getAllIssuedBook() {
		List<BookIssueDetails> issueDetails = bookIssueRepository.findAll();
		return issueDetails;
	}

	@Override
	public int issued_book_count(long uid) {
		List<BookIssueDetailsDto> bookIssueDetails = this.getIssuedBookDetails("issued", uid);
		return bookIssueDetails.size();
	}

	@Override
	public int total_book_count(long uid) {
		List<BookIssueDetailsDto> bookIssueDetails = this.getIssuedBookDetails("total", uid);
		return bookIssueDetails.size();
	}

	@Override
	public int read_book_count(long uid) {
		List<BookIssueDetailsDto> bookIssueDetails = this.getIssuedBookDetails("read", uid);
		return bookIssueDetails.size();
	}

	@Override
	public int pending_book_count(long uid) {
		List<BookIssueDetailsDto> bookIssueDetails = this.getIssuedBookDetails("pending", uid);
		return bookIssueDetails.size();
	}

	@Override
	public BookIssueDetailsDto toDto(BookIssueDetails bookIssueDetails) {
		BookIssueDetailsDto bookIssueDetailsDto = new BookIssueDetailsDto();
		bookIssueDetailsDto.setIssue_id(bookIssueDetails.getId());
		List<String> authorsList = bookIssueDetails.getBookDetails().getAuthors().stream().map(m -> m.getAuthorName())
				.collect(Collectors.toList());
		bookIssueDetailsDto.setAuthors(authorsList);
		bookIssueDetailsDto.setBookTitle(bookIssueDetails.getBookDetails().getBookName());
		bookIssueDetailsDto.setBookId(bookIssueDetails.getBookDetails().getBookId());
		bookIssueDetailsDto.setIssueDate(bookIssueDetails.getIssueDate());
		bookIssueDetailsDto.setUserName(bookIssueDetails.getUserDetail().getUserName());
		bookIssueDetailsDto.setIssueEndDate(bookIssueDetails.getIssueEndDate());
		bookIssueDetailsDto.setReturnDate(bookIssueDetails.getReturnDate());
		bookIssueDetailsDto.setIsExtendable(bookIssueDetails.getIsExtendable());
		bookIssueDetailsDto.setIsWithDraw(bookIssueDetails.getIsWithDraw());
		return bookIssueDetailsDto;
	}

	@Override
	public List<BookIssueDetailsDto> getIssuedBookDetails(String str, long uid) {
		List<BookIssueDetailsDto> filteredData = new ArrayList<>();
		for (BookIssueDetails issueBookDetails : bookIssueRepository
				.findByUserDetail(userDetailsRepository.findById(uid).orElse(null))) {
			BookIssueDetailsDto bookIssueDetailsDto = new BookIssueDetailsDto();
			if (str.toLowerCase().equals("issued")) {
				if (issueBookDetails.getReturnDate() == null) {
					bookIssueDetailsDto = compareDate(issueBookDetails, issueBookDetails.getIssueDate());
					if (bookIssueDetailsDto != null) {
						filteredData.add(bookIssueDetailsDto);
					}
				}
			} else if (str.toLowerCase().equals("total")) {

				filteredData.add(this.toDto(issueBookDetails));
			} else if (str.toLowerCase().equals("read")) {
				if (issueBookDetails.getReturnDate() != null) {
					bookIssueDetailsDto = compareDate(issueBookDetails, issueBookDetails.getIssueDate());
					// System.out.println(filteredData);
					// System.out.println(bookIssueDetailsDto);
					if (bookIssueDetailsDto != null) {
						filteredData.add(bookIssueDetailsDto);
					}

				}
			} else if (str.toLowerCase().equals("pending")) {

				bookIssueDetailsDto = compareDate(issueBookDetails, issueBookDetails.getIssueEndDate());
				// System.out.println(filteredData);
				if (bookIssueDetailsDto != null & issueBookDetails.getReturnDate() == null) {
					filteredData.add(bookIssueDetailsDto);
				}

			}
		}
		System.out.println(filteredData);
		return filteredData.stream().sorted(new Comparator<BookIssueDetailsDto>() {
			@Override
			public int compare(BookIssueDetailsDto o1, BookIssueDetailsDto o2) {
				if (o1.getReturnDate() == null) {
					return -1;
				}

				else {
					return 0;
				}

			}
		}).collect(Collectors.toList());

	}

	@Override
	public List<BookIssueDetailsDto> getAllIssuedToAdmin() {
		List<BookIssueDetailsDto> bookIssueDetailsDtos = new ArrayList<>();
		bookIssueRepository.findAll().forEach(bookIssueDetails -> {
			BookIssueDetailsDto bookIssueDetailsDto = new BookIssueDetailsDto();
			bookIssueDetailsDto.setBookId(bookIssueDetails.getBookDetails().getBookId());
			bookIssueDetailsDto.setBookTitle(bookIssueDetails.getBookDetails().getBookName());
			bookIssueDetailsDto.setUserName(bookIssueDetails.getUserDetail().getUserName());
			bookIssueDetailsDto.setReturnDate(bookIssueDetails.getReturnDate());
			bookIssueDetailsDto.setIssue_id(bookIssueDetails.getId());
			bookIssueDetailsDto.setIssueDate(bookIssueDetails.getIssueDate());
			bookIssueDetailsDto.setIssueEndDate(bookIssueDetails.getIssueEndDate());
			List<String> authorslist = new ArrayList<>();
			bookIssueDetails.getBookDetails().getAuthors().forEach(a -> {
				authorslist.add(a.getAuthorName());
			});
			bookIssueDetailsDto.setAuthors(authorslist);
			bookIssueDetailsDtos.add(bookIssueDetailsDto);

		});
		return bookIssueDetailsDtos;
	}

	public BookIssueDetailsDto compareDate(BookIssueDetails issuedBookDetails, Date date) {
		LocalDateTime localDateTime = LocalDateTime.now();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// List<BookIssueDetailsDto> filteredData = new ArrayList<>();
		BookIssueDetailsDto bookIssueDetailsDto = null;
		try {
			if (date.compareTo(formatter.parse(localDateTime.plusDays(1).toString())) <= 0) {

				bookIssueDetailsDto = this.toDto(issuedBookDetails);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookIssueDetailsDto;
	}

	public String issuebook(UserDetails user, BookDetails book, BookIssueDetails bookIssueDetails) {

		synchronized (user) {
			synchronized (book) {
				user.setLendCount(user.getLendCount() - 1);
				book.setQuantity(book.getQuantity() - 1);
				userDetailsRepository.save(user);
				bookRepository.save(book);
				bookIssueDetails.setBookDetails(book);
				bookIssueDetails.setUserDetail(user);
				bookIssueDetails.setIsExtendable(true);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				LocalDateTime localDateTime = LocalDateTime.now();
				try {
					bookIssueDetails.setIssueDate(formatter.parse(localDateTime.plusDays(1).toString()));
					bookIssueDetails.setIssueEndDate(formatter.parse(localDateTime.plusDays(8).toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				bookIssueDetails.setReturnDate(null);
				bookIssueDetails.setIsWithDraw(false);
				bookIssueRepository.save(bookIssueDetails);
//				emailServiceImpl.setBookIssueDetails(bookIssueDetails);
//				emailServiceImpl.issueBookEmailSender();

				return "Book was issued successfully...";
			}
		}
	}

	@Override
	public BookIssueDetails findByIssuedId(Long issuedId) {
		return bookIssueRepository.findById(issuedId).get();
	}

	@Override
	public List<BookIssueDetails> findAllNotExtendable(Long userId) {
		List<BookIssueDetails> nonExtendableBooks = new ArrayList<>();
		for (BookIssueDetails bookIssueDetails : bookIssueRepository
				.findByUserDetail(userDetailsRepository.findById(userId).orElse(null))) {
			if (!bookIssueDetails.getIsExtendable()) {
				nonExtendableBooks.add(bookIssueDetails);
			}
		}
		return nonExtendableBooks;
	}

}