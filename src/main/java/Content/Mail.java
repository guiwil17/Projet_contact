package Content;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String mail;

    protected Mail() {}

    public Mail(String adresse) {
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

    public String getAdresse() {
        return mail;
    }

}
