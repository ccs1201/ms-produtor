package br.com.msprodutor.poc;

import br.com.ccs.messagedispatcher.router.MessageRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
