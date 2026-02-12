package ricksciascia.u5d9.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ricksciascia.u5d9.entities.Author;
import ricksciascia.u5d9.exceptions.BadRequestException;
import ricksciascia.u5d9.exceptions.NotFoundException;
import ricksciascia.u5d9.payloads.AuthorPayload;
import ricksciascia.u5d9.repositories.AuthorRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class AuthorsService {
    private final AuthorRepository authorRepository;
    private final Cloudinary cloudinaryUploader;

    @Autowired
    public AuthorsService(AuthorRepository authorRepository, Cloudinary cloudinaryUploader) {
        this.authorRepository = authorRepository;
        this.cloudinaryUploader = cloudinaryUploader;
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
//        cerco tramite metodo prima che in caso di errore lancia anche eccezione
        Author trovato =  this.getAuthorById(authorId);
//        cancello
        this.authorRepository.delete(trovato);

    }

    public Author uploadAvatar(MultipartFile file, long authorId) {
        Author trovato = this.getAuthorById(authorId);
        try{
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            trovato.setAvatar(imageUrl);
            return authorRepository.save(trovato);
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
