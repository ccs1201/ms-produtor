package br.com.msprodutor.poc;

import br.com.ccs.messagedispatcher.router.MessageRouter;
import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class CustomMessageRouter implements MessageRouter {

    @Override
    public Object routeMessage(Message message) {
        return null;
    }
}
