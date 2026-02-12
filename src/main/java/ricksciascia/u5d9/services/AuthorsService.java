package ricksciascia.u5d9.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricksciascia.u5d9.entities.Author;
import ricksciascia.u5d9.exceptions.BadRequestException;
import ricksciascia.u5d9.exceptions.NotFoundException;
import ricksciascia.u5d9.payloads.AuthorPayload;
import ricksciascia.u5d9.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorsService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorPayload payload) {
//      validazione email tramite derived query
        this.authorRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L'email "+ author.getEmail() + " è già presente in DataBase!, riprova con un altra!");
        });
//      creo autore tramite payload
        Author autoreDaSalvare = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getDataDiNascita());
        autoreDaSalvare.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());
//      salvo
        Author salvato = this.authorRepository.save(autoreDaSalvare);
//      log
        System.out.println("L'utente " + salvato.getId() + " è stato aggiunto correttamente!");
//      return
        return salvato;
    }

    public List<Author> findAllAuthors() {

        return this.authorRepository.findAll();
    }

    public Author getAuthorById(long authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(()->new NotFoundException(authorId));

    }

    public Author updateAuthorById(long authorId, AuthorPayload payload) {
//        uso il metodo di sopra per trovare o meno l Autore
        Author trovato = this.getAuthorById(authorId);
//        validazione email come prima
        if(!trovato.getEmail().equals(payload.getEmail())) {
            this.authorRepository.findByEmail(payload.getEmail())
                    .ifPresent(author -> {
                        throw new BadRequestException("L'email "+author.getEmail()+ " è già in uso!");
                    });
        }
//        sovrascrivo trovato
        trovato.setName(payload.getName());
        trovato.setSurname(payload.getSurname());
        trovato.setEmail(payload.getEmail());
        trovato.setAvatar("https://ui-avatars.com/api?name=" + payload.getName() + "+" + payload.getSurname());
//        salvo
        Author autoreModificato = this.authorRepository.save(trovato);
//        log
        System.out.println("L'autore con id " + autoreModificato.getId() + " è stato aggiornato correttamente!");
//        ritorno
        return autoreModificato;
    }

    public void deleteAuthorById(long authorId) {
//        cerco tramite metodo prima che in caso di errore lancia anche eccezzione
        Author trovato =  this.getAuthorById(authorId);
//        cancello
        this.authorRepository.delete(trovato);

    }
}
