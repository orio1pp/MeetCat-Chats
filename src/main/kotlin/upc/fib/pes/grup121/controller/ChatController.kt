package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import upc.fib.pes.grup121.dto.ChatDTO
import upc.fib.pes.grup121.dto.GetChatDTO
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.service.ChatService
import upc.fib.pes.grup121.service.DeleteService
import javax.websocket.server.PathParam

@RestController
class ChatController(
    private final var chatService: ChatService,
    private final var deleteService: DeleteService
) {
    @GetMapping("chat/{username}")
    fun getChatByUsername(@PathVariable username:String): ResponseEntity<MutableList<GetChatDTO>> {
        var chats: MutableList<GetChatDTO>? = chatService.getChatByUsername(username)
        chats?.let{
            return ResponseEntity.ok(chats)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }
    @GetMapping("chat")
    fun getChatByFriendship(@RequestParam friendshipId: Long): ResponseEntity<Chat> {
        var chat: Chat? = chatService.getChatByFriendship(friendshipId)
        chat?.let{
            return ResponseEntity.ok(chat)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }
    @GetMapping("chats")
    fun getAllChats(@RequestParam username: String): ResponseEntity<List<GetChatDTO>>?{
        var chats: List<GetChatDTO>? = chatService.getAllChats(username)
        chats.let{
            return ResponseEntity.ok(it);
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST);

    }
    @PostMapping("chat")
    fun insertChat(@RequestBody chat: ChatDTO){
        chat.let{
            chatService.insertChat(chat);
        }
    }
    @DeleteMapping("chat")
    fun deleteChat(@RequestParam chatId: Long, userName: String){
        deleteService.deleteChat(chatId, userName)
    }

}
