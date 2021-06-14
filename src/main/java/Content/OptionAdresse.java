package Content;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface OptionAdresse extends CrudRepository<Adresse, Long>{

    List<Adresse> findByLastName(String lastName);

    Adresse findById(long id);

    List<Adresse> findAll();
}
