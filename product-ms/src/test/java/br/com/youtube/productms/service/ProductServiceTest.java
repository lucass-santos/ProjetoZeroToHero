package br.com.youtube.productms.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.youtube.productms.dto.ProductDTO;
import br.com.youtube.productms.model.Product;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @BeforeAll
    public static void setUP(){
        FixtureFactoryLoader.loadTemplates("br.com.youtube.productms.fixture");
    }

    @Test
    public void shouldCreateProduct(){
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        assertNotNull(response.get());
        assertEquals(request.getName(), response.get().getName());
        assertEquals(request.getDescription(), response.get().getDescription());
        assertEquals(request.getPrice(), response.get().getPrice());
        assertTrue(response.get().isAvailable());

    }

    @Test
    public void  shouldGetAllProducts(){
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        List<ProductDTO> responses = service.getAll();
        System.out.println(">>>>>>>>>>>" + responses.size());
        assertNotNull(responses);
        assertEquals(responses.get(0).getName(), response.get().getName());
        assertEquals(responses.get(0).getDescription(), response.get().getDescription());
        assertEquals(responses.get(0).getPrice(), response.get().getPrice());

    }

    @Test
    public void shouldGetByIdProduct(){
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);

        Long id = response.get().getId();

        Optional<ProductDTO> responseById = service.getById(id);

        assertNotNull(responseById.get());
        assertEquals(request.getName(), responseById.get().getName());
        assertEquals(request.getDescription(), responseById.get().getDescription());
        assertEquals(request.getPrice(), responseById.get().getPrice());
        assertTrue(responseById.get().isAvailable());

    }

    @Test
    public void shouldUpdateProduct(){
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        System.out.println(response.toString());
        Long id = response.get().getId();
        System.out.println("---------------------------------------------------------------");

        String newDescription = "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr";
        double newPrice = 24.93;
        request.setDescription(newDescription);
        request.setPrice(newPrice);
        Optional<ProductDTO> updatedProductDTO = service.update(id, request);
        System.out.println(updatedProductDTO.toString());

        assertNotNull(updatedProductDTO.get());
        assertEquals(newDescription, updatedProductDTO.get().getDescription());
        assertEquals(newPrice, updatedProductDTO.get().getPrice());
    }


    @Test
    public void shouldInactivateProduct(){
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        System.out.println(response.toString());
        Long id = response.get().getId();

        boolean inactive = service.inactive(id);

        assertTrue(inactive);

        
    }

}
