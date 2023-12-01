package com.test.qpassessment.service.impl;

import com.test.qpassessment.model.GroceryItem;
import com.test.qpassessment.repository.GroceryItemRepo;
import com.test.qpassessment.service.GroceryItemService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    @Autowired
    GroceryItemRepo groceryItemRepo;

    public GroceryItem createGroceryItem(GroceryItem groceryItem) {
        GroceryItem savedGroceryItem = groceryItemRepo.save(groceryItem);
        return savedGroceryItem;
    }

    @Override
    public List<GroceryItem> getGroceryItems(int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<GroceryItem> groceryItems = groceryItemRepo.findAll(pageRequest);
        return groceryItems.getContent();
    }

    @Override
    public GroceryItem updateGroceryItem(Long id, GroceryItem groceryItem) throws BadRequestException {
        Optional<GroceryItem> optItem = groceryItemRepo.findById(id);
        GroceryItem updatedItem = optItem.map(item -> {
            item.setName(groceryItem.getName());
            item.setDescription(groceryItem.getDescription());
            item.setPrice(groceryItem.getPrice());
            item.setActive(groceryItem.isActive());
            item.setQuantity(groceryItem.getQuantity());
            GroceryItem saved = groceryItemRepo.save(item);
            return saved;
        }).orElseThrow(() -> new BadRequestException("Entity Not present with given id"));

        return updatedItem;
    }

    @Override
    public void deleteGroceryItem(Long id) {
        groceryItemRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void updateInventoryOfGroceryItem(Long id, Long availableStock) throws BadRequestException {
        groceryItemRepo.updateInventoryOfGroceryItem(id, availableStock);
    }
}
