package upc.fib.pes.grup121.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.service.FriendshipService
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@CrossOrigin
@Controller
class FriendshipController(
    private final var friendshipService: FriendshipService
) {
    //falta paginar
    @GetMapping("friendship")
    fun getFriendshipsbyUsername(@RequestParam username: String): ResponseEntity<List<String>>? {
        var friends: List<String>? =friendshipService.getFriendshipsbyUsername(username);
        friends.let {
            return ResponseEntity.ok(it)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("friendship")
    fun insertFriendship(@RequestBody friendship: Friendship): ResponseEntity<Friendship>{
        friendship.let {
            friendshipService.insertFriendship(friendship).let {
                return ResponseEntity.ok(it);
            }
            return ResponseEntity(null, HttpStatus.IM_USED);
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }
}