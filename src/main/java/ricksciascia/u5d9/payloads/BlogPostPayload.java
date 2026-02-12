package ricksciascia.u5d9.payloads;

import jakarta.validation.constraints.Min;
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
    @Size(min = 5, max = 50, message = "Titolo deve essere compreso tra 5 e 50 caratteri")
    private String title;
    @NotBlank(message = "Il contenuto del Blog è obbligatorio")
    @Size(min = 5, message = "Contenuto troppo corto, deve essere maggiore di 5 caratteri")
    private String content;
    @Min(value = 0 ,message = "Il tempo di lettura non può essere inferiore a 0")
    private int tempoDiLettura;
    @Min(value = 0 ,message = "L'autore non può avere Id inferiore a 0")
    private long authorId;
}
