package com.test.qpassessment.controller;

import com.test.qpassessment.model.GroceryItem;
import com.test.qpassessment.service.GroceryItemService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/v1/grocery")
public class GroceryItemController {

    private GroceryItemService groceryItemService;

    @Autowired
    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    /**
     * Create new grocery item
     *
     * @param groceryItem:
     * @return the created grocery Item and HttpStatus.CREATED if grocery item was successfully created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroceryItem> createGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem savedGroceryItem = groceryItemService.createGroceryItem(groceryItem);
        return new ResponseEntity<>(savedGroceryItem, HttpStatus.CREATED);
    }

    /**
     * get all grocery items
     *
     * @param size: size of records
     * @param page: from which page records needed
     * @return the list of grocery Items and HttpStatus.OK
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroceryItem>> getGroceryItems(@RequestParam(required = false, defaultValue = "10") int size,
                                                             @RequestParam(required = false, defaultValue = "0") int page) {
        List<GroceryItem> groceryItems = groceryItemService.getGroceryItems(size, page);
        return new ResponseEntity<>(groceryItems, HttpStatus.OK);
    }

    /**
     * update grocery item by id
     *
     * @param id: id of grocery item
     * @return the updated grocery Item and HttpStatus.OK
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id,
                                                         @RequestBody GroceryItem groceryItem) throws BadRequestException {
        GroceryItem updateGroceryItem = groceryItemService.updateGroceryItem(id, groceryItem);
        return new ResponseEntity<>(updateGroceryItem, HttpStatus.OK);
    }

    /**
     * manage inventory of grocery item by id
     *
     * @param id: id of grocery item
     * @param stock: remaining quantity
     * @return HttpStatus.OK
     */
    @PutMapping(value = "/inventory/{id}")
    public ResponseEntity<Void> updateInventoryOfGroceryItem(@PathVariable Long id,
                                                                    @RequestParam Long stock) throws BadRequestException {
        groceryItemService.updateInventoryOfGroceryItem(id, stock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * delete grocery item by id
     *
     * @param id: id of grocery item
     * @return HttpStatus.OK
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) throws BadRequestException {
        groceryItemService.deleteGroceryItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
