package upc.fib.pes.grup121.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.model.User
import java.util.StringJoiner

interface FriendshipRepository: JpaRepository<Friendship, Long> {
    @Query("select u.username FROM Friendship f, User u where f.user_id = :id and u.user_id  = f.friend_id", nativeQuery = true)
    fun getFriendshipsbyUsername(@Param("id") id: Long?): List<String>
}