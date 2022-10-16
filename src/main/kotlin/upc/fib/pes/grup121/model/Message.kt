package upc.fib.pes.grup121.model

import javax.persistence.*

@Entity
@Table(name = "message")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var id: Long? = null;
    @Column
    var text: String? = null;
    @Column
    var date: String? = null;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    var chat: Chat? = null;
}