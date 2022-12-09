package upc.fib.pes.grup121.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import upc.fib.pes.grup121.dto.InsertMessageDTO
import upc.fib.pes.grup121.dto.MessagesDTO
import upc.fib.pes.grup121.model.Chat
import upc.fib.pes.grup121.model.Message
import upc.fib.pes.grup121.repository.MessageRepository


@Service
class MessageService(
    private final var messageRepository: MessageRepository
) {
    fun getMessagesById(messageDTO: MessagesDTO): List<Message>? {
        val sortByDate: PageRequest = PageRequest.of(messageDTO.page, messageDTO.size, Sort.by("date").ascending())
        try {
            var messages: List<Message>? =
                messageRepository.getMessages(messageDTO.username, messageDTO.chatId, sortByDate);
            return messages;
        } catch (e: Exception) {
            throw Exception("Couldnt get all messages, because chat does not exist")
        }
    }

    fun insertNewMessage(message: InsertMessageDTO) {
        try {
            val chat: Chat = Chat(message.chatId)
            messageRepository.save(Message(null, message.text, message.date, chat, message.username));
        } catch (e: Exception) {
            throw Exception("Couldnt insert message")
        }
    }

}
