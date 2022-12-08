package com.esafzay.websocket.client.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;

@Component
@Slf4j
public class ReceiverStompSessionHandler extends StompSessionHandlerAdapter {

    @Autowired
    private StompMessageHandler stompMessageHandler;

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return super.getPayloadType(headers);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        super.handleFrame(headers, payload);
    }

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        log.info("Connected!");
        super.afterConnected(stompSession, stompHeaders);

//        stompSession.subscribe("/queue/messages-123", stompMessageHandler);
        stompSession.subscribe("/user/123/queue/messages", stompMessageHandler);
        log.info("Subscribed to : /queue/messages");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        throw new RuntimeException("Failure in WebSocket handling", exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        super.handleTransportError(session, exception);
    }
}
