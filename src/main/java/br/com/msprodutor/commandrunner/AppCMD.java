package br.com.msprodutor.commandrunner;

import br.com.messagedispatcher.publisher.MessagePublisher;
import br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor;
import br.com.msprodutor.controller.NotificationController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppCMD {

    private final MessagePublisher messagePublisher;

    @Bean
    CommandLineRunner run() {
        log.info("Criando mensagens....");
        return args -> {
            for (int i = 0; i < 1000; i++) {
                messagePublisher.sendNotification(MsConsumidor.MS_CONSUMIDOR_RK,
                        new NotificationController.SucessoRecord(i));
                log.info("Mensagem {} enviada.", i);
            }
        };
    }
}
