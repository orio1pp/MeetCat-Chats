package upc.fib.pes.grup121.controller

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.*
import upc.fib.pes.grup121.dto.GetFriendshipsDTO
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.service.FriendshipService
import upc.fib.pes.grup121.service.DeleteService
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@CrossOrigin
@RestController
class FriendshipController(
    private final var friendshipService: FriendshipService,
    private final var deleteService: DeleteService
) {
    //falta paginar
    @GetMapping("friendship")
    fun getFriendshipsbyUsername(@RequestParam username:String, @RequestParam page: Int, @RequestParam size: Int): ResponseEntity<List<Friendship>>{
        var friends: List<Friendship> =friendshipService.getFriendshipsbyUsername(username, page, size);
        return ResponseEntity.ok(friends)
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

    @RequestMapping(method = [RequestMethod.DELETE], value = ["friendship"])
    fun deleteFriendship(@RequestParam friendId: String, @RequestParam ownerId: String){
        deleteService.deleteFriend(friendId, ownerId);
    }
}
