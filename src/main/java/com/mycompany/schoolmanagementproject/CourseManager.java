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
    
    public boolean addCourse(Course course) {
        if (courseExists(course.getcID())) {
            return false; // Duplicate found
    }
        courses.add(course);
    return true;
}
    
    public boolean updateCourse(String oldId, String newId, String newName) {
    // 1. If the ID is being changed, check if the new ID already exists
    if (!oldId.equalsIgnoreCase(newId)) {
        for (Course c : courses) {
            if (c.getcID().equalsIgnoreCase(newId)) {
                return false; // Duplicate ID found, abort update
            }
        }
    }

    // 2. Find the course with oldId and update its values
    for (Course c : courses) {
        if (c.getcID().equalsIgnoreCase(oldId)) {
            c.setcID(newId);
            c.setcName(newName);
            return true; // Successfully updated
        }
    }

    return false; // Old course was not found
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
