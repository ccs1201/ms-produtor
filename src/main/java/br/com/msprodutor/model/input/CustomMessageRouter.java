package br.com.msprodutor.model.input;

import br.com.ccs.messagedispatcher.router.MessageRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.util.Arrays;

//@Component
//@Primary
@Slf4j
public class CustomMessageRouter implements MessageRouter {

    @Override
    public Object routeMessage(Object message) {
        var messageBody = ((Message) message).getBody();
        log.info("CustomMessageRouter: {}", Arrays.toString(messageBody));
        return null;
    }
}
