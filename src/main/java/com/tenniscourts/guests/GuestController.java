package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Api(value = "GuestController" , tags = {"Guest Controller"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("tennisCourts/guests")

public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @ApiOperation(value = "Show all the guests")
    @GetMapping()
    public ResponseEntity<List<GuestDTO>> findAllGuests() {
        return ResponseEntity.ok(guestService.findAllGuests());
    }

    @ApiOperation(value = "Add new guest")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guest have been added")})
    @PostMapping
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @ApiOperation(value = "Update an existing guest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Guest have been updated")})
    @PutMapping
    public ResponseEntity<Void> updateGuest(@RequestBody GuestDTO guestDTO) {
        guestService.updateGuest(guestDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Delete an existing guest")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Guest have been deleted")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Find a guest by id")
    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> findGuestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(guestService.findGuestById(id));
    }

    @ApiOperation(value = "Find a guest by name")
    @GetMapping(params = "name")
    public ResponseEntity findAllGuestsByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(guestService.findAllGuestsByName(name));
    }
}