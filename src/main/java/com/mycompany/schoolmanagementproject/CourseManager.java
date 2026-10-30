package com.mycompany.schoolmanagementproject;
import java.util.*;


public class CourseManager {
    private List<Course> courses;
    
    public CourseManager(){
        this.courses = new ArrayList<>();
    }
    
    public boolean courseExists(String cID) {
        for (Course c : courses) {
            if(c.getcID().equalsIgnoreCase(cID.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addCourse(Course c) {
        if (courseExists(c.getcID())) {
            return false; // Duplicate found
    }
        courses.add(c);
        return true;
}
    
    public boolean updateCourse(String cID, String newName) {
        for(Course c : courses){
            if(c.getcID().equalsIgnoreCase(cID.trim())) {
                c.setcName(newName.trim());
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteCourse(String cID) {
        return courses.removeIf(c -> c.getcID().equalsIgnoreCase(cID.trim()));
        
    }
    
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
        
    }
    
    public List<Course> searchCourses(String query) {
        List<Course> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase().trim();
        
        for(Course c : courses) {
            if(c.getcID().toLowerCase().contains(lowerQuery) || c.getcName().toLowerCase().contains(lowerQuery)) {
                results.add(c);
        }
        }
        return results;
    }
    
    public void sortCByName() {
        courses.sort(Comparator.comparing(Course::getcName, String.CASE_INSENSITIVE_ORDER ));
    }
}
