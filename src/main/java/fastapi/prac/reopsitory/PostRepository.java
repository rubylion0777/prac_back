package fastapi.prac.reopsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fastapi.prac.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
