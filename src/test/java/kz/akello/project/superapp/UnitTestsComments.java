package kz.akello.project.superapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.akello.project.superapp.dto.CommentDTO;
import kz.akello.project.superapp.mapper.CommentMapper;
import kz.akello.project.superapp.model.Comments;
import kz.akello.project.superapp.model.News;
import kz.akello.project.superapp.repository.CommentRepository;
import kz.akello.project.superapp.repository.NewsRepository;
import kz.akello.project.superapp.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@SpringBootTest
class UnitTestsComments {
	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private CommentService commentService;

	@Autowired
	private NewsRepository newsRepository;


	@Test
	void checkDtoComments(){
		Comments comments = new Comments();
		comments.setId(1L);
		comments.setUserName("Anonymous");
		News news = new News();
		comments.setNews(news);
		comments.setCommentary("Commentary for Testing");
		comments.setPostDate(new Timestamp(System.currentTimeMillis()));

		CommentDTO commentDTO = commentMapper.toDto(comments);

		Assertions.assertNotNull(commentDTO);
		Assertions.assertEquals(comments.getId(), commentDTO.getId());
		Assertions.assertEquals(comments.getUserName(), commentDTO.getUserName());
		Assertions.assertEquals(news, comments.getNews());
		Assertions.assertEquals(comments.getCommentary(), commentDTO.getCommentary());
		Assertions.assertEquals(comments.getPostDate(), commentDTO.getPostDate());
	}

	@Test
	void insertCommentIntoDb(){
		Comments comments = new Comments();
		comments.setId(1L);
		comments.setUserName("Anonymous");
		comments.setCommentary("Commentary for Testing");

		News news = new News();
		news.setNewsAuthor("SPIDER MAN");
		news = newsRepository.save(news);

		comments.setNews(news);

		CommentDTO commentDTO = commentService.addComment(commentMapper.toDto(comments));

		Assertions.assertNotNull(commentDTO);
		Assertions.assertNotNull(comments.getId());
		Assertions.assertEquals(comments.getUserName(), commentDTO.getUserName());
		Assertions.assertEquals(news, comments.getNews());
		Assertions.assertEquals(comments.getCommentary(), commentDTO.getCommentary());
		Assertions.assertNotNull(commentDTO.getPostDate());
		Assertions.assertTrue(commentDTO.getPostDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMinutes(1)));
		Assertions.assertTrue(commentDTO.getPostDate().toLocalDateTime().isBefore(LocalDateTime.now().plusMinutes(1)));
	}

}
