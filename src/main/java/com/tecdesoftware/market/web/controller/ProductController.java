package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.service.ProductService;
import com.tecdesoftware.market.persistence.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
//Le dice a Spring que va a ser el controlador de una API REST
@CrossOrigin(origins="*") //solo en pruebas
@RestController
@RequestMapping("/products")
@Tag(name ="Product Controller", description = "Manage products in the store")
public class ProductController {
    //Inyectar el servicio
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/all")
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products"
    )
    @ApiResponse( responseCode = "200", description = "Successful retrieval of products")
    @ApiResponse ( responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get products by ID",
            description = "Return a product by its ID if it exists"
    )
    @ApiResponse( responseCode = "200", description = "Product found")
    @ApiResponse ( responseCode = "404", description = "Product not found")
    @ApiResponse ( responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID of the products to be retrieved",
            example = "7", required = true)

            @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get product by category",
            description = "Return all products in a specific category"
    )
    @ApiResponse( responseCode = "200", description = "Product found in the category")
    @ApiResponse ( responseCode = "404", description = "No product found in the category")
    @ApiResponse ( responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(
            //TODO: Add @Parameter
            @Parameter( description = "ID of the category to be retrieved",
                    example = "7", required = true
            )
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new product", description = "Register a new product and return the created product",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    examples = @ExampleObject (
                            name = "Example Product",
                            value = """
                                  {
                                     "name": "Butter beer",
                                     "categoryId" : 2,
                                     "price": 19.50,
                                     "stock": 230,
                                     "active": true
                                  }
                                    """
                    )
            )
        )
    )
    @ApiResponse( responseCode = "201", description = "Product created successfully")//creacion exitosa - metodo post
    @ApiResponse ( responseCode = "400", description = "Invalid product data")// mala peticion
    @ApiResponse ( responseCode = "401", description = "Unauthorized")//no autorizado
    @ApiResponse( responseCode = "403", description = "Forbidden") //prohibido
    @ApiResponse ( responseCode = "409", description = "Product conflict(duplicate ID)")//conflicto
    @ApiResponse ( responseCode = "500", description = "Internal server error") //error de servidor general
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update product by ID",
            description = "Update an existing product using its ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Product object with updated fields",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Updated Product Example",
                                    value = """
                                        {
                                            "name": "Updated Butter Beer",
                                            "categoryId": 2,
                                            "price": 22.00,
                                            "stock": 180,
                                            "active": true
                                        }
                                        """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> update(@PathVariable("id") int productId, @RequestBody Product product) {
        return productService.getProduct(productId)
                .map(existingProduct -> {
                    product.setProductId(productId); // CORRECTO: usas el nombre del campo de la clase Product
                    Product updated = productService.save(product); // Reutilizas el metodo save
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //TODO: Add annotations
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete product by ID",
            description = "Delete a product if it exists"
    )
    @ApiResponse( responseCode = "200", description = "Product deleted successfully")
    @ApiResponse ( responseCode = "404", description = "Invalid product ID")
    @ApiResponse ( responseCode = "401", description = "Unauthorized")
    @ApiResponse( responseCode = "403", description = "Forbidden")
    @ApiResponse ( responseCode = "404", description = "Product not found")
    @ApiResponse ( responseCode = "500", description = "Internal server error")
    public ResponseEntity delete(
            @Parameter(
                    description = "ID of the product to be deleted", example = "7", required = true
            )
            @PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

