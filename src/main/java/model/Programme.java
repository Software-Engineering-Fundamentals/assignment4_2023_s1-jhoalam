package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Programmes offered by a university
 */
public class Programme {
    /**
     * Name and id of the programme
     */
    private String name;
    private int pID;

    /**
     * Start date of the programme
     */
    private Date startDate;

    /**
     * End date of the programme
     */
    private Date dueDate;

    /**
     * Estimated duration of the course in months
     */
    private int estimatedDuration;

    /**
     * Students allocated to the programme
     */
    private List<Student> enrolled = new ArrayList<Student>();

    // Football
    private Football football;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return pID;
    }

    public void setID(int ID) {
        this.pID = ID;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public List<Student> getEnrollments() {
        return enrolled;
    }

    
    public boolean removeEnrolledStudent(Student student) {
    
    	return false;
    	   
    }



    /**
     * Add a new student to the programme
     * @param Student: to enroll  to student in a programme 
     * @return true if the student is successfully enrolled, false otherwise
     */

    public boolean addStudent(Student student){
    	// Cannot add a student if the start date has passed.
        if (new Date().after(this.startDate)) {
            return false;
        }

        // Checking if enrolled students is over the limit
        if (this.enrolled.size() >= 250) {
            return false;
        }

        // A student cannot enroll if they are already enrolled in the same programme
        for (Student s : this.enrolled) {
            if (s.getId() == student.getId()) {
                throw new IllegalStudentEnrollException("Student is already enrolled in this programme");
            }
        }

        // Ff all requirements are met, then the student is enrolled
        this.enrolled.add(student);

        // Also add the student to the list of students for Football
        this.football.addAvailStudent(student);
        
        return true;
    }




}
