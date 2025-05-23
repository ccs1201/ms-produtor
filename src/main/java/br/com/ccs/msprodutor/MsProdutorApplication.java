package br.com.ccs.msprodutor;


import br.com.ccs.dispatcher.messaging.annotation.EnableMessageDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMessageDispatcher
public class MsProdutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProdutorApplication.class, args);
    }

}
