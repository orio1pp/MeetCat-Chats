package upc.fib.pes.grup121.model

import javax.persistence.*
@Entity
@Table(name = "chat", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("friendship_id")))
)
class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var chatId: Long? = null,

    @OneToOne
    @JoinColumn(name = "friendship_id", referencedColumnName = "id")
    private val friendship: Friendship? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private val messageList: List<Message>? = null
) {
    fun getFriendshipId(): Friendship?{
        return this.friendship;
    }
}