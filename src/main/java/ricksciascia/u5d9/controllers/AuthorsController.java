package ricksciascia.u5d9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ricksciascia.u5d9.entities.Author;
import ricksciascia.u5d9.exceptions.ValidationException;
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
    public Author saveAutore(@RequestBody @Validated AuthorPayload payload, BindingResult validationResult) {

        if(validationResult.hasErrors()) {
            List<String> listaErrori = validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(listaErrori);
        } else {
            return this.authorsService.saveAuthor(payload);
        }
    }

    @PutMapping({"/{authorId}"})
//    NOTA PERSONALE subito dopo @Validated ci va l'oggetto BindingResult altrimenti se c'è di mezzo un altro parametro tipo l authorId non capisce l'associazione
    public Author editAutore(@RequestBody @Validated AuthorPayload payload, BindingResult validationResult, @PathVariable long authorId) {
        if(validationResult.hasErrors()) {
            List<String> listaErrori = validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(listaErrori);
        } else {
            return this.authorsService.updateAuthorById(authorId, payload);
        }

    }

    @DeleteMapping({"/{authorId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAutore(@PathVariable long authorId) {

        this.authorsService.deleteAuthorById(authorId);
    }
}