package br.com.msprodutor.controller;

import br.com.messagedispatcher.publisher.MessagePublisher;
import br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ClassEscapesDefinedScope")
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final MessagePublisher publisher;

    @PostMapping
    public void sendEvent(@RequestBody EventPayload payload) {
        publisher.sendEvent(MsConsumidor.MS_CONSUMIDOR_EVENT_QUEUE, payload);
        log.info("Método sendEvent | Mensagem enviada: {}", payload);
    }

    @PatchMapping
    public void sendEventErro() {
        publisher.sendEvent(MsConsumidor.MS_CONSUMIDOR_EVENT_QUEUE, new EventErrorPayload());
        log.info("Método sendEventErro | Mensagem enviada:");
    }

    private record EventPayload(Integer idade) {
    }

    private record EventErrorPayload() {
    }
}
