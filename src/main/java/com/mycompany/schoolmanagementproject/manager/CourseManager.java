package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.CourseDAO;
import com.mycompany.schoolmanagementproject.model.Course;
import java.util.ArrayList;

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
            return false;
        }
        return courseDAO.insertCourse(course);
    }

    public boolean updateCourse(String oldId, String newId, String newName) {
        if (!oldId.equalsIgnoreCase(newId) && courseDAO.courseExists(newId)) {
            return false;
        }
        return courseDAO.updateCourse(oldId, newId, newName);
    }

    public boolean deleteCourse(String cID) {
        return courseDAO.deleteCourse(cID);
    }

   
    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(courseDAO.selectAllCourses());
    }


    public void sortCoursesByName(ArrayList<Course> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getcName().compareToIgnoreCase(list.get(j + 1).getcName()) > 0) {
                    Course temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public int getEnrolledCount(String courseName) {
        return courseDAO.getEnrolledCount(courseName);
    }
}