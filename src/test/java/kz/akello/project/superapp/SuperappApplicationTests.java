package kz.akello.project.superapp;

import kz.akello.project.superapp.dto.CategoryDTO;
import kz.akello.project.superapp.dto.NewsDTO;
import kz.akello.project.superapp.dto.ProductDTO;
import kz.akello.project.superapp.dto.UserDTO;
import kz.akello.project.superapp.mapper.NewsMapper;
import kz.akello.project.superapp.mapper.ProductMapper;
import kz.akello.project.superapp.mapper.UserMapper;
import kz.akello.project.superapp.model.Category;
import kz.akello.project.superapp.model.News;
import kz.akello.project.superapp.model.Product;
import kz.akello.project.superapp.model.User;
import kz.akello.project.superapp.repository.NewsRepository;
import kz.akello.project.superapp.repository.ProductRepository;
import kz.akello.project.superapp.service.NewsService;
import kz.akello.project.superapp.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class UnitTestsForTheProject {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void checkProductDto(){
		Product product = new Product();
		product.setId(77L);
		product.setName("Test Product");
		product.setDescription("Fake Product Description");
		product.setPrice(777);
		product.setHandled(false);
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

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsRepository newsRepository;

	@Test
	void databaseInsertTest(){

		News news = new News();

		news.setTitle("FAKE NEWS");
		news.setDescription("DESCRIPTION FOR FAKE NEWS");

		NewsDTO newsDTO = newsService.addNews(newsMapper.toDto(news));

		Assertions.assertNotNull(newsDTO);
		Assertions.assertNotNull(newsDTO.getId());
		Assertions.assertEquals(news.getTitle(), newsDTO.getTitle());
		Assertions.assertEquals(news.getDescription(), newsDTO.getDescription());
		Assertions.assertEquals("Anonymous", newsDTO.getNewsAuthor());
		Assertions.assertNotNull(newsDTO.getPostDate());
		Assertions.assertTrue(newsDTO.getPostDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMinutes(1)));
		Assertions.assertTrue(newsDTO.getPostDate().toLocalDateTime().isBefore(LocalDateTime.now().plusMinutes(1)));

		newsRepository.deleteAll();
	}




}
