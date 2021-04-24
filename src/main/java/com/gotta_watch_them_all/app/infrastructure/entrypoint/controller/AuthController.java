package com.gotta_watch_them_all.app.infrastructure.entrypoint.controller;

import com.gotta_watch_them_all.app.core.entity.Role;
import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.core.entity.User;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.RoleMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.mapper.UserMapper;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.RoleRepository;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.repository.UserRepository;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.request.LoginRequest;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.request.SignupRequest;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.response.JwtResponse;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.response.MessageResponse;
import com.gotta_watch_them_all.app.infrastructure.security.JwtUtils;
import com.gotta_watch_them_all.app.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        var jwtResponse = new JwtResponse()
                .setToken(jwt)
                .setId(userDetails.getId())
                .setUsername(userDetails.getUsername())
                .setEmail(userDetails.getEmail())
                .setRoles(roles);
        return ResponseEntity.ok(
                jwtResponse
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: User is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User().setName(signupRequest.getUsername())
                .setEmail(signupRequest.getEmail())
                .setPassword(encoder.encode(signupRequest.getPassword()));
        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRoleEntity = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(RoleMapper.entityToDomain(userRoleEntity));
        } else {

            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(RoleMapper.entityToDomain(adminRole));
                } else {
                    RoleEntity userRoleEntity = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(RoleMapper.entityToDomain(userRoleEntity));
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(UserMapper.domainToEntity(user));

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
