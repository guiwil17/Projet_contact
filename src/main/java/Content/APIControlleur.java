package Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class APIControlleur {

    @Autowired
    private OptionContact contactRepo;
    @Autowired
    private OptionAdresse adresseRepo;
    @Autowired
    private EmailRepository emailRepo;
    private static final Logger log = LoggerFactory.getLogger(ContactData.class);

    @GetMapping(value="/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Contact> findCities() {
        log.info("xml");
        for (Contact contact:
        contactRepo.findAll()) {
            log.info(contact.toString());
        }
        return contactRepo.findAll();
    }

    @GetMapping(value="/xml/contact/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public Contact findContact(@PathVariable Long id) {


        return new Contact();
    }
}