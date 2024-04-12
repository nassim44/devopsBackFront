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
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class OperatorControllerTest {
    @Mock
    private OperatorRepository operatorRepositoryMock;
    @InjectMocks
    private OperatorServiceImpl operatorService = new OperatorServiceImpl(operatorRepositoryMock);
    @InjectMocks
    private OperatorController operatorController = new OperatorController(operatorService);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(operatorController).build();


    @Test
    public void testRetrieveAllOperators() {
        List<Operator> expectedOperators = Arrays.asList(
                new Operator(1L, "Operator A","A","nassim123", new HashSet<>()),
                new Operator(2L, "Operator B","B","nassim123", new HashSet<>())
        );

        when(operatorRepositoryMock.findAll()).thenReturn(expectedOperators);
        List<Operator> actualOperators = operatorService.retrieveAllOperators();

        assertEquals(expectedOperators.size(), actualOperators.size());
        assertEquals(expectedOperators, actualOperators);

    }

    @Test
    void addOperator() {
        Operator newOperator = new Operator(1L, "operator 1", "1", "nassim123", new HashSet<>());
        when(operatorService.addOperator(newOperator)).thenReturn(newOperator);
        Operator addedOperator = operatorController.addOperator(newOperator);
        assertEquals(newOperator, addedOperator);
    }



    @Test
    void removeOperator() throws Exception {
        Long operatorId = 1L;

        mockMvc.perform(delete("/operatot/{operatorId}", operatorId))
                .andExpect(status().isOk());
    }



    @Test
    void modifyOperateur() {
        Operator modifiedOperator = new Operator(1L,"operator 1" ,"1" ,"nassim123",new HashSet<>());
        when(operatorService.updateOperator(modifiedOperator)).thenReturn(modifiedOperator);
        Operator updatedOperator  = operatorController.modifyOperateur(modifiedOperator);
        assertEquals(modifiedOperator, updatedOperator);
    }
}