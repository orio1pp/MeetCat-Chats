package upc.fib.pes.grup121.model

import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    var id: Long? = null,

    @Column
    private val username: String? = null,

    @Column
    private val mail: String? = null
) {
    
}