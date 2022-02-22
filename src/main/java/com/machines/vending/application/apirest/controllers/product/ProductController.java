package com.machines.vending.application.apirest.controllers.product;

import com.machines.vending.domain.commands.product.CreateProductCommand;
import com.machines.vending.domain.commands.product.DeleteProductCommand;
import com.machines.vending.domain.commands.product.UpdateProductCommand;
import com.machines.vending.domain.exceptions.product.CreateProductWithGivenIdException;
import com.machines.vending.domain.exceptions.product.NotValidProductCostException;
import com.machines.vending.domain.exceptions.product.NotValidProductNameException;
import com.machines.vending.domain.exceptions.product.ProductNotFoundException;
import com.machines.vending.domain.models.Product;
import com.machines.vending.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/products",
        consumes = "application/json",
        produces = "application/json")
public class ProductController {
    private final CreateProductCommand createProductCommand;
    private final DeleteProductCommand deleteProductCommand;
    private final UpdateProductCommand updateProductCommand;

    @PostMapping()
    @ResponseStatus(CREATED)
    public void createProduct(Authentication authentication,
                              @RequestBody Product product) throws CreateProductWithGivenIdException, NotValidProductCostException, NotValidProductNameException {
        final User user = (User) authentication.getPrincipal();
        createProductCommand.execute(Product.builder()
                .sellerId(user.getId())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void updateProduct(Authentication authentication,
                              @PathVariable int id,
                              @RequestBody Product product) throws NotValidProductCostException, NotValidProductNameException, ProductNotFoundException {
        final User user = (User) authentication.getPrincipal();
        updateProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(user.getId())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteProduct(Authentication authentication,
                              @PathVariable int id) {
        final User user = (User) authentication.getPrincipal();
        deleteProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(user.getId())
                .build());
    }
}
