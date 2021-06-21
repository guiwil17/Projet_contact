package Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value="/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String Home() {
        return "Bienvenue sur notre API";
    }

    //Contact
    @GetMapping(value="/xml/contact", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Contact> findContact() {
        return contactRepo.findAll();
    }

    @GetMapping(value="/xml/contact/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public Contact findContact(@PathVariable Long id) {
        List <Contact> listContact =  contactRepo.findAll();
        for (Contact contact:listContact) {

            if(contact.getId() == id){
                return contact;
            }

        }
        return new Contact();
    }


    @GetMapping(value="/xml/contact/delete/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public List <Contact>  deleteContact(@PathVariable Long id) {
        List <Contact> listContact =  contactRepo.findAll();
        for (Contact contact:
                listContact) {
            if(contact.getId() == id){
                contactRepo.delete(contact);
            }

        }
        return contactRepo.findAll();
    }

    @PostMapping(value="/xml/contact/add", produces=MediaType.APPLICATION_XML_VALUE)
    public Contact updateContact(@RequestParam(name="id", required=true) Long id, @RequestParam(name="FirstName", required=true) String FirstName, @RequestParam(name="LastName", required=true) String LastName, @RequestParam(name="Adresses", required=false) String Adresses) {
        List <Contact> listContact =  contactRepo.findAll();
        for (Contact contact:
                listContact) {
            if(contact.getId() == id){
                return contact;
            }

        }
        return new Contact();
    }

    //Adresse
    @GetMapping(value="/xml/adresse", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Adresse> findAdresses() {
        return adresseRepo.findAll();
    }

    @GetMapping(value="/xml/adresse/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public Adresse findAdresse(@PathVariable Long id) {
        List <Adresse> listAdresse =  adresseRepo.findAll();
        for (Adresse adresse : listAdresse) {
            if(adresse.getId() == id){
                return adresse;
            }

        }
        return new Adresse();
    }

    @GetMapping(value="/xml/adresse/delete/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public List<Adresse>  deleteAdresse(@PathVariable Long id) {
        List <Adresse> listAdresse =  adresseRepo.findAll();
        for (Adresse adresse:
                listAdresse) {
            if(adresse.getId() == id){
                adresseRepo.delete(adresse);
            }

        }
        return adresseRepo.findAll();
    }

    @PostMapping(value="/xml/adresse/add", produces=MediaType.APPLICATION_XML_VALUE)
    public Adresse updateAdresse(@RequestBody Adresse adresse) {
        return adresse;
    }

    //Email
    @GetMapping(value="/xml/email", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Email> findEmails() {
        return emailRepo.findAll();
    }

    @GetMapping(value="/xml/email/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public Email findEmail(@PathVariable Long id) {
        List <Email> listEmail =  emailRepo.findAll();
        for (Email email : listEmail) {
            if(email.getId() == id){
                return email;
            }
        }
        return new Email();
    }


}