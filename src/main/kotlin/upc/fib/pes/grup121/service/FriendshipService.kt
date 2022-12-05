package upc.fib.pes.grup121.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.FriendshipRepository
import java.awt.print.Pageable

@Service
class FriendshipService(
    private final var friendshipRepository: FriendshipRepository,
) {
    fun getFriendshipsbyUsername(userNameOnwer: String): List<String>{
        return friendshipRepository.findAllByOwnerId(userNameOnwer);
    }
    fun insertFriendship(friendship: Friendship): Friendship? {
        try {
            friendshipRepository.findByOwnerIdAndFriendId(friendship.ownerId, friendship.friendId).let {
                return friendship;
            }
        }catch (e:Exception) {
            return friendshipRepository.save(friendship);
        }
    }
    fun getFriendshipbyId(id: Long): Friendship{
        return friendshipRepository.getReferenceById(id);
    }


}