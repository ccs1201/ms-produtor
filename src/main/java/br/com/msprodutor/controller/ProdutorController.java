package br.com.msprodutor.controller;

import br.com.ccs.messagedispatcher.messaging.annotation.Event;
import br.com.ccs.messagedispatcher.messaging.annotation.MessageListener;
import br.com.ccs.messagedispatcher.messaging.publisher.MessagePublisher;
import br.com.msprodutor.model.input.MessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
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
    public MessageInput publica(@RequestBody MessageInput input, HttpServletRequest request) throws JsonProcessingException {
//        log.info("Método publica | Request recebida: {}", input);
//        log.info("Headers: {}", getHeadersToString(request));
//
        var reponse = publisher.doGet(input, MessageInput.class);
//
        log.info("Response: {}", reponse);
        return reponse;
    }


    @Event
    public void consomeMensagem(MessageInput input) {
//        log.info("Método consome | Mensagem consumida: {}", input);
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody MessageInput input, HttpServletRequest request) {
//        log.info("Método throwErro | Mensagem consumida: {}", input);
        throw new RuntimeException("Erro ao consumir mensagem");
    }

    private String getHeadersToString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder("\n");
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName ->
                        headers.append(headerName).append(": ").append(request.getHeader(headerName)).append("\n"));
        return headers.toString();
    }
}
