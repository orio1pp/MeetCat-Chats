package upc.fib.pes.grup121.model

import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.Date
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "esdeveniments")
data class Esdeveniment(
    @Id  @GeneratedValue(strategy = GenerationType.AUTO) var id: Long?,
    var titol: String,
    var descripcio: String,
    var data: LocalDateTime,
    var location: String,
    var lastUpdate: LocalDateTime? = null,
    var createdDate: LocalDateTime? = null
)