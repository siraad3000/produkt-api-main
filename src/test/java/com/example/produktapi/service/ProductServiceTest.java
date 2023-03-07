package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Captor
    ArgumentCaptor<Product> productCaptor;
    @InjectMocks
    private ProductService underTest;

    //Product testProduct;

    /*
    @BeforeEach
     void setUp (){
        testProduct = new Product("item", 30.00, "itemCategory", "testDescription", "");
        testProduct.setId(1);
    }
     */

    //Checks if method returns findAll()
    @Test
    void givenGetAllProducts_thenExactlyOneInteractionWithRepositoryMethodFindAll(){
        //When
        underTest.getAllProducts();

        //Then
        verify(repository,times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void whenGetAllCategories_thenExactlyOneInteractionWithRepositoryMethodGetByCategory(){
        //When
        underTest.getAllCategories();
        //Then
        verify(repository,times(1)).findAllCategories();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void givenAnExistingCategory_whenGetProductsByCategory_thenReceivesANonEmptyList() {
        //Ska inte göras
        //when
        //Then
    }

    @Test
    @DisplayName("add-normalflöde")
    void whenAddingAProduct_thenSaveMethodShouldBeCalled(){
        //Given
        Product product = new Product("Ny-jacka",300.00,"Kläder","Beskrivning","");

        //When
        underTest.addProduct(product);
         
        //Then
        //capture catches the new value that was saved by the save() method.
        verify(repository).save(productCaptor.capture());
        assertEquals(product,productCaptor.getValue());
    }

    @Test
    @DisplayName("add-felflöde")
    void whenAddingProductWithDuplicateTitle_thenThrowError(){
        //Given
        String title = "test-title";
        Product product = new Product(title,400.00,"","","");
        given(repository.findByTitle("test-title")).willReturn(Optional.of(product));

        //Then
         BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                //When
                () -> underTest.addProduct(product));
        verify(repository,times(1)).findByTitle(title);
        verify(repository,times(0)).save(any());
        assertEquals("En produkt med titeln: test-title finns redan",exception.getMessage());

    }

    @Test
    @DisplayName("update-normalflöde")
    void givenCorretId_whenUpdatingAProduct_thenThenSaveAndUpdateProduct() {
        //Given
        Integer id = 1;
        Product product = new Product("green-shoes",200.00,"shoes","about-shoes","image");

        //When
        Product newUpdatedProduct = new Product("necklace",190.00,"jewelery","shinning-diamond","image");
        product.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(newUpdatedProduct));
        when(repository.save(newUpdatedProduct)).thenReturn(newUpdatedProduct);

        Product res = underTest.updateProduct(newUpdatedProduct,id);

        //Then
        verify(repository).save(productCaptor.capture());
        assertEquals("necklace",res.getTitle());
    }

    @Test
    @DisplayName("update-felflöde")
    void givenIncorrectId_whenUpdatingAProduct_thenThrowErrorBadException() {

        //Given
        Integer id = 1;
        Product product = new Product("",2000.00,"","","");

        //Then
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                //When
                () -> underTest.updateProduct(product,id));
        assertEquals(1,id);
        assertEquals("Produkt med id 1 hittades inte",exception.getMessage());

    }

    @Test
    @DisplayName("delete-normalflöde")
    void givenCorrectId_whenDeletingAProduct_thenDeleteProduct() {
        //Given
        Integer id = 1;
        Product product = new Product("scarf",100.00,"Clothes","scarf-description","image");
        product.setId(id);
        //When
        when(repository.findById(id)).thenReturn(Optional.of(product));
        underTest.deleteProduct(id);
        //Then
        verify(repository,times(1)).deleteById(id);
        assertNotNull(underTest.getProductById(id));
    }


    @Test
    @DisplayName("delete-felflöde")
    void givenIncorrectId_whenDeletingProduct_thenThrowErrorEntityNotFoundException() {
        //Given
        Integer id = 1;
        EntityNotFoundException  exception = assertThrows(EntityNotFoundException.class,
                //When
                () -> underTest.deleteProduct(id));
        assertEquals(id,id);
        assertEquals("Produkt med id 1 hittades inte", exception.getMessage());

    }
}

