package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Message
import upc.fib.pes.grup121.repository.MessageRepository

@Service
class MessageService(
    private final var messageRepository: MessageRepository
) {
    fun getMessagesById(chatId: Long): List<Message>?{
        chatId.let{
            var messages: List<Message>? = messageRepository.getMessagesByChatId(chatId);
            if(messages != null){
                return messages;
            }
        }
        return null;
    }

    fun insertNewMessage(message: Message){
        messageRepository.save(message);
    }
}