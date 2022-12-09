package upc.fib.pes.grup121.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Chat
import javax.transaction.Transactional

interface ChatRepository : JpaRepository<Chat, Long> {
    @Query("select * from chat c where c.friendship_id = :friendshipId", nativeQuery = true)
    fun getChatByFriendship(@Param("friendshipId") friendshipId: Long): Chat
    @Query("select * from chat c, friendship f where f.id = c.friendship_id and f.owner_id = :username", nativeQuery = true)
    fun getAllChatsByUserId(@Param("username") username: String): List<Chat>


    @Transactional
    @Modifying
    @Query("delete from chat c where c.chat_id = :chatId", nativeQuery = true)
    fun deleteChat(@Param("chatId") chatId: Long)
}
