package Content;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String Adresse;
    private String Code_postale;
    private String Ville;

    protected Adresse() {}

    public Adresse(String Adresse, String Code_postale, String Ville) {
        this.Adresse = Adresse;
        this.Code_postale = Code_postale;
        this.Ville = Ville;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, Adresse='%s', Code_postale='%s', Ville='%s']",
                id, Adresse, Code_postale, Ville);
    }

    public Long getId() {
        return id;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getCode_postale() {
        return Code_postale;
    }

    public String getVille() {
        return Ville;
    }
}
