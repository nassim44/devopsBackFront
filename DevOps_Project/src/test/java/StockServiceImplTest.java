import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepositoryMock;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("hello");
    }

    @Test
    public void testAddStock() {
        // Given
        Stock newStock = new Stock();
        newStock.setIdStock(1L);

        // When
        when(stockRepositoryMock.save(newStock)).thenReturn(newStock);
        Stock savedStock = stockService.addStock(newStock);

        // Then
        assertEquals(newStock, savedStock);
    }

    @Test
    public void testRetrieveStock() {
        // Given
        Long id = 1L;
        Stock expectedStock = new Stock();
        expectedStock.setIdStock(id);

        // When
        when(stockRepositoryMock.findById(id)).thenReturn(Optional.of(expectedStock));
        Stock retrievedStock = stockService.retrieveStock(id);

        // Then
        assertEquals(expectedStock, retrievedStock);
    }

    @Test
    public void testRetrieveAllStock() {
        // Given
        List<Stock> expectedStocks = Arrays.asList(new Stock(), new Stock());

        // When
        when(stockRepositoryMock.findAll()).thenReturn(expectedStocks);
        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        // Then
        assertEquals(expectedStocks.size(), retrievedStocks.size());
        assertEquals(expectedStocks, retrievedStocks);
    }
}
