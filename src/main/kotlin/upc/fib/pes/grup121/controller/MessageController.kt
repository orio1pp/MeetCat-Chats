package upc.fib.pes.grup121.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo


class MessageController {
    @MessageMapping("/news")
    @SendTo("/topic/news")
    fun broadcastNews(@Payload message: String?) {

    }
}