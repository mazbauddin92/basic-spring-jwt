package com.mysoft.basicspringjwt.controller;

import com.mysoft.basicspringjwt.config.AppJwtTokenUtil;
import com.mysoft.basicspringjwt.model.JwtRequest;
import com.mysoft.basicspringjwt.model.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppJwtTokenUtil appJwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> getAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getName(), authenticationRequest.getId(),authenticationRequest.getValidated());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getName());

        final String token = appJwtTokenUtil.generateToken(userDetails,authenticationRequest);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String name, Integer id,Boolean Validate) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, id));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
