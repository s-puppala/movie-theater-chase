package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.util.LocalDateProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;

public class TheaterTests {
	
	private Theater theater;
	private Customer john;
	
	@BeforeEach
	void init() {
		theater = new Theater();
		john = new Customer("John Doe", "id-12345");
	}
	
	@Test
	void reserveSuccessTest() {
		int sequence = 1;
		Reservation expected = new Reservation(john, theater.getSchedule().get(sequence-1), 0);
		Reservation actual = theater.reserve(john, sequence, 0);
		assertTrue(expected.getShowing().equals(actual.getShowing()));
	}
	
	void reserveFailureTest() {
		int sequence = 10;
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			theater.reserve(john, sequence, 0);
	    });
		String expectedMessage = "not able to find any showing for given sequence " + sequence;
		assertEquals(expectedMessage, exception.getMessage());
	}
	
    @Test
    void totalFeeForCustomerTest() {
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printDefaultMovieScheduleTest() {
    	// init output stream
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
 
    	// test
    	theater.printScheduleDefaultMode();
    	String expectedOutput = "2022-06-22\n" + 
    			"===================================================\n" + 
    			"1: 2022-06-22T09:00 Turning Red (1 hour 25 minutes) $11.0\n" + 
    			"2: 2022-06-22T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" + 
    			"3: 2022-06-22T12:50 The Batman (1 hour 35 minutes) $9.0\n" + 
    			"4: 2022-06-22T14:30 Turning Red (1 hour 25 minutes) $11.0\n" + 
    			"5: 2022-06-22T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" + 
    			"6: 2022-06-22T17:50 The Batman (1 hour 35 minutes) $9.0\n" + 
    			"7: 2022-06-22T19:30 Turning Red (1 hour 25 minutes) $11.0\n" + 
    			"8: 2022-06-22T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n" + 
    			"9: 2022-06-22T23:00 The Batman (1 hour 35 minutes) $9.0\n" + 
    			"===================================================\n";
    	assertEquals(expectedOutput, outContent.toString());
   
    	// reset output stream
    	System.setOut(originalOut);
    }
    
    @Test
    void printJsonMovieScheduleTest() {
    	// init output stream
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	PrintStream originalOut = System.out;
    	System.setOut(new PrintStream(outContent));
 
    	// test
    	theater.printScheduleJsonMode();
    	String expectedOutput = "\n{\n" + 
    			"  \"date\" : \"2022-06-22\",\n" + 
    			"  \"showingsToday\" : [ {\n" + 
    			"    \"sequenceNum\" : 1,\n" + 
    			"    \"startTime\" : \"2022-06-22T09:00\",\n" + 
    			"    \"title\" : \"Turning Red\",\n" + 
    			"    \"runtime\" : \"(1 hour 25 minutes)\",\n" + 
    			"    \"price\" : \"$11.0\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 2,\n" + 
    			"    \"startTime\" : \"2022-06-22T11:00\",\n" + 
    			"    \"title\" : \"Spider-Man: No Way Home\",\n" + 
    			"    \"runtime\" : \"(1 hour 30 minutes)\",\n" + 
    			"    \"price\" : \"$12.5\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 3,\n" + 
    			"    \"startTime\" : \"2022-06-22T12:50\",\n" + 
    			"    \"title\" : \"The Batman\",\n" + 
    			"    \"runtime\" : \"(1 hour 35 minutes)\",\n" + 
    			"    \"price\" : \"$9.0\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 4,\n" + 
    			"    \"startTime\" : \"2022-06-22T14:30\",\n" + 
    			"    \"title\" : \"Turning Red\",\n" + 
    			"    \"runtime\" : \"(1 hour 25 minutes)\",\n" + 
    			"    \"price\" : \"$11.0\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 5,\n" + 
    			"    \"startTime\" : \"2022-06-22T16:10\",\n" + 
    			"    \"title\" : \"Spider-Man: No Way Home\",\n" + 
    			"    \"runtime\" : \"(1 hour 30 minutes)\",\n" + 
    			"    \"price\" : \"$12.5\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 6,\n" + 
    			"    \"startTime\" : \"2022-06-22T17:50\",\n" + 
    			"    \"title\" : \"The Batman\",\n" + 
    			"    \"runtime\" : \"(1 hour 35 minutes)\",\n" + 
    			"    \"price\" : \"$9.0\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 7,\n" + 
    			"    \"startTime\" : \"2022-06-22T19:30\",\n" + 
    			"    \"title\" : \"Turning Red\",\n" + 
    			"    \"runtime\" : \"(1 hour 25 minutes)\",\n" + 
    			"    \"price\" : \"$11.0\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 8,\n" + 
    			"    \"startTime\" : \"2022-06-22T21:10\",\n" + 
    			"    \"title\" : \"Spider-Man: No Way Home\",\n" + 
    			"    \"runtime\" : \"(1 hour 30 minutes)\",\n" + 
    			"    \"price\" : \"$12.5\"\n" + 
    			"  }, {\n" + 
    			"    \"sequenceNum\" : 9,\n" + 
    			"    \"startTime\" : \"2022-06-22T23:00\",\n" + 
    			"    \"title\" : \"The Batman\",\n" + 
    			"    \"runtime\" : \"(1 hour 35 minutes)\",\n" + 
    			"    \"price\" : \"$9.0\"\n" + 
    			"  } ]\n" + 
    			"}\n";
    	assertEquals(expectedOutput, outContent.toString());
   
    	// reset output stream
    	System.setOut(originalOut);
    }
    
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater();
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(reservation.totalFee(), 50);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater();
        theater.printScheduleDefaultMode();
    }
}
