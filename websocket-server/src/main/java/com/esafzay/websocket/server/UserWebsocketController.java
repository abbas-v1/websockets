package com.esafzay.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class UserWebsocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(@Payload String message) {
        log.info("Received : " + message);
        this.simpMessagingTemplate.convertAndSendToUser("123", "/queue/messages", message);
//        this.simpMessagingTemplate.convertAndSend("/queue/messages-" + "123", message);

    }
}
