package br.com.msprodutor.controller;

import br.com.messagedispatcher.annotation.MessageListener;
import br.com.messagedispatcher.annotation.Notification;
import br.com.messagedispatcher.publisher.MessagePublisher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor.MS_CONSUMIDOR_QUEUE;

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
    @Notification
    public void sendNotificationSucesso(@RequestBody SucessoRecord input) {
        log.info("Método sendNotificationSucesso | Mensagem consumida: {}", input);
        publisher.sendNotification(MS_CONSUMIDOR_QUEUE, input);
    }

    @PostMapping("sendNotificationError")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotificationError(@RequestBody ExceptionRecord input) {
        publisher.sendNotification(MS_CONSUMIDOR_QUEUE, input);
    }

    public record SucessoRecord(@NotNull int id) {
    }

    public record ExceptionRecord(@NotBlank String mensagem) {
    }
}
