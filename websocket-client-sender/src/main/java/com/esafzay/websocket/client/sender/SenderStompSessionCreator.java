package com.esafzay.websocket.client.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Component
@Slf4j
public class SenderStompSessionCreator {

    @Autowired
    StompSessionHandler stompSessionHandler;

    @EventListener(value = ApplicationReadyEvent.class)
    public void connect() {

        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
        webSocketStompClient.setMessageConverter(new StringMessageConverter());
//        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        try {
            log.info("Creating session ...");
            webSocketStompClient.connectAsync("ws://localhost:8080/stomp", stompSessionHandler);

        } catch (Exception e) {
            log.error("Connection failed.");
        }
    }
}
