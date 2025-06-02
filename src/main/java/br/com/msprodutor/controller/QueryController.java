package br.com.msprodutor.controller;


import br.com.ccs.messagedispatcher.annotation.MessageListener;
import br.com.ccs.messagedispatcher.annotation.Query;
import br.com.ccs.messagedispatcher.publisher.MessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.random.RandomGenerator;

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
        var response = publisher.doQuery(new QuerySuccessOutput(), QuerySuccessResponse.class);
        return response;
    }

    @Query
    public QuerySuccessResponse querySucessoHandler(QuerySuccessOutput payload) {
        return new QuerySuccessResponse(RandomGenerator.getDefault().nextInt(100));
    }

    @GetMapping("queryError")
    @ResponseStatus(HttpStatus.OK)
    public QueryErrorResponse queryError() {
        var response = publisher.doQuery(new QuerySuccessErrorOutput(), QueryErrorResponse.class);
        return response;
    }

    @Query
    public void queryErrorHandler(QuerySuccessErrorOutput payload) throws Exception {
        throw new RuntimeException("Quando exception não for filha de MessageDispatcherRuntimeException," +
                " deve ocorrer o retry e a resposta ira demorar...");
    }

    private record QuerySuccessErrorOutput() {
    }

    private record QuerySuccessResponse(int resultado) {
    }

    private record QuerySuccessOutput() {
    }

    private record QueryErrorResponse() {
    }
}
