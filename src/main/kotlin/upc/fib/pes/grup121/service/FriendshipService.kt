package upc.fib.pes.grup121.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import upc.fib.pes.grup121.dto.FriendshipsDTO
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.FriendshipRepository
import java.awt.print.Pageable

@Service
class FriendshipService(
    private final var friendshipRepository: FriendshipRepository,
) {
    fun getFriendshipsbyUsername(friendshipsDTO: FriendshipsDTO): List<String> {
        val sortByDate: PageRequest = PageRequest.of(friendshipsDTO.page, friendshipsDTO.size, Sort.by("friendId").ascending())
        return friendshipRepository.findAllByOwnerId(friendshipsDTO.username,sortByDate);
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

    fun getFriendshipbyId(id: Long): Friendship {
        return friendshipRepository.getReferenceById(id);
    }

    fun deleteFriend(friendId: String, ownerId: String) {
        val friendship = friendshipRepository.findByOwnerIdAndFriendId(ownerId, friendId);
        friendship.let {
            friendshipRepository.delete(it);
        }
    }

}
