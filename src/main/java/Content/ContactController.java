package Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.graalvm.compiler.debug.TTY.print;

@Controller
@SessionAttributes("valueSession2")
public class ContactController {

    @Autowired
    private OptionContact contactRepo;
    @Autowired
    private OptionAdresse adresseRepo;
    @Autowired
    private EmailRepository emailRepo;
    private static final Logger log = LoggerFactory.getLogger(ContactData.class);

    @GetMapping("/contact")
    public String contact(@RequestParam(name="erreur", required=false, defaultValue="false") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue="World") String fooCookie, HttpServletResponse response) {
        model.addAttribute("name", contactRepo.findAll());
        //Cookie cookie = new Cookie("ddd", contactRepo.findById(2).toString());
        //response.addCookie(cookie);
        model.addAttribute("addrs", adresseRepo.findAll());
        log.info(name);
        model.addAttribute("erreur", name);
        model.addAttribute("ContactForm", new ContactForm());
        model.addAttribute("adresse", new Adresse());
        return "contact";
    }

    @ModelAttribute("liste")
    public List<Contact> getSomeList(){
        return contactRepo.findAll();
    }

    @ModelAttribute("listeAdresse")
    public List<Adresse> getSomeListAdresse(){
        return adresseRepo.findAll();
    }

    @PostMapping("/Modifcontact")
    public String modifContact(@ModelAttribute Contact contact, Model model) {

        /*
        String f = contact.getFirstName();
        Integer id = Integer.parseInt(f);
        contact = contactRepo.findById(id);

         */

        Long id = contact.getId();

       List<Contact> listcontact = contactRepo.findAll();

        for (Contact cont : listcontact){
            if(contact.getId() == cont.getId()){
               contact = cont;
            }

        }
        log.info(contact.toString());
        ContactForm contactForm = new ContactForm(contact.getId(),contact.getFirstName(), contact.getLastName(), contact.getAdresses(), contact.getMailPersonnel().getMail(), contact.getMailPersonnel().getId());

        if (contact.getMailProfessionnel() != null){
            contactForm.setEmailProfessionnel(contact.getMailProfessionnel().getMail());
            contactForm.setIdemailPro(contact.getMailProfessionnel().getId());

        }
        else{
            contactForm.setEmailProfessionnel("");
            contactForm.setIdemailPro(-1L);

        }
        model.addAttribute("contact", contactForm);
        model.addAttribute("modifcontact", new ContactForm());
        model.addAttribute("addrs", adresseRepo.findAll());
        return "update";
    }

    @PostMapping("/contact")
    public String updateContact(@ModelAttribute ContactForm contactform, Model model) {
        log.info(contactform.toString());
        Email emailPerso = new Email(contactform.getIdemailPerso(),contactform.getEmailPersonnel());
        log.info(emailPerso.toString());
        Contact contact = new Contact(contactform.getFirstName(), contactform.getLastName(), contactform.getAdresses());

        if(contactform.getIdemailPro() != -1L){

            Email email = new Email(contactform.getEmailProfessionnel());
            contact.setMailProfessionnel(email);
            contact.setMailPersonnel(emailPerso);
            try{
                emailRepo.save(email);
                emailRepo.save(emailPerso);
                contactRepo.save(contact);
            }
            catch (Exception e){
                log.info(e.toString());
                return "redirect:/contact?erreur=true";
            }
        }
        else{
            log.info("ici");
            contact.setMailPersonnel(emailPerso);
            log.info(contact.toString());
            try{
                emailRepo.save(emailPerso);
                contactRepo.save(contact);
            }
            catch (Exception e){
                log.info(e.toString());
                return "redirect:/contact?erreur=true";
            }
        }
        log.info(contact.toString());

        List<Email> tot = emailRepo.findAll();
        for (Email con:
                tot) {
            log.info(con.toString());
        }

        model.addAttribute("name", contactRepo.findAll());
        model.addAttribute("contact", new ContactForm());
        model.addAttribute("contact", contact);
        model.addAttribute("addrs", adresseRepo.findAll());
        return "contact";
    }

    @GetMapping("/Deletecontact")
    public String deleteContact(@ModelAttribute Contact contact, Model model) {
        contactRepo.delete(contact);
        return "redirect:/contact";
    }

    @PostMapping("/Addcontact")
    public String addContact(@ModelAttribute ContactForm contactform, Model model) {
        log.info(contactform.toString());
        Email emailPerso = new Email(contactform.getEmailPersonnel());

        Contact contact = new Contact(contactform.getFirstName(), contactform.getLastName(), contactform.getAdresses());
        if(contactform.getEmailProfessionnel() != ""){
            Email email = new Email(contactform.getEmailProfessionnel());
            contact.setMailProfessionnel(email);
            contact.setMailPersonnel(emailPerso);
        }
        else {
            contact.setMailPersonnel(emailPerso);
        }
        log.info(emailPerso.toString());


        //contact.setMailProfessionnel(email);

        try{
        contactRepo.save(contact);
    }
        catch (Exception e){
        log.info(e.toString());
            return "redirect:/contact?erreur=true";
    }
        log.info(contact.toString());

        List<Contact> tot = contactRepo.findAll();
        for (Contact con:
             tot) {
            log.info(con.toString());
        }

        return "redirect:/contact";

    }

    @GetMapping("/adresses")
    public String adresses(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue = "hello") String fooCookie, HttpServletResponse response) {
        model.addAttribute("adresses", adresseRepo.findAll());
        model.addAttribute("adresse", new Adresse());
        return "adresses";
    }

    @GetMapping("/adresses/delete")
    public String deleteAdresse(@ModelAttribute Adresse adresse, Model model) {
        log.info(adresse.toString());
        adresseRepo.delete(adresse);
        return "redirect:/adresses";
    }

    @PostMapping("/adresses/add")
    public String addAdresse(@ModelAttribute Adresse adresse, Model model) {
        adresseRepo.save(adresse);
        return "redirect:/adresses";
    }

    @PostMapping("/adresses/update")
    public String updateAdresse(@ModelAttribute Adresse adresse, Model model) {
        adresseRepo.save(adresse);
        return "redirect:/adresses";
    }

    @PostMapping("/adresses/modif")
    public String modifAdresse(@ModelAttribute Adresse adresse, Model model) {
        log.info(adresse.toString());
        /*
        String f = contact.getFirstName();
        Integer id = Integer.parseInt(f);
        contact = contactRepo.findById(id);

         */
        model.addAttribute("adresse", adresse);
        model.addAttribute("modifadresse", new Adresse());

        return "updateAdresse";
    }


}
