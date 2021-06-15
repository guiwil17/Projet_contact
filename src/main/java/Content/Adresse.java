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
    private String ligne;
    private String code_postale;
    private String ville;

    protected Adresse() {}

    public Adresse(String ligne, String code_postale, String ville) {
        this.ligne = ligne;
        this.code_postale = code_postale;
        this.ville = ville;
    }

    @Override
    public String toString() {
        return String.format(
                "Adresse[id=%d, Adresse='%s', Code_postale='%s', Ville='%s']",
                id, ligne, code_postale, ville);
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getAdresse() {
        return ligne;
    }

    public String getCode_postale() {
        return code_postale;
    }

    public String getVille() {
        return ville;
    }

    public String getTotal() {
        return  ligne + " " + ville + " " + code_postale;
    }
}
