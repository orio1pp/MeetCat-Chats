package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import upc.fib.pes.grup121.dto.ChatDTO
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.service.ChatService

@RestController
class ChatController(
    private final var chatService: ChatService
) {
    @GetMapping("chat")
    fun getChatByFriendship(@RequestParam friendshipId: Long): ResponseEntity<Chat> {
        var chat: Chat? = chatService.getChatByFriendship(friendshipId)
        chat?.let{
            return ResponseEntity.ok(chat)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @GetMapping("chats")
    fun getAllChats(@RequestParam userId: String): List<String>?{
        var chats: List<String>? = chatService.getAllChats(userId)
        chats.let{
            return it;
        }
        return null;

    }
    @PostMapping("chat")
    fun insertChat(@RequestBody chat: ChatDTO){
        chat.let{
            chatService.insertChat(it);
        }
    }

}