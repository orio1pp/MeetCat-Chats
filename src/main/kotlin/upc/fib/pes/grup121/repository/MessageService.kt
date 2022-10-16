package upc.fib.pes.grup121.repository

import upc.fib.pes.grup121.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MessageRepository: JpaRepository<Message,Long> {
    @Query("select * from message m where m.chat_id = :chatId", nativeQuery = true)
    fun getMessagesByChatId(@Param("chatId") chatId: Long): List<Message>?
}