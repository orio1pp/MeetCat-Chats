package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.ChatRepository
import upc.fib.pes.grup121.repository.FriendshipRepository
import upc.fib.pes.grup121.repository.MessageRepository

@Service
class DeleteService(
    private final var chatRepository: ChatRepository,
    private final var messageRepository: MessageRepository,
    private final var friendshipRepository: FriendshipRepository
) {
    fun deleteChat(chatId: Long, userName: String){
        chatRepository.findById(chatId).let {
            val friendship: Friendship? = it.get().getFriendship()
            if(friendship?.friendId.equals(userName) or friendship?.ownerId.equals(userName)) {
                messageRepository.deleteMessages(chatId)
                chatRepository.deleteChat(chatId)

            }
        }
    }
    fun deleteMessages(chatId: Long) {
        messageRepository.deleteMessages(chatId);
    }
    fun deleteFriend(friendId: String, ownerId: String) {
        var friendship: Friendship? = null;
        try {
            friendship = friendshipRepository.findByOwnerIdAndFriendId(ownerId, friendId)
        }catch (e: java.lang.Exception){
            throw Exception("Couldn't find the friendship")
        }

        try{
            val chatId: Long = friendshipRepository.existsChat(friendship.id)
            messageRepository.deleteMessages(chatId)
            chatRepository.deleteChat(chatId)
        }catch (e:java.lang.Exception){
        }
        friendshipRepository.deleteFriendship(friendId, ownerId);
    }


}