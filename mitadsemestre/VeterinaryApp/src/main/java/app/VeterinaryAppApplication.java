package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapters.inputs.LoginInput;

@SpringBootApplication
public class VeterinaryAppApplication implements CommandLineRunner {
	
	@Autowired
	private LoginInput loginInput;
	@Override
	public void run(String... args) throws Exception {
		/*System.out.println("Beans registrados en la aplicación:");
		String[] beanNames = beanFactory.getBeanDefinitionNames();
	for (String beanName : beanNames) {
		System.out.println(beanName);
	}*/
		loginInput.menu();
	}
	public static void main(String[] args) {
		SpringApplication.run(VeterinaryAppApplication.class, args);
	}

}