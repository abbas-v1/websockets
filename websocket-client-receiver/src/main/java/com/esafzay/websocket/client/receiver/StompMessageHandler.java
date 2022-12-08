package com.esafzay.websocket.client.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;

@Component
@Slf4j
public class StompMessageHandler implements StompFrameHandler {
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received from another client : " + payload.toString());
    }
}
