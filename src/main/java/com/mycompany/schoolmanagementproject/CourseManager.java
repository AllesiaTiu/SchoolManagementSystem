package com.mycompany.schoolmanagementproject;

import java.util.*;

public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager() {
        this.courseDAO = new CourseDAO();
    }

    public boolean courseExists(String cID) {
        return courseDAO.courseExists(cID);
    }

    public boolean addCourse(Course course) {
        if (courseDAO.courseExists(course.getcID())) {
            return false; // Duplicate
        }
        return courseDAO.insertCourse(course);
    }

    public boolean updateCourse(String oldId, String newId, String newName) {
        if (!oldId.equalsIgnoreCase(newId) && courseDAO.courseExists(newId)) {
            return false; // New ID conflicts with an existing one
        }
        return courseDAO.updateCourse(oldId, newId, newName);
    }

    public boolean deleteCourse(String cID) {
        return courseDAO.deleteCourse(cID);
    }

    public List<Course> getAllCourses() {
        return courseDAO.selectAllCourses();
    }

    public List<Course> searchCourses(String query) {
        List<Course> all = courseDAO.selectAllCourses();
        List<Course> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase().trim();

        for (Course c : all) {
            if (c.getcID().toLowerCase().contains(lowerQuery) || 
                c.getcName().toLowerCase().contains(lowerQuery)) {
                results.add(c);
            }
        }
        return results;
    }

    public List<Course> sortCByName() {
        List<Course> all = courseDAO.selectAllCourses();
        all.sort(Comparator.comparing(Course::getcName, String.CASE_INSENSITIVE_ORDER));
        return all;
    }

    // Get live enrolled count from DB
    public int getEnrolledCount(String courseName) {
        return courseDAO.getEnrolledCount(courseName);
    }
}