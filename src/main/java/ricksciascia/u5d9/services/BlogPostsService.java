package ricksciascia.u5d9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ricksciascia.u5d9.entities.Author;
import ricksciascia.u5d9.entities.BlogPost;
import ricksciascia.u5d9.exceptions.NotFoundException;
import ricksciascia.u5d9.payloads.BlogPostPayload;
import ricksciascia.u5d9.repositories.BlogPostRepository;

@Service
public class BlogPostsService {
    private final BlogPostRepository blogPostRepository;
    private final AuthorsService authorsService;

    @Autowired
    public BlogPostsService(BlogPostRepository blogPostRepository, AuthorsService authorsService) {
        this.blogPostRepository = blogPostRepository;
        this.authorsService = authorsService;
    }

    public BlogPost saveBlogPost(BlogPostPayload payload) {
//        innanzitutto dovrei prendere l idAuthor del payload e trovare l oggetto Author da DB in modo da allegarlo
        long authorId = payload.getAuthorId();
//        trovo l oggetto autoreBlog per poi allegarlo
        Author autoreBlog = this.authorsService.getAuthorById(authorId);
//        creo nuovo blog
        BlogPost newBlog = new BlogPost(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getTempoDiLettura(), autoreBlog);
//        salvo
        BlogPost salvato = this.blogPostRepository.save(newBlog);
//        loga
        System.out.println("Blog " + salvato.getTitle() + " dell'autore: "+ salvato.getAuthor() + " salvato correttamente");
//        ritorno
        return salvato;
    }

    public Page<BlogPost> findAllBlogs(int page, int size, String orderBy) {
        if(size > 25) size = 25;
        if(size < 0) size = 5;
        if(page<0) page = 0;
//        if(!(orderBy.equals("title") || orderBy.equals("category"))) orderBy = "tempoDiLettura";
        if(!orderBy.equals("title") && !orderBy.equals("category")) orderBy = "tempoDiLettura";
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy).ascending());
        return this.blogPostRepository.findAll(pageable);
    }

    public BlogPost getBlogById(long blogId) {
        return this.blogPostRepository.findById(blogId).orElseThrow(()-> new NotFoundException(blogId));
    }
}
