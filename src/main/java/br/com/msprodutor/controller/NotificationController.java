package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.annotation.Notification;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.model.input.ExceptionPayload;
import br.com.msprodutor.model.input.OrderCreatedPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Validated
@MessageListener
public class NotificationController {

    private final MessagePublisher publisher;

    @PostMapping("sendNotificationSucesso")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotificationSucesso(@RequestBody OrderCreatedPayload input) {
        publisher.sendNotification(new OrderCreatedPayload(RandomGenerator.getDefault().nextInt()));
    }

    @Notification
    public void Sucesso(OrderCreatedPayload payload) {
        log.info("Método sendNotificationSucesso | Mensagem consumida: {}", payload);
    }

    @PostMapping("sendNotificationError")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotificationError(@RequestBody ExceptionPayload input) {
        publisher.sendNotification(input);
    }

    @Notification
    public void erro(ExceptionPayload payload) {
        throw new UnsupportedOperationException("Método sendNotificationError não implementado");
    }
}
