package br.com.msprodutor.commandrunner;

import br.com.messagedispatcher.publisher.MessagePublisher;
import br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor;
import br.com.msprodutor.controller.NotificationController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
//@Component
public class AppCMD {

    private final MessagePublisher messagePublisher;

    @Bean
    CommandLineRunner run() {
        log.info("Enviando mensagens....");
        return args -> {

            var executor = Executors.newFixedThreadPool(200, Thread.ofVirtual().factory());
            var qtdMsgs = 500_000;

            AtomicInteger counter = new AtomicInteger(0);
            List<CompletableFuture> futures = new ArrayList<>(qtdMsgs);
            for (int i = 0; i < qtdMsgs; i++) {
                futures.add(CompletableFuture.runAsync(() -> {
                    var n = counter.incrementAndGet();
                    messagePublisher.sendNotification(MsConsumidor.MS_CONSUMIDOR_RK,
                            new NotificationController.SucessoRecord(n));
                    log.info("Mensagem {} enviada.", n);
                }, executor));
            }
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            log.info("Mensagens enviadas.");
        };
    }
}
