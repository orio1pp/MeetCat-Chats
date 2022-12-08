package upc.fib.pes.grup121.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Chat

interface ChatRepository : JpaRepository<Chat, Long> {
    @Query("select * from chat c where c.friendship_id = :friendshipId", nativeQuery = true)
    fun getChatByFriendship(@Param("friendshipId") friendshipId: Long): Chat
    @Query("select c.chat_id from chat c, friendship f where f.id = c.friendship_id and f.owner_id = :userId", nativeQuery = true)
    fun getAllChatsByUserId(@Param("userId") userId: String): List<String>
}
