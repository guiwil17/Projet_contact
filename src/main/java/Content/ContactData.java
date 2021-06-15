package Content;

import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactData {

    private static final Logger log = LoggerFactory.getLogger(ContactData.class);

    public static void main(String[] args) {
        SpringApplication.run(ContactData.class);
    }

    @Bean
    public CommandLineRunner demo(OptionContact contact, OptionAdresse adresse) {
        return (args) -> {
            // save a few customers

            contact.save(new Contact("Jack", "Bauer"));
            contact.save(new Contact("Chloe", "O'Brian"));
            contact.save(new Contact("Kim", "Bauer"));
            contact.save(new Contact("David", "Palmer"));
            contact.save(new Contact("Michelle", "Dessler"));

            adresse.save(new Adresse("Rue des martyres", "51100", "Reims"));
            adresse.save(new Adresse("Rue Jean Jaurès", "51100", "Reims"));
            adresse.save(new Adresse("Avenue des champs élysée", "75000", "Paris"));
            adresse.save(new Adresse("Rue des bonbons", "45000", "Grenoble"));
            adresse.save(new Adresse("Rue des bijoux", "75000", "Paris"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Contact customer : contact.findAll()) {
                log.info(customer.toString());
            }
            log.info("");


            for (Adresse ad : adresse.findAll()) {
                log.info(ad.toString());
            }

            // fetch an individual customer by ID
            Contact customer = contact.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            /*
            contact.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });

             */
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}