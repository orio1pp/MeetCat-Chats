package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.dto.ChatDTO
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.ChatRepository

@Service
class ChatService(
    private final var chatRepository: ChatRepository,
    private final var friendshipService: FriendshipService
) {
    fun getChatByFriendship(friendshipId: Long): Chat? {
        var friendship: Friendship? = friendshipService.getFriendshipbyId(friendshipId);
        friendship.let {
            return chatRepository.getChatByFriendship(friendshipId)
        }
        return null
    }

    fun getAllChats(userId: String): List<String>?{
        var chats: List<String> = chatRepository.getAllChatsByUserId(userId)
        chats.let{
            return chats
        }
        return null
    }

    fun insertChat(chat: ChatDTO){
        val newChat: Chat = Chat(null, friendshipService.getFriendshipbyId(chat.friendship), null);
        chatRepository.save(newChat)
    }
}