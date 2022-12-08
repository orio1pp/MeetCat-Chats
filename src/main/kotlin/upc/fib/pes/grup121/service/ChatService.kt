package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.ChatRepository

@Service
class ChatService(
    private final var chatRepository: ChatRepository,
    private final var friendshipService: FriendshipService,
    private final var messageService: MessageService
) {
    fun getChatByFriendship(friendshipId: Long): Chat? {
        var friendship: Friendship? = friendshipService.getFriendshipbyId(friendshipId);
        friendship.let {
            return chatRepository.getChatByFriendship(friendshipId)
        }
        return null
    }
    fun getAllChats(userId: Long): List<Chat>?{
        var chats: List<Chat> = chatRepository.getAllChatsByUserId(userId)
        chats.let{
            return chats
        }
        return null
    }
    fun insertChat(chat:Chat){
        chatRepository.save(chat)
    }
    fun deleteChat(chatId: Long, userName: String){
        chatRepository.findById(chatId).let {
            val friendship: Friendship? = it.get().getFriendshipId()
            if(friendship?.friendId.equals(userName) or friendship?.ownerId.equals(userName)) {
                messageService.deleteMessages(chatId)
                chatRepository.delete(it.get())

            }
        }
    }
}