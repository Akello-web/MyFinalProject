package kz.akello.project.superapp;

import kz.akello.project.superapp.dto.ProductDTO;
import kz.akello.project.superapp.mapper.ProductMapper;
import kz.akello.project.superapp.model.Category;
import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.repository.CategoryRepository;
import kz.akello.project.superapp.repository.ProductRepository;
import kz.akello.project.superapp.repository.UserRepository;
import kz.akello.project.superapp.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuperappApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Test
	void checkProductDto(){
		Product product = new Product();
		product.setId(77L);
		product.setName("Test Product");
		product.setDescription("Fake Product Description");
		product.setPrice(777);
		User user = new User();
		product.setUserProduct(user);
		Category category = new Category();
		product.setCategory(category);

		ProductDTO productDTO = productMapper.toDto(product);

		Assertions.assertEquals(product.getId(), productDTO.getId());
		Assertions.assertEquals(product.getName(), productDTO.getName());
		Assertions.assertEquals(product.getDescription(), productDTO.getDescription());
		Assertions.assertEquals(product.getPrice(), productDTO.getPrice());
		Assertions.assertFalse(product.isHandled());
		Assertions.assertEquals(category, product.getCategory());
		Assertions.assertEquals(user, product.getUserProduct());
	}

	@Test
	void insertProductsIntoDB(){
		Product product = new Product();
		product.setId(77L);
		product.setName("Test Product");
		product.setDescription("Fake Product Description");
		product.setPrice(777);
		product.setHandled(false);

		User user = new User();
		user.setFullName("Testbek");
		user = userRepository.save(user);
		product.setUserProduct(user);

		Category category = new Category();
		category.setName("Category Test");
		category = categoryRepository.save(category);

		product.setCategory(category);

		ProductDTO productDTO = productService.addProduct(productMapper.toDto(product));

		Assertions.assertNotNull(productDTO);
		Assertions.assertNotNull(productDTO.getId());
		Assertions.assertEquals(product.getName(), productDTO.getName());
		Assertions.assertEquals(product.getDescription(), productDTO.getDescription());
		Assertions.assertEquals(product.getPrice(), productDTO.getPrice());
		Assertions.assertFalse(productDTO.isHandled());
		Assertions.assertEquals(user.getFullName(), product.getUserProduct().getFullName());
		Assertions.assertEquals(category, product.getCategory());

		productRepository.deleteAll();
		userRepository.deleteAll();
		categoryRepository.deleteAll();
	}

}
