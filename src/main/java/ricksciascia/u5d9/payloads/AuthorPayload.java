package ricksciascia.u5d9.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Generated;

import java.time.LocalDate;

public class AuthorPayload {
    @NotBlank(message = "Il nome è un campo obbligatorio")
    @Size(min = 2, max = 20, message = "il nome deve essere compreso tra 2 e 20 caratteri")
    private String name;
    @NotBlank(message = "Il cognome è un campo obbligatorio")
    @Size(min = 2, max = 20, message = "il cognome deve essere compreso tra 2 e 20 caratteri")
    private String surname;
    @NotBlank(message = "L'indirizzo email è un campo obbligatorio")
    @Email(message = "L'indirizzo email è in un formato non corretto!")
    private String email;
    @NotBlank(message = "La data di nascita è un campo obbligatorio")
    private LocalDate dataDiNascita;

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getSurname() {
        return this.surname;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public LocalDate getDataDiNascita() {
        return this.dataDiNascita;
    }

    @Generated
    public AuthorPayload(final String name, final String surname, final String email, final LocalDate dataDiNascita) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
    }

    @Generated
    public String toString() {
        String var10000 = this.getName();
        return "AuthorPayload(name=" + var10000 + ", surname=" + this.getSurname() + ", email=" + this.getEmail() + ", dataDiNascita=" + String.valueOf(this.getDataDiNascita()) + ")";
    }
}