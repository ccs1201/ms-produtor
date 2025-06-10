package br.com.msprodutor.controller;

import br.com.messagedispatcher.annotation.Command;
import br.com.messagedispatcher.annotation.MessageListener;
import br.com.messagedispatcher.publisher.MessagePublisher;
import br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor;
import br.com.msprodutor.exceptions.MsProdutorException;
import br.com.msprodutor.model.input.DoCommandError;
import br.com.msprodutor.model.input.DoCommandSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor.*;

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
    public DoCommandSuccess doCommandSucesso(@RequestBody DoCommandSuccess input) {
        return publisher.doCommand(MS_CONSUMIDOR_QUEUE, input, input.getClass());
    }

    @Command
    public DoCommandSuccess doCommandSucessoHandler(DoCommandSuccess payload) {
        return payload;
    }

    @PostMapping("doCommandError")
    public void doCommandError() {
        var input = new DoCommandError();
        publisher.doCommand(MS_CONSUMIDOR_QUEUE, input, input.getClass());
    }

    @Command
    public DoCommandError doCommandErrorHandler(DoCommandError payload) {
        throw new MsProdutorException("doCommandError para testes se falhou Ta certo");
    }
}
