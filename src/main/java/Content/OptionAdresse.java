package Content;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface OptionAdresse extends CrudRepository<Adresse, Long>{

    List<Adresse> findByVille(String ville);

    Adresse findById(long id);

    List<Adresse> findAll();
}
