package com.machines.vending.application.apirest.controllers.product;

import com.machines.vending.application.apirest.controllers.BaseController;
import com.machines.vending.domain.commands.product.CreateProductCommand;
import com.machines.vending.domain.commands.product.DeleteProductCommand;
import com.machines.vending.domain.commands.product.ReadAllProductsCommand;
import com.machines.vending.domain.commands.product.ReadProductCommand;
import com.machines.vending.domain.commands.product.UpdateProductCommand;
import com.machines.vending.domain.models.IdInfo;
import com.machines.vending.domain.models.Product;
import com.machines.vending.domain.models.ProductItem;
import com.machines.vending.domain.models.security.UserSessionDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.machines.vending.domain.models.Role.BUYER;
import static com.machines.vending.domain.models.Role.SELLER;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/products",
        consumes = "application/json",
        produces = "application/json")
public class ProductController extends BaseController {
    private final CreateProductCommand createProductCommand;
    private final DeleteProductCommand deleteProductCommand;
    private final UpdateProductCommand updateProductCommand;
    private final ReadProductCommand readProductCommand;
    private final ReadAllProductsCommand readAllProductsCommand;

    @PostMapping()
    @ResponseStatus(CREATED)
    public IdInfo createProduct(@RequestHeader(TOKEN_KEY) String token,
                                @RequestBody Product product) throws Exception {
        final UserSessionDetails user = checkRights(token, SELLER);
        return createProductCommand.execute(Product.builder()
                .sellerId(user.getId())
                .productName(product.getProductName())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void updateProduct(@RequestHeader(TOKEN_KEY) String token,
                              @PathVariable int id,
                              @RequestBody Product product) throws Exception {
        final UserSessionDetails user = checkRights(token, SELLER);
        updateProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(user.getId())
                .productName(product.getProductName())
                .cost(product.getCost())
                .amountAvailable(product.getAmountAvailable())
                .build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteProduct(@RequestHeader(TOKEN_KEY) String token,
                              @PathVariable int id) throws Exception {
        final UserSessionDetails user = checkRights(token, SELLER);
        deleteProductCommand.execute(Product.builder()
                .id(id)
                .sellerId(user.getId())
                .build());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Product getProduct(@PathVariable int id) throws Exception {
        return readProductCommand.execute(Product.builder()
                .id(id)
                .build());
    }

    @GetMapping("")
    @ResponseStatus(OK)
    public List<ProductItem> getAllProducts(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final UserSessionDetails user = checkRights(token, BUYER, SELLER);
        return readAllProductsCommand.execute(user.getId(), user.getRole());
    }

    @GetMapping("/seller")
    @ResponseStatus(OK)
    public List<ProductItem> getMyProducts(@RequestHeader(TOKEN_KEY) String token) throws Exception {
        final UserSessionDetails user = checkRights(token, SELLER);
        return readAllProductsCommand.execute(user.getId(), user.getRole());
    }
}
