package com.test.qpassessment.service;

import com.test.qpassessment.model.GroceryItem;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface GroceryItemService {

    GroceryItem createGroceryItem(GroceryItem groceryItem);

    List<GroceryItem> getGroceryItems(int size, int page);

    GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem) throws BadRequestException;

    void deleteGroceryItem(Long id);

    void updateInventoryOfGroceryItem(Long id, Long availableStock) throws BadRequestException;
}
