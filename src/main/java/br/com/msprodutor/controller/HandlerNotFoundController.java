package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.ccs.messagedispatcher.messaging.publisher.RabbitMessagePublisher;
import br.com.msprodutor.model.input.HandlerNotFoundInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Validated
@MessageListener
public class HandlerNotFoundController {

    private final MessagePublisher publisher;

    //ok
    @PostMapping("handlerNotFound")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handlerNotFound(@RequestBody HandlerNotFoundInput input) {
        publisher.doCommand(input, input.getClass());
    }
}
