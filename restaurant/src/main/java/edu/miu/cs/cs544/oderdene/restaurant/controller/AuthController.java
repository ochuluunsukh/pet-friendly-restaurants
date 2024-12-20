package edu.miu.cs.cs544.oderdene.restaurant.controller;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import edu.miu.cs.cs544.oderdene.restaurant.service.CustomerDetailService;
import edu.miu.cs.cs544.oderdene.restaurant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(CustomerDetailService customerDetailService) {
        this.customerDetailService = customerDetailService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return ResponseEntity.ok("Login successful");
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer registerRequest) {
        try {
            customerDetailService.loadUserByUsername(registerRequest.getUsername());
            return ResponseEntity.badRequest().body("Username already exists");
        } catch (Exception ignored) {
            Customer cust = new Customer(
                    registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    passwordEncoder.encode(registerRequest.getPassword()),
                    "USER"
            );

            customerService.saveCustomer(cust);

            return ResponseEntity.ok("Customer registered successfully");
        }
    }

}
