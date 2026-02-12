package ricksciascia.u5d9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ricksciascia.u5d9.entities.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
