package br.com.msprodutor.poc;

import br.com.ccs.messagedispatcher.messaging.MessageType;
import br.com.ccs.messagedispatcher.messaging.annotation.*;

@MessageListener
public class UserMessageListener {

    @Command
    public void handleUserCreated(UserCreatedPayload payload) {
        // Processa mensagem de usuário criado
    }

    @Query
    public void handleUserUpdated(UserUpdatedPayload payload) {
        // Processa mensagem de usuário atualizado
    }

    @Notification
    public void handleUserDeleted(UserDeletedPayload payload) {
        // Processa mensagem de usuário deletado
    }
    @Notification
    public void handleUserDeletedNotification(OrderCreatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    @Event
    public void handleUserEvent(OrderCreatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    @Command
    public void handleUserEventt(UserUpdatedPayload payload) {
        // Processa mensagem de usuário deletado
    }

    @MessageHandler(action = MessageType.NOTIFICATION, forClass = OrderCreatedPayload.class)
    public void handleOrderCreated(OrderCreatedPayload payload) {
        // Processa mensagem de pedido criado
    }
}


