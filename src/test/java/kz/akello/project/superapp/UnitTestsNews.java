package kz.akello.project.superapp;

import kz.akello.project.superapp.dto.NewsDTO;
import kz.akello.project.superapp.mapper.NewsMapper;
import kz.akello.project.superapp.model.News;
import kz.akello.project.superapp.repository.NewsRepository;
import kz.akello.project.superapp.service.NewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class UnitTestsNews {
	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsRepository newsRepository;

	@Test
	void checkDto(){
		News news = new News();
		news.setId(1L);
		news.setNewsAuthor("Anonymous");
		news.setTitle("Test Title");
		news.setDescription("Description for DTO Testing");
		news.setPostDate(new Timestamp(System.currentTimeMillis()));

		NewsDTO newsDTO = newsMapper.toDto(news);

		Assertions.assertEquals(news.getId(), newsDTO.getId());
		Assertions.assertEquals(news.getTitle(), newsDTO.getTitle());
		Assertions.assertEquals(news.getDescription(), newsDTO.getDescription());
		Assertions.assertEquals(news.getNewsAuthor(), newsDTO.getNewsAuthor());
		Assertions.assertEquals(news.getPostDate(), newsDTO.getPostDate());
	}

	@Test
	void insertNewsToDb(){

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
