package Content;

import javax.persistence.*;
import java.util.List;
import Content.Adresse;
import Content.Email;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Contact {
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

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "contact_id",referencedColumnName="id")
    private List<Email> emails;

    protected Contact() {}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%d, firstName='%s', lastName='%s', adresses='%s', mail='%s']",
                id, firstName, lastName, adresses, emails);
    }

    public Long getId() {
        return id;
    }
    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }

    public List<Email> getEmail() {
        return emails;
    }

    public void setEmail(List<Email> emails) {
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
