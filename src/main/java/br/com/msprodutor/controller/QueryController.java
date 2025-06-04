package br.com.msprodutor.controller;

import br.com.messagedispatcher.annotation.MessageListener;
import br.com.messagedispatcher.annotation.Query;
import br.com.messagedispatcher.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.random.RandomGenerator;

import static br.com.msprodutor.constants.MsProdutorConstants.MsConsumidor.MS_CONSUMIDOR_RK;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Validated
@MessageListener
public class QueryController {

    private final MessagePublisher publisher;


    @GetMapping("querySucesso")
    @ResponseStatus(HttpStatus.OK)
    public QuerySuccessResponse querySucesso() {
        return publisher.doQuery(MS_CONSUMIDOR_RK, new QuerySuccessOutput(), QuerySuccessResponse.class);
    }

    @Query
    public QuerySuccessResponse querySucessoHandler(QuerySuccessOutput payload) {
        return new QuerySuccessResponse(RandomGenerator.getDefault().nextInt(100));
    }

    @GetMapping("queryError")
    @ResponseStatus(HttpStatus.OK)
    public QueryErrorResponse queryError() {
        return publisher.doQuery(MS_CONSUMIDOR_RK, new QuerySuccessErrorOutput(), QueryErrorResponse.class);
    }

    @Query
    public void queryErrorHandler(QuerySuccessErrorOutput payload) throws Exception {
        throw new RuntimeException("Quando exception não for filha de MessageDispatcherRuntimeException," +
                " deve ocorrer o retry e a resposta ira demorar...");
    }

    public record QuerySuccessErrorOutput() {
    }

    public record QuerySuccessResponse(int resultado) {
    }

    public record QuerySuccessOutput() {
    }

    public record QueryErrorResponse() {
    }
}
