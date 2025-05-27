package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.MessageAction;
import br.com.ccs.messagedispatcher.messaging.annotation.Command;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.model.input.MessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@MessageListener
@Slf4j
@Validated
public class ProdutorController {

    private final MessagePublisher publisher;

    @PostMapping("publica")
    public MessageInput publica(@RequestBody MessageInput input) throws JsonProcessingException {
//        log.info("Método publica | Request recebida: {}", input);
//        log.info("Headers: {}", getHeadersToString(request));
//
        var response = publisher.doCommand(input, input.getClass());
        return response;
    }

    @Command
    public MessageInput consomeMensagem(MessageInput input) {
        log.info("Método consome | Mensagem consumida: {}", input);

        return new MessageInput("Nome alterado no consumidor", input.idade(), input.sexo(), input.dataNascimento(), MessageAction.COMMAND.name());
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody MessageInput input) {
        log.info("Método throwErro | Mensagem consumida: {}", input);
        throw new RuntimeException("Erro ao consumir mensagem");
    }
}
