package br.com.ccs.msprodutor.controller;

import br.com.ccs.dispatcher.messaging.MessagePublisher;
import br.com.ccs.dispatcher.messaging.annotation.MessageHandler;
import br.com.ccs.dispatcher.messaging.annotation.MessageListener;
import br.com.ccs.msprodutor.model.input.MessageInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@Slf4j
@Validated
@MessageListener
public class ProdutorController {

    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;

    @PostMapping("publica")
    public void publica(@RequestBody MessageInput input, HttpServletRequest request) throws JsonProcessingException {
        log.info("Método publica | Request recebida: {}", input);
        log.info("Headers: {}", getHeadersToString(request));

        publisher.sendEvent(input);

        log.info("Mensagem enviada com sucesso");
    }

    @PostMapping("consome")
    @MessageHandler(forClass = MessageInput.class)
    public void consomeMensagem(@RequestBody MessageInput input, HttpServletRequest request) {
        log.info("Método consome | Mensagem consumida: {}", input);
        log.info("Headers: {}", getHeadersToString(request));
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody MessageInput input, HttpServletRequest request) {
        log.info("Método throwErro | Mensagem consumida: {}", input);
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
