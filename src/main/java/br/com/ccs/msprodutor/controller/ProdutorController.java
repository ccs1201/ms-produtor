package br.com.ccs.msprodutor.controller;

import br.com.ccs.dispatcher.model.MessageWrapper;
import br.com.ccs.msprodutor.model.input.MessageInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class ProdutorController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("publica")
    public void enviarMensagem(@RequestBody MessageInput input) {
        var message = MessageWrapper.builder()
                .path(input.path())
                .method(HttpMethod.POST)
                .body(input)
                .build();

        rabbitTemplate.convertAndSend("ms-produtor", message, msg -> {
            log.info("Dados da mensagem enviada: {}", msg.getMessageProperties());
            return msg;
        });

        log.info("Mensagem enviada com sucesso");
    }

    @PostMapping("consome")
    public void consomeMensagem(@RequestBody MessageInput input) {
        log.info("Método consomeMensagem | Mensagem consumida: {}", input);
    }

    @PostMapping("throwErro")
    public void throwErro(@RequestBody MessageInput input) {
        log.info("Método throwErro | Mensagem consumida: {}", input);
        throw new RuntimeException("Erro ao consumir mensagem");
    }
}
