package upc.fib.pes.grup121.model

import javax.persistence.*

@Entity
@Table(name = "friendship")
class Friendship(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var id: Long? = null,
    @Column
    var friendId: String? = null,
    @Column
    var ownerId:String? = null

) {
}