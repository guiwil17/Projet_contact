package Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("valueSession2")
public class ContactController {

    @Autowired
    private OptionContact contactRepo;
    @Autowired
    private OptionAdresse adresseRepo;
    @Autowired
    private EmailRepository emailRepo;

    @GetMapping("/contact")
    public String contact(@RequestParam(name="erreur", required=false, defaultValue="false") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue="World") String fooCookie, HttpServletResponse response) {
        model.addAttribute("name", contactRepo.findAll());
        model.addAttribute("addrs", adresseRepo.findAll());
        model.addAttribute("erreur", name);
        model.addAttribute("ContactForm", new ContactForm());
        model.addAttribute("adresse", new Adresse());
        return "contact";
    }
    @GetMapping("/login")
    public String login(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue="World") String fooCookie, HttpServletResponse response) {
       return "login";
    }

    @ModelAttribute("liste")
    public List<ContactForm> getSomeList(){
        List<ContactForm> contactform = new ArrayList<>();
        for (Contact contact:contactRepo.findAll()) {
            ContactForm cont = new ContactForm(contact.getId(), contact.getFirstName(), contact.getLastName(),contact.getAdresses(), contact.getMailPersonnel().getMail());

            if(contact.getMailProfessionnel() != null)
            {
                cont.setEmailProfessionnel(contact.getMailProfessionnel().getMail());
                contactform.add(cont);
            }
            else {
                contactform.add(cont);
            }

        }

        return contactform;
    }

    @ModelAttribute("listeAdresse")
    public List<Adresse> getSomeListAdresse(){
        return adresseRepo.findAll();
    }

    @PostMapping("/Modifcontact")
    public String modifContact(@ModelAttribute Contact contact, Model model) {
        Long id = contact.getId();

       List<Contact> listcontact = contactRepo.findAll();

        for (Contact cont : listcontact){
            if(contact.getId() == cont.getId()){
               contact = cont;
            }

        }
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
        Email emailPerso = new Email(contactform.getIdemailPerso(),contactform.getEmailPersonnel());
        Contact contact = new Contact(contactform.getId(),contactform.getFirstName(), contactform.getLastName(), contactform.getAdresses());

        if(contactform.getEmailProfessionnel() != ""){
            Email email = new Email(contactform.getEmailProfessionnel());
            if(contactform.getIdemailPro() != -1L) {
                email.setId(contactform.getIdemailPro());
            }
            contact.setMailProfessionnel(email);
            contact.setMailPersonnel(emailPerso);
            try{
                emailRepo.save(email);
                emailRepo.save(emailPerso);
                contactRepo.save(contact);
            }
            catch (Exception e){
                return "redirect:/contact?erreur=true";
            }
        }
        else{
            contact.setMailPersonnel(emailPerso);
            try{
                emailRepo.save(emailPerso);
                contactRepo.save(contact);
            }
            catch (Exception e){
                return "redirect:/contact?erreur=true";
            }
        }

        List<Email> tot = emailRepo.findAll();

        model.addAttribute("name", contactRepo.findAll());
        model.addAttribute("contact", new ContactForm());
        model.addAttribute("contact", contact);
        model.addAttribute("addrs", adresseRepo.findAll());
        return "redirect:/contact";
    }

    @GetMapping("/Deletecontact")
    public String deleteContact(@ModelAttribute Contact contact, Model model) {
        contactRepo.delete(contact);
        return "redirect:/contact";
    }

    @PostMapping("/Addcontact")
    public String addContact(@ModelAttribute ContactForm contactform, Model model) {
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


        try{
        contactRepo.save(contact);
    }
        catch (Exception e){
            return "redirect:/contact?erreur=true";
    }

        return "redirect:/contact";

    }

    @GetMapping("/adresses")
    public String adresses(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue = "hello") String fooCookie, HttpServletResponse response) {
        model.addAttribute("adresses", adresseRepo.findAll());
        model.addAttribute("adresse", new Adresse());
        return "adresses";
    }
    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue = "hello") String fooCookie, HttpServletResponse response) {

        return "index";
    }

    @GetMapping("/adresses/delete")
    public String deleteAdresse(@ModelAttribute Adresse adresse, Model model) {
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
        model.addAttribute("adresse", adresse);
        model.addAttribute("modifadresse", new Adresse());

        return "updateAdresse";
    }


}
