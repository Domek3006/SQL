package pl.poznan.put.Casino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("pl.poznan.put.Entities")
@EnableJpaRepositories("pl.poznan.put.Repositories")
@ComponentScan({"pl.poznan.put.Validation", "pl.poznan.put.Casino"})
public class CasinoApplication{

	public static void main(String[] args) {
		SpringApplication.run(CasinoApplication.class, args);
	}

}
