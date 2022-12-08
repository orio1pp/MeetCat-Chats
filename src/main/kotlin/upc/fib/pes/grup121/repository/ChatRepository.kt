package upc.fib.pes.grup121.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Chat

interface ChatRepository : JpaRepository<Chat, Long> {
    @Query("select * from chat c where c.friendship_id = :friendshipId", nativeQuery = true)
    fun getChatByFriendship(@Param("friendshipId") friendshipId: Long): Chat
    @Query("select * from chat c, Friendship f where f.id = c.friendship_id and f.user_id = :userId", nativeQuery = true)
    fun getAllChatsByUserId(@Param("userId") userId: Long): List<Chat>


}