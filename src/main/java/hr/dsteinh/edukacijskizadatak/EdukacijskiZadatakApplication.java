package hr.dsteinh.edukacijskizadatak;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class EdukacijskiZadatakApplication {

	@Bean
	public RestTemplate bookApiRestTemplate(@Value("${book.api.url}") String url){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
		return restTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(EdukacijskiZadatakApplication.class, args);
	}

}
