package cz.project.c3.resource;

import cz.project.c3.domain.dto.UserDTO;
import cz.project.c3.domain.dto.UserRegisterDTO;
import cz.project.c3.domain.user.User;
import cz.project.c3.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final IUserService userService;

    @Autowired
    public UserResource(IUserService userService) {
        this.userService = userService;
    }

    // имя фамилия адресс -> страна город

    /**
     * @return
     */
    @RequestMapping(value = "/logged_user", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser() {
        Optional<User> user = userService.getCurrentUser();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserRegisterDTO dto) {
        User user = userService.register(dto);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Только сам себя
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@PathVariable long id, UserDTO dto) {
        return ResponseEntity.ok().build();
    }

    /**
     * Только админ
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }
}
