package com.flatironstudios.plately.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shoppinglist")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/generate")
    public ResponseEntity<ShoppingListResponseDTO> generateList(@RequestBody ShoppingListRequestDTO request) {
        return ResponseEntity.ok(new ShoppingListResponseDTO(shoppingListService.generateShoppingList(request.recipeIds())));
    }
}
