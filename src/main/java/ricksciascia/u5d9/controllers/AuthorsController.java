package ricksciascia.u5d9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d9.entities.Author;
import ricksciascia.u5d9.payloads.AuthorPayload;
import ricksciascia.u5d9.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping({"/authors"})
public class AuthorsController {
    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping
    public List<Author> getListaAutori() {

        return this.authorsService.findAllAuthors();
    }

    @GetMapping({"/{authorId}"})
    public Author getAutore(@PathVariable long authorId) {

        return this.authorsService.getAuthorById(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAutore(@RequestBody AuthorPayload payload) {

        return this.authorsService.saveAuthor(payload);
    }

    @PutMapping({"/{authorId}"})
    public Author editAutore(@RequestBody AuthorPayload payload, @PathVariable long authorId) {
        return this.authorsService.updateAuthorById(authorId, payload);
    }

    @DeleteMapping({"/{authorId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutore(@PathVariable long authorId) {

        this.authorsService.deleteAuthorById(authorId);
    }
}