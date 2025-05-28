package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.annotation.*;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.model.input.*;
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
public class HandlerNotFoundController {

    private final MessagePublisher publisher;

    //ok
    @PostMapping("handlerNotFound")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handlerNotFound(@RequestBody DoCommandSucess input) {
        publisher.doCommand(input, input.getClass());
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody DoCommandSucess input) {
        log.info("Método throwErro | Mensagem consumida: {}", input);
        throw new RuntimeException("Erro ao consumir mensagem");
    }



    private void logMessage(Object payload) {
        log.info("Mensagem recebida em {} payload {}", this.getClass().getSimpleName(), payload);
    }


}
