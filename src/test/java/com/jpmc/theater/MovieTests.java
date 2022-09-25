package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }
    
    @Test
    void specialMovieDiscountTest() {
        Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 1);
        double expected = movie.getTicketPrice() * 0.2;
        Showing showing = new Showing(movie, 3, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 21), LocalTime.of(9, 0)));
        double actual = movie.getDiscount(showing);
        assertEquals(expected, actual);
    }
    
    @Test
    void firstSequenceDiscountTest() {
    	Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 2);
        double expected = 3;
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 21), LocalTime.of(9, 0)));
        double actual = movie.getDiscount(showing);
        assertEquals(expected, actual);
    }
    
    @Test
    void secondSequenceDiscountTest() {
    	Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 2);
        double expected = 2;
        Showing showing = new Showing(movie, 2, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 21), LocalTime.of(9, 0)));
        double actual = movie.getDiscount(showing);
        assertEquals(expected, actual);
    }
    
    @Test
    void seventhDayDiscountTest() {
    	Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 2);
        double expected = 1;
        Showing showing = new Showing(movie, 3, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 7), LocalTime.of(9, 0)));
        double actual = movie.getDiscount(showing);
        assertEquals(expected, actual);
    }
    
    @Test
    void afternoonDiscountTest() {
    	Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 2);
        double expected = movie.getTicketPrice() * 0.25;
        Showing showing = new Showing(movie, 3, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 21), LocalTime.of(11, 0)));
        double actual = movie.getDiscount(showing);
        assertEquals(expected, actual);
    }
    
    @Test
    void calculateTicketPriceTest() {
    	Movie movie = new Movie("Turning Red", Duration.ofMinutes(85), 11, 2);
        double expected = movie.getTicketPrice() - 3;
        Showing showing = new Showing(movie, 1, LocalDateTime.of(LocalDate.of(2022, Month.JUNE, 21), LocalTime.of(9, 0)));
        double actual = movie.calculateTicketPrice(showing);
        assertEquals(expected, actual);
    }
}
