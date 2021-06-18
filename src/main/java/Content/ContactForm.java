package Content;

import javax.persistence.*;
import java.util.List;

public class ContactForm {

    private Long Id;
    private String firstName;

    public ContactForm(Long id, String firstName, String lastName, List<Adresse> adresses, String emailProfessionnel) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.emailProfessionnel = emailProfessionnel;
    }

    private String lastName;



    private List<Adresse> adresses;

    private String emailProfessionnel;
    private Long idemailPro;

    private String emailPersonnel;
    private Long idemailPerso;

    public String getEmailPersonnel() {
        return emailPersonnel;
    }

    public void setEmailPersonnel(String emailPersonnel) {
        this.emailPersonnel = emailPersonnel;
    }

    public Long getIdemailPerso() {
        return idemailPerso;
    }

    public void setIdemailPerso(Long idemailPerso) {
        this.idemailPerso = idemailPerso;
    }

    public Long getIdemailPro() {
        return idemailPro;
    }

    public void setIdemailPro(Long idemailPro) {
        this.idemailPro = idemailPro;
    }

    public ContactForm(Long id, String firstName, String lastName, List<Adresse> adresses) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
    }

    public ContactForm(Long id, String firstName, String lastName, List<Adresse> adresses, String emailProfessionnel, Long idemailPro, String emailPersonnel, Long idemailPerso) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.emailProfessionnel = emailProfessionnel;
        this.idemailPro = idemailPro;
        this.emailPersonnel = emailPersonnel;
        this.idemailPerso = idemailPerso;
    }


    public ContactForm(Long id, String firstName, String lastName, List<Adresse> adresses, String emailPersonnel, Long idemailPerso) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.emailPersonnel = emailPersonnel;
        this.idemailPerso = idemailPerso;
    }

    protected ContactForm() {}

    public ContactForm(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public ContactForm(String firstName, String lastName, List<Adresse> adresses, String emailProfessionnel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.emailProfessionnel = emailProfessionnel;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[firstName='%s', lastName='%s', adresses='%s', mailperso='%s']",
                firstName, lastName, adresses,emailProfessionnel );
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public String getEmailProfessionnel() {
        return emailProfessionnel;
    }

    public void setEmailProfessionnel(String emailProfessionnel) {
        this.emailProfessionnel = emailProfessionnel;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
