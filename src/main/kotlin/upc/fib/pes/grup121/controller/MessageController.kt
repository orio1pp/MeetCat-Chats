package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import upc.fib.pes.grup121.model.Message
import upc.fib.pes.grup121.service.MessageService

@Controller
class MessageController(
    private final var messageService: MessageService
) {
    @GetMapping("message/getMessagesByid")
    fun getMessagesById(@RequestParam chatId: Long):ResponseEntity<List<Message>> {
        var messages: List<Message>? = messageService.getMessagesById(chatId)
        messages.let{
            return ResponseEntity.ok(it);
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @PostMapping("message/newMessage")
    fun insertNewMessage(@RequestBody message: Message){
        message.let{
            return messageService.insertNewMessage(message);
        }
    }
}