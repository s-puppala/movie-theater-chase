package com.jpmc.theater.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.util.JsonSchedule;
import com.jpmc.theater.util.RowSchedule;
import com.jpmc.theater.util.LocalDateProvider;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater() {
        this.provider = LocalDateProvider.singleton();

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }
    
    public List<Showing> getSchedule() {
    	return schedule;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing = null;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printScheduleDefaultMode() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }
    
    public void printScheduleJsonMode() {
    	
    	List<RowSchedule> rows = new ArrayList<>();
    	
    	// Populate rows list with values from showings
    	for (Showing s : schedule) {
    		rows.add(new RowSchedule(
    				s.getSequenceOfTheDay(), 
    				s.getStartTime().toString(), 
    				s.getMovie().getTitle(), 
    				humanReadableFormat(s.getMovie().getRunningTime()), 
    				Double.toString(s.getMovieFee())));
    	}
    	
    	// Instantiate jsonObj
    	JsonSchedule jsonObj = new JsonSchedule(provider.currentDate().toString(), rows);
    	
    	// Print JsonSchedule object in JSON human readable format
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		System.out.println("\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj));
    	} catch (JsonProcessingException jpe) {
    		System.out.println("Exception occurred: Unable to print JSON schedule.");
    	}
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        return (value == 1) ? "" : "s";
    }
}
