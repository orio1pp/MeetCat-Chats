package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.FriendshipRepository

@Service
class FriendshipService(
    private final var friendshipRepository: FriendshipRepository,
) {
    fun getFriendshipsbyUsername(userNameOnwer: String): List<String>{
        return friendshipRepository.findAllByOwnerId(userNameOnwer);
    }
    fun insertFriendship(friendship: Friendship) {
        friendshipRepository.save(friendship);
    }
    fun getFriendshipbyId(id: Long): Friendship{
        return friendshipRepository.getReferenceById(id);
    }

}