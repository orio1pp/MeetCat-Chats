package upc.fib.pes.grup121.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import upc.fib.pes.grup121.dto.MessagesDTO
import upc.fib.pes.grup121.model.Message
import upc.fib.pes.grup121.repository.MessageRepository
import java.awt.print.Pageable

@Service
class MessageService(
    private final var messageRepository: MessageRepository
) {
    fun getMessagesById(messageDTO: MessagesDTO): List<Message>?{
        messageDTO.chatId.let{
            val sortByDate: PageRequest = PageRequest.of(messageDTO.page, messageDTO.size, Sort.by("date").ascending())
            var messages: List<Message>? = messageRepository.findAllByChatAnAndUsername(messageDTO.chatId, messageDTO.username, sortByDate);
            if(messages != null){
                return messages;
            }
        }
        return null;
    }
    fun insertNewMessage(message: Message){
        messageRepository.save(message);
    }

    fun deleteMessages(chatId: Long){
        messageRepository.deleteAllByChat(chatId);
    }
}