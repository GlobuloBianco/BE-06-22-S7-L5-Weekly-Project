package it.S7L5WP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.S7L5WP.config.Beans;
import it.S7L5WP.models.Sonda;
import it.S7L5WP.services.SondaService;

@SpringBootApplication
public class S7L5WpApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(S7L5WpApplication.class, args);
	}

	@Autowired
	SondaService sondaServ;

	ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
	
	// switch avvio
	boolean crea = false;

	@Override
	public void run(String... args) throws Exception {
		if (crea) {
			creazione();
		}
		
		((AnnotationConfigApplicationContext)ctx).close();
	}

	public void creazione() {

		Sonda s1 = (Sonda)ctx.getBean("sonda", 66.1234, 6.5678);
		System.out.println(s1);
		s1.setLivelloFumo(7);
		sondaServ.save(s1);

		// sonda.setLivelloFumo(7);
	}

}
