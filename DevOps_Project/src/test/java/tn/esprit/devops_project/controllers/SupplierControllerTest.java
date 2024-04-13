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
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class SupplierControllerTest {
    @Mock
    private SupplierRepository supplierRepositoryMock;
    @InjectMocks
    private SupplierServiceImpl supplierService = new SupplierServiceImpl(supplierRepositoryMock);
    @InjectMocks
    private SupplierController supplierController = new SupplierController(supplierService);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();


    @Test
     void testGetSuppliers() {
        List<Supplier> expectedSuppliers = Arrays.asList(
                new Supplier(1L, "Operator A","A", SupplierCategory.ORDINAIRE, new HashSet<>()),
                new Supplier(2L, "Operator B","B",SupplierCategory.CONVENTIONNE, new HashSet<>())
        );

        when(supplierRepositoryMock.findAll()).thenReturn(expectedSuppliers);
        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        assertEquals(expectedSuppliers.size(), actualSuppliers.size());
        assertEquals(expectedSuppliers, actualSuppliers);

    }

    @Test
    void addSupplier() {
        Supplier newSupplier = new Supplier(1L, "Operator A","A", SupplierCategory.ORDINAIRE, new HashSet<>());
        when(supplierService.addSupplier(newSupplier)).thenReturn(newSupplier);
        Supplier addedSupplier = supplierController.addSupplier(newSupplier);
        assertEquals(newSupplier, addedSupplier);
    }

    @Test
    void removeSupplier() throws Exception {
        Long idSupplier = 1L;

        mockMvc.perform(delete("/supplier/{supplierId}", idSupplier))
                .andExpect(status().isOk());
    }



    @Test
    void modifySupplier() {
        Supplier updatedSupplier = new Supplier(1L, "Operator A","A", SupplierCategory.ORDINAIRE, new HashSet<>());
        when(supplierService.updateSupplier(updatedSupplier)).thenReturn(updatedSupplier);
        Supplier updatedSupplierSupplier  = supplierService.updateSupplier(updatedSupplier);
        assertEquals(updatedSupplier, updatedSupplierSupplier);
    }

}