package edu.miu.cs.cs544.oderdene.restaurant.repository;

import edu.miu.cs.cs544.oderdene.restaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
