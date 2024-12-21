package edu.miu.cs.cs544.oderdene.restaurant.service;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import edu.miu.cs.cs544.oderdene.restaurant.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.oderdene.restaurant.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer customer = customerRepository.getById(id);
        customer.setUsername(updatedCustomer.getUsername());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        Customer review = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        customerRepository.delete(review);
    }

    public Customer getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails)
        {
            return (Customer) authentication.getPrincipal();
        }
        return null;
    }
}
