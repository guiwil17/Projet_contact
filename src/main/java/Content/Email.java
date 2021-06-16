package Content;
import Content.Contact;

import javax.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "mail",unique=true)
    public String mail;

    protected Email() {}


    public Email(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, mail='%s']",
                id, mail);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

}