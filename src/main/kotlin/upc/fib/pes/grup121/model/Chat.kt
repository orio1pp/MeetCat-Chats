package upc.fib.pes.grup121.model

import javax.persistence.*
@Entity
@Table(name = "chat", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("friendship_id")))
)
data class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var chatId: Long? = null,

    @OneToOne(cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
    @JoinColumn(name = "friendship_id", referencedColumnName = "id")
    private val friendship: Friendship? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat", cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
    private val messageList: List<Message>? = null
) {
    fun getFriendship(): Friendship?{
        return this.friendship;
    }
}