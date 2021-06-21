package Content;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Interface de communication avec la table adresse
public interface EmailRepository extends CrudRepository<Email, String> {

        Email findByMail(String mail);

        List<Email> findAll();
}