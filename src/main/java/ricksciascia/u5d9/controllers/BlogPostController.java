package ricksciascia.u5d9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d9.entities.BlogPost;
import ricksciascia.u5d9.payloads.BlogPostPayload;
import ricksciascia.u5d9.services.BlogPostsService;

@RestController
@RequestMapping({"/blogs"})
public class BlogPostController {
    private final BlogPostsService blogPostsService;

    @Autowired
    public BlogPostController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload payload) {

        return this.blogPostsService.saveBlogPost(payload);
    }

    @GetMapping
    public Page<BlogPost> findAllBlogs(@RequestParam(defaultValue = "0")int page,
                                       @RequestParam(defaultValue = "2")int size,
                                       @RequestParam(defaultValue = "title")String orderBy ) {

        return this.blogPostsService.findAllBlogs(page,size,orderBy);
    }

    @GetMapping({"/{blogId}"})
    public BlogPost getBlog(@PathVariable long blogId) {
        return  this.blogPostsService.getBlogById(blogId);
    }
}
