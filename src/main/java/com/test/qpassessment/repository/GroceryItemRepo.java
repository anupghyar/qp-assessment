package com.test.qpassessment.repository;

import com.test.qpassessment.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepo extends JpaRepository<GroceryItem, Long> {
}
