package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import upc.fib.pes.grup121.dto.InsertMessageDTO
import upc.fib.pes.grup121.dto.MessagesDTO
import upc.fib.pes.grup121.model.Message
import upc.fib.pes.grup121.service.DeleteService
import upc.fib.pes.grup121.service.MessageService

@RestController
class MessageController(
    private final var messageService: MessageService
) {
    @GetMapping("message")
    fun getMessagesById(
        @RequestParam chatId: Long,
        @RequestParam username: String,
        @RequestParam size: Int,
        @RequestParam page: Int
    ):ResponseEntity<List<Message>> {
        var messages: List<Message>? = messageService.getMessagesById(MessagesDTO(chatId, username, page, size))
        messages.let{
            return ResponseEntity.ok(it);
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @PostMapping("message")
    fun insertNewMessage(@RequestBody message: InsertMessageDTO){
        message.let{
            return messageService.insertNewMessage(message);
        }
    }
}