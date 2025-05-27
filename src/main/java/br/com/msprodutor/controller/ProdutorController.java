package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.MessageAction;
import br.com.ccs.messagedispatcher.messaging.annotation.Command;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.model.input.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.random.RandomGenerator;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@MessageListener
@Slf4j
@Validated
public class ProdutorController {

    private final MessagePublisher publisher;

    @PostMapping("doCommand")
    public MessageInput doCommandica(@RequestBody MessageInput input) {
        var response = publisher.doCommand(input, input.getClass());
        return response;
    }

    @PostMapping("sendNotification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotification(@RequestBody OrderCreatedPayload input) {
        publisher.sendNotification(new OrderCreatedPayload(RandomGenerator.getDefault().nextInt()));
    }

    @PostMapping("sendNotificationError")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendNotificationError(@RequestBody ExceptionPayload input) {
        publisher.sendNotification(input);
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody MessageInput input) {
        log.info("Método throwErro | Mensagem consumida: {}", input);
        throw new RuntimeException("Erro ao consumir mensagem");
    }
}
