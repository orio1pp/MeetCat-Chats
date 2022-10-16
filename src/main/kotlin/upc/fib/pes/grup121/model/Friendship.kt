package upc.fib.pes.grup121.model

import javax.persistence.*

@Entity
@Table(name = "friendship")
class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user: User? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private val friend: User? = null
}