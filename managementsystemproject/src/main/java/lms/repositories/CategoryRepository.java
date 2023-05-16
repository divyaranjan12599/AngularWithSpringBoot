package lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lms.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByCategoryName(String name);
    
    
    @Query(value="select category_name from category where category_name<>?1",nativeQuery = true)
    public List<String>  getcategorynames(String name);

}
