package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.Calendar;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to add a new student to a programme are met.
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with initialise method.
 */
public class AddStudent {
	
	private Programme programme;
    private Student student;

    @BeforeEach
    public void initialise() {
        // Initialise instances of all objects
        programme = new Programme();
        student = new Student("Jacky", 3896673);

        // set start date of programme
        Calendar startdate = Calendar.getInstance();
        startdate.set(2023, Calendar.JULY, 18);
        programme.setStartDate(startdate.getTime());
    
    }

    @Test
    public void testEnrollStudent_WhenProgrammeStartDateHasPassed() {

        // Test to see if the start date has already passed
        Calendar startdate = Calendar.getInstance();
        startdate.set(2023, Calendar.JANUARY, 17);
        programme.setStartDate(startdate.getTime());

        assertEquals(false, programme.addStudent(student));
    }

    @Test
    public void testEnrollStudent_WhenProgrammeIsFull() {

        // Test to see if the programme is full
        for(int i = 0; i < 250; i++) {
            programme.addStudent(new Student("Student" + (i+2), i+2));
        }
        assertFalse(programme.addStudent(student));
    }

    @Test
    public void testEnrollStudent_WhenStudentIsAlreadyEnrolled() {

        // Test to see if student is already enrolled in the programme
        programme.addStudent(student);
        assertThrows(IllegalStudentEnrollException.class, () -> programme.addStudent(student));
    }

    @Test
    public void testEnrollStudent_WhenAllConditionsAreMet() {

        assertTrue(programme.addStudent(student));
        assertEquals(1, programme.getEnrollments().size());
    }

}