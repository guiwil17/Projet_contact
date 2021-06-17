package Content;

import Content.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, String> {

        Email findByMail(String mail);

        List<Email> findAll();
}