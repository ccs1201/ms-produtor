package br.com.ccs.msprodutor;

import br.com.ccs.dispatcher.annotations.EnableCcsDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCcsDispatcher
public class MsProdutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProdutorApplication.class, args);
    }

}
