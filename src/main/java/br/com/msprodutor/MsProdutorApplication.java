package br.com.msprodutor;


import br.com.ccs.messagedispatcher.annotation.EnableMessageDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMessageDispatcher
public class MsProdutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProdutorApplication.class, args);
    }

}
