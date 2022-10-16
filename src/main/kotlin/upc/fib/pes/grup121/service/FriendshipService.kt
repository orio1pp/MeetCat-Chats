package upc.fib.pes.grup121.service

import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.model.User
import upc.fib.pes.grup121.repository.FriendshipRepository

@Service
class FriendshipService(
    private final var friendshipRepository: FriendshipRepository,
    private final var userService: UserService
) {
    fun getFriendshipsbyUsername(username: String):List<String> {
        var user: User = userService.getUserByUsername(username);
        user?.let {
            var users: List<String> = friendshipRepository.getFriendshipsbyUsername(user.userId)
            return users;
        }
    }

    fun insertFriendship(friendship: Friendship) {
        friendshipRepository.save(friendship);
    }

}