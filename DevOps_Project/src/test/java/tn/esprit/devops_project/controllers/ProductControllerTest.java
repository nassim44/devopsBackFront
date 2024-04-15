package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Test
    void addProduct() throws Exception {

        Long stockId = 1L;

        Product newProduct = new Product(1L, "Product A", 10.00F,10, ProductCategory.BOOKS, new Stock(1L, "product A", new HashSet<>()));
        when(productService.addProduct(newProduct,stockId)).thenReturn(newProduct);
        Product addedProduct = productController.addProduct(newProduct,stockId);
        assertEquals(newProduct, addedProduct);
    }

    @Test
    void retrieveProduct() throws Exception {
        Product product = new Product(1L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock());
        Long id = 1L;
        when(productService.retrieveProduct(1L)).thenReturn(product);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/{id}",id))
                .andExpect(status().isOk());
    }

    @Test
    void retreiveAllProduct() throws Exception {
        List<Product> productList = Arrays.asList(
                new Product(1L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock()),
                new Product(2L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock())
        );

        when(productService.retreiveAllProduct()).thenReturn(productList);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

    @Test
    void retreiveProductStock() throws Exception {
        Long stockId = 1L;

        List<Product> productList = Arrays.asList(
                new Product(1L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock()),
                new Product(2L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock())
        );

        when(productService.retreiveProductStock(stockId)).thenReturn(productList);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/stock/{id}", stockId))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveProductByCategory() throws Exception {
        ProductCategory category = ProductCategory.ELECTRONICS;

        List<Product> productList = Arrays.asList(
                new Product(1L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock()),
                new Product(2L, "Product A", 10.00F,10,ProductCategory.BOOKS, new Stock())
        );

        when(productService.retrieveProductByCategory(category)).thenReturn(productList);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/productCategoy/{category}", category))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(delete("/product/{id}", productId))
                .andExpect(status().isOk());
    }
}