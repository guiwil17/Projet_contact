package Content;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Content.Adresse;
import Content.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ContactAdresse",
            joinColumns = @JoinColumn(name = "id_contact"),
            inverseJoinColumns = @JoinColumn(name = "id_adresse")
    )

    private List<Adresse> adresses;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="emailid")
    private Email mailProfessionnel;


    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="emailid2")
    private Email mailPersonnel;


    public Contact(String firstName, String lastName, Email mailPersonnel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailPersonnel = mailPersonnel;
    }

    public Contact(Long id, String firstName, String lastName, List<Adresse> adresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
    }

    public Contact(Long id, String firstName, String lastName, List<Adresse> adresses, Email mailProfessionnel, Email mailPersonnel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.mailProfessionnel = mailProfessionnel;
        this.mailPersonnel = mailPersonnel;
    }

    public Contact(String firstName, String lastName, List<Adresse> adresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
    }

    public Contact(String firstName, String lastName, List<Adresse> adresses, Email mailProfessionnel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.mailProfessionnel = mailProfessionnel;
    }

    public Contact(String firstName, String lastName, List<Adresse> adresses, Email mailProfessionnel, Email mailPersonnel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.mailProfessionnel = mailProfessionnel;
        this.mailPersonnel = mailPersonnel;
    }

    public Email getMailPersonnel() {
        return mailPersonnel;
    }

    public void setMailPersonnel(Email mailPersonnel) {
        this.mailPersonnel = mailPersonnel;
    }

    public Email getMailProfessionnel() {
        return mailProfessionnel;
    }

    public void setMailProfessionnel(Email mailProfessionnel) {
        this.mailProfessionnel = mailProfessionnel;
    }




    protected Contact() {}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%d, firstName='%s', lastName='%s', adresses='%s', mailpro='%s', mailperso='%s']",
                id, firstName, lastName, adresses, mailProfessionnel, mailPersonnel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
