package ricksciascia.u5d9.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlogPostPayload {
    @NotBlank(message = "La categoria è obbligatoria")
    private String category;
    @NotBlank(message = "Il titolo del Blog è obbligatorio")
    @Size(max = 50, message = "Titolo troppo lungo, deve essere inferiore ai 50 caratteri")
    private String title;
    @NotBlank(message = "Il contenuto del Blog è obbligatorio")
    @Size(min = 5, message = "Contenuto troppo corto, deve essere maggiore di 5 caratteri")
    private String content;
    @NotBlank(message = "Il tempo di lettura è un valore obbligatorio")
    private int tempoDiLettura;
    @NotBlank(message = "L'id del Autore è un valore obbligatorio")
    private long authorId;
}
