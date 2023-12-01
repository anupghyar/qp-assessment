package com.test.qpassessment.repository;

import com.test.qpassessment.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryItemRepo extends JpaRepository<GroceryItem, Long> {

    @Modifying
    @Query("update GROCERY_ITEM gi set gi.quantity = :quantity where gi.id = :id")
    void updateInventoryOfGroceryItem(@Param("id") Long id, @Param("quantity") Long quantity);
}
