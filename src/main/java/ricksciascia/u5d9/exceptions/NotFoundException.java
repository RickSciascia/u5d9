package ricksciascia.u5d9.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("il record con id: " + id + " non è stato trovato!");
    }
}
