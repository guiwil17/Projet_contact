import Content.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {

        List<Email> findByMail(String Mail);

        Email findById(long id);
}