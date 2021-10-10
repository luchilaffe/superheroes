package com.mindata.superheroes.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mindata.superheroes.controller.JwtAuthenticationController;
import com.mindata.superheroes.dto.JwtRequestDto;
import com.mindata.superheroes.dto.JwtResponseDto;
import com.mindata.superheroes.model.Users;
import com.mindata.superheroes.utils.RestEndpoints;

/**
 * Rest Controller that manage users of the system
 * 
 * @author carlos.lafferriere
 *
 */
@RestController
@CrossOrigin
public class JwtAutenticationRestController {

    private JwtAuthenticationController jwtAuthenticationController;

    public JwtAutenticationRestController(JwtAuthenticationController jwtAuthenticationController) {
        this.jwtAuthenticationController = jwtAuthenticationController;
    }

    /**
     * Endpoint to add Users to data base system. The users will have access to others endpoints.
     * 
     * @param userDto to be add
     * @return the user add
     */
    @PostMapping(RestEndpoints.USER_ADD)
    public ResponseEntity<Users> signUp(@RequestBody JwtRequestDto userDto) {
        return ResponseEntity.ok(jwtAuthenticationController.signUp(userDto));
    }

    /**
     * Method that authenticate the given user. If the given data is correct, it will be return a
     * valid token.
     * 
     * @param authenticationRequest contains users data to validate.
     * @return a valid token if success.
     * @throws Exception when there is a problem
     */
    @PostMapping(RestEndpoints.USER_AUTH)
    public ResponseEntity<JwtResponseDto> createAuthenticationToken(
            @RequestBody JwtRequestDto authenticationRequest) throws Exception {

        final String token =
                jwtAuthenticationController.createAuthenticationToken(authenticationRequest);

        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
