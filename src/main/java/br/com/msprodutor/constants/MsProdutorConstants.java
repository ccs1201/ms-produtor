package br.com.msprodutor.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MsProdutorConstants {

    public static final class MsConsumidor {
        public static final String MS_CONSUMIDOR_QUEUE = "ms-consumidor";
        public static final String MS_CONSUMIDOR_EVENT_QUEUE = "ms-consumidor.event.inbox";
    }
}
