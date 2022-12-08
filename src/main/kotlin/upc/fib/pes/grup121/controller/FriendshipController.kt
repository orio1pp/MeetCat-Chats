package upc.fib.pes.grup121.controller

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import upc.fib.pes.grup121.dto.FriendshipsDTO
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.service.FriendshipService

@Controller
class FriendshipController(
    private final var friendshipService: FriendshipService
) {
    @GetMapping("friendship")
    fun getFriendshipsbyUsername(@RequestBody friendshipsDTO: FriendshipsDTO): ResponseEntity<List<String>>? {
        var friends: List<String>? =friendshipService.getFriendshipsbyUsername(friendshipsDTO);
        friends.let {
            return ResponseEntity.ok(it)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("friendship")
    fun insertFriendship(@RequestBody friendship: Friendship){
        friendshipService.insertFriendship(friendship);
    }

    @DeleteMapping("friendship")
    fun deleteFriendship(@RequestParam friendId: String, @RequestParam ownerId: String){
        friendshipService.deleteFriend(friendId, ownerId);
    }
}