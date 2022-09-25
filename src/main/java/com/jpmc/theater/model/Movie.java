package com.jpmc.theater.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        //return ticketPrice - getDiscount(showing.getSequenceOfTheDay());
    	return ticketPrice - getDiscount(showing);
    }

//    private double getDiscount(int showSequence) {
    public double getDiscount(Showing showing) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        
        int showSequence = showing.getSequenceOfTheDay();
       
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {

            sequenceDiscount = 2; // $2 discount for 2nd show
        }
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }

        // biggest discount wins
//        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
        
        // 7th day discount
        double dayDiscount = 0;
        LocalDateTime startTime = showing.getStartTime();
        if (startTime.getDayOfMonth() == 7) 
        	dayDiscount = 1;
        
        // 11AM - 4PM discount
        double afternoonDiscount = 0;
        if (startTime.getHour() >= 11 && startTime.getHour() <= 16)
        	afternoonDiscount = ticketPrice * 0.25;
        
        // Biggest discount wins
        return Math.max(
        		Math.max(specialDiscount, sequenceDiscount), 
        		Math.max(dayDiscount, afternoonDiscount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}