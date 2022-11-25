package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.service.ChatService

@Controller
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
    fun getAllChats(@RequestParam userId: Long): ResponseEntity<List<Chat>>{
        var chats: List<Chat>? = chatService.getAllChats(userId)
        chats.let{
            return ResponseEntity.ok(it)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    @PostMapping("chat")
    fun insertChat(@RequestBody chat: Chat){
        chat.let{
            chatService.insertChat(it);
        }
    }

}