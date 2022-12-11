package upc.fib.pes.grup121.repository

import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Friendship
import javax.transaction.Transactional


interface FriendshipRepository: JpaRepository<Friendship, Long> {
    fun findAllByOwnerIdOrFriendId(ownerId:String, friendId:String, pageable: PageRequest): List<Friendship>
    fun findByOwnerIdAndFriendId(ownerId: String?, friendId: String?) : Friendship
    @Transactional
    @Modifying
    @Query("delete from friendship f where f.friend_id = :friendId and f.owner_id = :ownerId", nativeQuery = true)
    fun deleteFriendship(@Param("friendId") friendId: String?, @Param("ownerId") ownerId: String?)
    @Query("select c.chat_id  from chat c where c.friendship_id = :friendshipId", nativeQuery = true)
    fun existsChat(@Param("friendshipId") friendshipId:Long?): Long
}
