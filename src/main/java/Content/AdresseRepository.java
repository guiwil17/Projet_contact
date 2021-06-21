package Content;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Interface de communication avec la table adresse
public interface AdresseRepository extends CrudRepository<Adresse, Long>{

    List<Adresse> findByVille(String ville);

    Adresse findById(long id);

    List<Adresse> findAll();
}
