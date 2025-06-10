package br.com.msprodutor.controller;

import br.com.messagedispatcher.publisher.MessagePublisher;
import br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final MessagePublisher publisher;

    @SuppressWarnings("ClassEscapesDefinedScope")
    @PostMapping
    public void sendEvent(@RequestBody EventPayload payload) {
        publisher.sendEvent(MsConsumidor.MS_CONSUMIDOR_EVENT_QUEUE, payload);
        log.info("Método sendEvent | Mensagem enviada: {}", payload);
    }

    private record EventPayload(Integer idade) {
    }
}
