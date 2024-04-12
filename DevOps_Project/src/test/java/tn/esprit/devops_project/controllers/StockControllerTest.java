package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class StockControllerTest {
    @Mock
    private StockRepository stockRepositoryMock;
    @InjectMocks
    private StockServiceImpl stockService;
    @InjectMocks
    private StockController stockController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        stockService = new StockServiceImpl(stockRepositoryMock);
        stockController = new StockController(stockService);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }


    @Test
    void addStock() {
        Stock newStock = new Stock(1L, "operator 1", new HashSet<>());
        when(stockService.addStock(newStock)).thenReturn(newStock);
        Stock addedStock = stockController.addStock(newStock);
        assertEquals(newStock, addedStock);
    }

    @Test
    void testGetAllStocks() {
        List<Stock> expectedStock = Arrays.asList(
                new Stock(1L, "product A", new HashSet<>()),
                new Stock(2L, "product B", new HashSet<>())
        );

        when(stockRepositoryMock.findAll()).thenReturn(expectedStock); // <-- NullPointerException here
        List<Stock> actualStock = stockService.retrieveAllStock();

        assertEquals(expectedStock.size(), actualStock.size());
        assertEquals(expectedStock, actualStock);
    }

    @Test
    void retrieveStock() throws Exception {
        Stock stock = new Stock(1L, "product A", new HashSet<>());
        Long id = 1L;
        when(stockRepositoryMock.findById(id)).thenReturn(Optional.of(stock)); // Change here
        mockMvc.perform(get("/stock/{id}", id))
                .andExpect(status().isOk());
    }
}