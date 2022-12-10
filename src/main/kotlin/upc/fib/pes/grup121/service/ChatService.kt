package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.dto.ChatDTO
import upc.fib.pes.grup121.dto.GetChatDTO
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
    fun getAllChats(username: String): List<GetChatDTO>?{
        try{
            var chats: List<Chat> = chatRepository.getAllChatsByUserId(username)
            var result: MutableList<GetChatDTO> = getChatsDTO(chats)
            return result;
        }catch (e: Exception){
            throw Exception("Couldnt get all chats, because user doesnt have chats")
        }
    }
    fun insertChat(chat:ChatDTO){
        try{
            val friendship:Friendship = friendshipService.getFriendshipbyId(chat.friendship)
            chatRepository.save(Chat(null, friendship, null))
        }catch (e:Exception){
            throw java.lang.Exception("Could not find friendship")
        }
    }
    fun getChatById(chatId: Long): Chat{
        return chatRepository.findById(chatId).get();
    }

    fun getChatByUsername(username: String): MutableList<GetChatDTO> {
        try{
            val chats:List<Chat> = chatRepository.getByUsername(username);
            val chatsDTO:MutableList<GetChatDTO> = getChatsDTO(chats)
            return chatsDTO;
        }
        catch (e:Exception){
            throw java.lang.Exception("User doesn't exists")
        }
    }

    private fun getChatsDTO(chats:List<Chat>): MutableList<GetChatDTO> {
        val result: MutableList<GetChatDTO> = ArrayList<GetChatDTO>();
        chats.forEach({
            var getChatDTO: GetChatDTO = GetChatDTO(it.chatId, it.getFriendship()!!.friendId, it.getFriendship()!!.id);
            result.add(getChatDTO)
        })
        return result
    }
}
