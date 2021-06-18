package Content;

import Content.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, String> {

        Email findByMail(String mail);

        List<Email> findAll();
}