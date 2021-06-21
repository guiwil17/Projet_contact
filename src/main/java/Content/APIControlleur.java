package Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIControlleur {
    //Initialisation des variables
    @Autowired
    private ContactRepository contactRepo;
    @Autowired
    private AdresseRepository adresseRepo;
    @Autowired
    private EmailRepository emailRepo;

    //Initialisation de la réponse de l'API en cas de connexion à la racine de l'API
    @GetMapping(value="/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String Home() {
        return "Bienvenue sur notre API";
    }

    //Contact
    //Génération de la liste de contact
    @GetMapping(value="/xml/contact", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Contact> findContact() {
        return contactRepo.findAll();
    }

    //Génération du contact {id}
    @GetMapping(value="/xml/contact/{id}", produces=MediaType.APPLICATION_XML_VALUE)
    public Contact findContact(@PathVariable Long id) {
        //Cette methode n'est pas la plus optimisé cependant, en utilisant findById(), il se peut qu'aucun ID ne soie envoyé et alors une erreur apparait
        List <Contact> listContact =  contactRepo.findAll();
        for (Contact contact:listContact) {

            if(contact.getId() == id){
                return contact;
            }

        }
        return new Contact();
    }

    // Suppression du contact {id}
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

    // Ajout du contact
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
    // Génération de la liste d'adresse
    @GetMapping(value="/xml/adresse", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Adresse> findAdresses() {
        return adresseRepo.findAll();
    }

    // Génération de l'adresse {id}
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

    // Suppression de l'adresse {id}
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

    // Ajout du adresse
    @PostMapping(value="/xml/adresse/add", produces=MediaType.APPLICATION_XML_VALUE)
    public Adresse updateAdresse(@RequestBody Adresse adresse) {
        return adresse;
    }


    // Email
    // Génération des emails

    @GetMapping(value="/xml/email", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Email> findEmails() {
        return emailRepo.findAll();
    }

    // Génération de l'email {id}
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