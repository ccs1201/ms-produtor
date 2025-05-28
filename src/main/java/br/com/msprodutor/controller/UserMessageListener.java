package br.com.msprodutor.controller;


import br.com.ccs.messagedispatcher.messaging.MessageAction;
import br.com.ccs.messagedispatcher.messaging.annotation.*;
import br.com.msprodutor.exceptions.MsProdutorException;
import br.com.msprodutor.model.input.*;
import lombok.extern.slf4j.Slf4j;

//@MessageListener
@Slf4j
public class UserMessageListener {

    @Command
    public CreateUserPayload createUser(CreateUserPayload payload) {
        logMessage(payload);
        throw new MsProdutorException("é pra falhar aqui | payload: " +payload.toString());
//        return payload;
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


