package lms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lms.entities.BookDetails;

@Repository
public interface BookRepository extends JpaRepository<BookDetails, Long> {


    @Query(value = "{call getbookdetails(?1)}", nativeQuery = true)
    public List<Long> getbookdetails(String name);
    
    
    //public List<BookDetails> findByAuthorsAndBookName(List<Author> author,String name);
    
    


}
