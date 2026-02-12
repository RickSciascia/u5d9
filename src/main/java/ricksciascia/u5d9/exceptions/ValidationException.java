package ricksciascia.u5d9.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> messaggiDiErrore;

    public ValidationException(List<String> messaggiDiErrore) {
        super("Ci sono degli errori nel Payload per maggiori dettagli consulta la lista sotto:");
        this.messaggiDiErrore = messaggiDiErrore;
    }
}
