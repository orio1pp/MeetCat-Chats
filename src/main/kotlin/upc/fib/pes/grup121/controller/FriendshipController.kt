package upc.fib.pes.grup121.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.model.User
import upc.fib.pes.grup121.service.FriendshipService
import java.util.StringJoiner
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Controller
class FriendshipController(
    private final var friendshipService: FriendshipService
) {
    @GetMapping("friendship/getFriendshipsbyUsername")
    fun getFriendshipsbyUsername(@RequestParam username: String): ResponseEntity<List<String>>? {
        var friends: List<String> =friendshipService.getFriendshipsbyUsername(username);
        if(!friends.equals(null))
            return ResponseEntity.ok(friends)
        else
            return null;
    }

    @PostMapping("friendship/insertFriendship")
    fun insertFriendship(@RequestBody friendship: Friendship){
        friendshipService.insertFriendship(friendship);
    }
}