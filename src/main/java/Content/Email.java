package Content;
import Content.Contact;

import javax.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
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


    public void setEmail(String mail) {
        this.mail = mail;
    }

    public String getEmail() {
        return mail;
    }

}
