package Content;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OptionContact extends CrudRepository<Contact, Long> {

    List<Contact> findByLastName(String lastName);

    Contact findById(long id);

    List<Contact> findAll();
}