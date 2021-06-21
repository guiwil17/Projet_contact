package Content;

import javax.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "mail",unique=true)
    public String mail;

        protected Email() {}


    public Email(String mail) {
        this.mail = mail;
    }

    public Email(Long id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return String.format(
                "Email[id='%s',mail='%s']",
                id,mail);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}