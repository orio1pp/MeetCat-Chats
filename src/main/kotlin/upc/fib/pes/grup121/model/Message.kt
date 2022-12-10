package upc.fib.pes.grup121.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "message")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var messageId: Long? = null,
    @Column
    var text: String? = null,
    @Column
    var date: LocalDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "chat_id")
    var chat: Chat? = null,
    @Column
    var username: String? = null )
{
}