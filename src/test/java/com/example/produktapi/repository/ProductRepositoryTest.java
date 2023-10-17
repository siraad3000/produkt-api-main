package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
class ProductRepositoryTest {

    //Create an instance of the repo
    @Autowired
    private ProductRepository underTest;

    @Test
    void givenCorrectTitle_whenSearchingForExistingTitle_thenReturnTrue(){
        //Given
            String title = "White Gold Plated Princess";
            Product product = new Product(title,
                    9.99,
                    "jewelery",
                    "Prinsessans bästa vän. Köp för att få den i din ägo",
                    "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jp");

            System.out.println(underTest.findByTitle(title));

            //When
            Optional<Product> optionalProduct = underTest.findByTitle(title);
            System.out.println(optionalProduct);
            //Then
            //Ett sätt att skriva tre tester
            Assertions.assertTrue(optionalProduct.isPresent());
            Assertions.assertFalse(optionalProduct.isEmpty());
            Assertions.assertEquals(title,optionalProduct.get().getTitle());

        }

    @Test
    void givenInCorrectTitle_whenSearchingForNonExistingTitle_thenReturnTrueEmptyOptional(){
        //Given
        String title = "En title som inte existerar";

        //When
        Optional<Product> optionalProduct = underTest.findByTitle(title);

        //Then
        Assertions.assertFalse(optionalProduct.isPresent());
        Assertions.assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void givenCorrectCategory_whenSearchingForAnExistingCategory_thenReturnFalseEmptyOptional() {
        //Given
        String category = "women's clothing";

        //When
        List<Product> listOfProducts= underTest.findByCategory(category);

        //Then
        Assertions.assertFalse(listOfProducts.isEmpty());
    }

    @Test
    void givenIncorrectCategory_whenSearchingForNonExistingCategory_thenReturnTrueEmptyOptional(){
        //Given
        //Kategori felskriven
        String category = "jewelerysss";

        //When
        List<Product> listOfCategory= underTest.findByCategory(category);

        //Then
        Assertions.assertTrue(listOfCategory.isEmpty());

    }

    @Test
    void getProductById(){
        //Given
        Integer id = 5;
        //when
         Optional<Product> listOfProductId = underTest.findById(id);
        //Then
        Assertions.assertFalse(listOfProductId.isEmpty());
        System.out.println("Här är deeeeeeen");
        System.out.println(listOfProductId);

    }

    @Test
    void whenSearchingForTheAmountOfAllTheExistingCategories_thenReturnFalseOptionalEmpty() {
        //When
        List<String> listOfCategories = underTest.findAllCategories();

        //Then
        Assertions.assertEquals(4, listOfCategories.size());
        Assertions.assertFalse(listOfCategories.isEmpty());
    }

    @Test
    void whenCheckingThatThereIsNoDuplicatesOfCategories_thenReturnEqualsListWithNoDuplicates(){

        //When
        List<String> listOfCategory = underTest.findAllCategories();

        //Then
        //Stream distinct() är en java operation som retunerar en stream av unika element.
        //Om det finns "duplicates" tas de bort.Sen placeras den på en ny lista
        Assertions.assertEquals(listOfCategory,listOfCategory.stream().distinct().collect(Collectors.toList()),"The list contains duplicates");

        System.out.println(listOfCategory);

    }


}