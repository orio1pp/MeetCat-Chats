package upc.fib.pes.grup121.repository

import org.springframework.data.domain.PageRequest
import upc.fib.pes.grup121.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface MessageRepository: JpaRepository<Message,Long> {
    @Query("select * from message m where m.chat_id = :chatId", nativeQuery = true)
    fun getMessagesByChatId(@Param("chatId") chatId: Long): List<Message>?

    fun findAllByChatAndUsername(chatId: Long, username:String, pageable: PageRequest): List<Message>

    @Query("select * from message m where m.chat_id = :chatId", nativeQuery = true)
    fun getMessages(@Param("chatId") chatId:Long, pageable: PageRequest): List<Message>

    @Transactional
    @Modifying
    @Query("delete from message m where m.chat_id = :chatId", nativeQuery = true)
    fun deleteMessages(@Param("chatId") chatId: Long)
}