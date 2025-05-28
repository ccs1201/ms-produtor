package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.MessageAction;
import br.com.ccs.messagedispatcher.messaging.annotation.Command;
import br.com.ccs.messagedispatcher.messaging.annotation.Event;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageHandler;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.annotation.Notification;
import br.com.ccs.messagedispatcher.messaging.annotation.Query;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.exceptions.MsProdutorException;
import br.com.msprodutor.model.input.CreateUserPayload;
import br.com.msprodutor.model.input.ExceptionPayload;
import br.com.msprodutor.model.input.MessageInput;
import br.com.msprodutor.model.input.OrderCreatedPayload;
import br.com.msprodutor.model.input.UserUpdatedPayload;
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
public class ProdutorController {

    private final MessagePublisher publisher;

    @PostMapping("handlerNotFound")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handlerNotFound(@RequestBody MessageInput input) {
        publisher.doCommand(input, input.getClass());
    }

    @PostMapping("doCommand")
    public MessageInput doCommand(@RequestBody MessageInput input) {
        var response = publisher.doCommand(input, input.getClass());
        return response;
    }

    @Command
    public MessageInput createUser(MessageInput payload) {
        logMessage(payload);
        return payload;
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


    @Query
    public void handleUserUpdated(UserUpdatedPayload payload) {
        // Processa mensagem de usuário atualizado
    }

    @Notification
    public void unsupportedOperationException(ExceptionPayload payload) {
        logMessage(payload);
        throw new UnsupportedOperationException("Método unsupportedOperationException não implementado");
    }

    @Notification
    public void handleUserDeletedNotification(OrderCreatedPayload payload) {
        logMessage(payload);
    }

    @Event
    public void handleUserEvent(OrderCreatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    //    @Event
    public void handleUserEventt(OrderCreatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    @Command
    public void handleUserEventt(UserUpdatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    @MessageHandler(action = MessageAction.COMMAND, forClass = OrderCreatedPayload.class)
    public void handleOrderCreated(OrderCreatedPayload payload) {
        // Processa mensagem de pedido criado
    }

    private void logMessage(Object payload) {
        log.info("Mensagem recebida em {} payload {}", this.getClass().getSimpleName(), payload);
    }
}
