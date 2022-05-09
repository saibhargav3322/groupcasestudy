package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Ratings;
import com.example.demo.repository.RatingRepository;
import com.example.demo.service.RatingService;

@SpringBootTest
class RatingApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
	@MockBean
	private RatingRepository ratingRepository;
	
	@Test
	public void getAllRatings() {
		when(ratingRepository.findAll()).thenReturn(Stream
				.of(new Ratings("sai","good",10),
				new Ratings("chinnu","average",7),
				new Ratings("veera","worst",3)).collect(Collectors.toList()));
				assertEquals(3, ratingService.allRatings().size());
	}
	
	@Test
	public void addRating() {
		Ratings ratings = new Ratings("jagadeesh","good",9);
		when(ratingRepository.save(ratings)).thenReturn(ratings);
		assertEquals(ratings, ratingService.addRating(ratings));
		}
	
	@Test
	public void deleteRating() {
		Ratings ratings = new Ratings("chinnu","average",7);
		ratingRepository.delete(ratings);
		verify(ratingRepository, times(1)).delete(ratings);
	}

}
