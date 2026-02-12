package ricksciascia.u5d9.payloads;

import lombok.Generated;

import java.time.LocalDate;

public class AuthorPayload {
    private String name;
    private String surname;
    private String email;
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