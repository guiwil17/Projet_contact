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
    private static final Logger log = LoggerFactory.getLogger(ContactData.class);

    @GetMapping("/contact")
    public String contact(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session, @CookieValue(value = "foo", defaultValue="World") String fooCookie, HttpServletResponse response) {
        model.addAttribute("name", contactRepo.findAll());
        //Cookie cookie = new Cookie("ddd", contactRepo.findById(2).toString());
        //response.addCookie(cookie);
        model.addAttribute("addrs", adresseRepo.findAll());
        model.addAttribute("contact", new Contact());
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
        log.info(contact.toString());
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

        model.addAttribute("contact", contact);
        model.addAttribute("modifcontact", new Contact());
        model.addAttribute("addrs", adresseRepo.findAll());
        return "update";
    }

    @PostMapping("/contact")
    public String updateContact(@ModelAttribute Contact contact, Model model) {
        log.info(contact.toString());
        contactRepo.save(contact);

        model.addAttribute("name", contactRepo.findAll());
        model.addAttribute("contact", new Contact());
        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping("/Deletecontact")
    public String deleteContact(@ModelAttribute Contact contact, Model model) {
        contactRepo.delete(contact);
        return "redirect:/contact";
    }

    @PostMapping("/Addcontact")
    public String addContact(@ModelAttribute Contact contact, Model model) {
        contactRepo.save(contact);
        log.info(contact.toString());
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
