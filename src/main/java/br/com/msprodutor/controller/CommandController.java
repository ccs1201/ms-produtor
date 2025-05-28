package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.annotation.Command;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.exceptions.MsProdutorException;
import br.com.msprodutor.model.input.DoCommandError;
import br.com.msprodutor.model.input.DoCommandSucess;
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
@Slf4j
@Validated
@MessageListener
public class CommandController {

    private final MessagePublisher publisher;

    //ok
    @PostMapping("doCommandSucesso")
    public DoCommandSucess doCommandSucesso(@RequestBody DoCommandSucess input) {
        var response = publisher.doCommand(input, input.getClass());
        return response;
    }
    @Command
    public DoCommandSucess doCommandSucessoCommand(DoCommandSucess payload) {
//        logMessage(payload);
        return payload;
    }

    @PostMapping("doCommandError")
    public void doCommandError() {
        var input = new DoCommandError();
        publisher.doCommand(input, input.getClass());
    }

    @Command
    public DoCommandError doCommandErrorCommand(DoCommandError payload) {
//        logMessage(payload);
        throw new MsProdutorException("Erro no processamento do doCommandError para testes");
    }
}
