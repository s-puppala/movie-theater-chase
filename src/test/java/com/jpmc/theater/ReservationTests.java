package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        /*var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);*/
        Theater theater = new Theater();
        Customer john = new Customer("John Doe", "id-524698");
        Showing showing = theater.getSchedule().get(0);
        int audienceCount = 10;
        double expectedFee = showing.getMovieFee() * audienceCount;
        Reservation res = new Reservation(john, showing, audienceCount);
        double actualFee = res.totalFee();
        assertEquals(expectedFee, actualFee);
    }
}
