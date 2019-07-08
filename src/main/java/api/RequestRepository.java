package api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    // custom query to search to blog post by title or content
//    List<Request> findByTitleContainingOrContentContaining(String text, String textAgain);

}
