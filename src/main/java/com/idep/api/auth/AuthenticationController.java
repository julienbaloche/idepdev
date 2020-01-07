package com.idep.api.auth;

import com.idep.api.user.User;
import com.idep.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins="http///localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userRepository.findByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        final User userExists = userRepository.findByUsername(registerRequest.getUsername());
        if (userExists != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("user with this username already exists.");
        }

        User newUser = new User();
        newUser.setMail(registerRequest.getMail());
        newUser.setSurname(registerRequest.getSurname());
        newUser.setForename(registerRequest.getForename());
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return ResponseEntity.ok(userRepository.save(newUser));
    }

    //gets the details on the currently logged in user.
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> me(Principal principalUser) {
        if (principalUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("no Principal Found, please put in a valid header with token (replaces xxxx) e.g 'Authorization:Bearer xxxx' ");
        }
        return ResponseEntity.ok(principalUser);
    }

        // test endpoint
        @RequestMapping(value = "/test1", method = RequestMethod.GET)
        public ResponseEntity<String> test() {
            return ResponseEntity.ok("The app is working");
        }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
