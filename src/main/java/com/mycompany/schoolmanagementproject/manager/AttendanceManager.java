package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.AttendanceDAO;
import com.mycompany.schoolmanagementproject.model.AttendanceRecord;
import java.util.ArrayList;

public class AttendanceManager {

    private AttendanceDAO attendanceDAO;
    private ArrayList<AttendanceRecord> classList; // Explicit ArrayList storage

    public AttendanceManager() {
        this.attendanceDAO = new AttendanceDAO();
        this.classList = new ArrayList<>();
    }

    // Load students for a class and date
    public ArrayList<AttendanceRecord> getStudentsForCourse(String courseName, String date) {
        this.classList = attendanceDAO.getStudentsByCourse(courseName, date);
        return this.classList;
    }

    // Save attendance list to database
    public boolean saveAttendance(ArrayList<AttendanceRecord> records) {
        this.classList = records;
        return attendanceDAO.saveAttendanceList(records);
    }

    // Manual Bubble Sort on ArrayList<AttendanceRecord> by Student Name
    // (Crucial prerequisite for Binary Search!)
    public void sortAttendanceByName(ArrayList<AttendanceRecord> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getStudentName().compareToIgnoreCase(list.get(j + 1).getStudentName()) > 0) {
                    AttendanceRecord temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // Manual Binary Search algorithm (O(log n)) by Student Name
    public int binarySearchByName(ArrayList<AttendanceRecord> list, String targetName) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = list.get(mid).getStudentName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return mid; // Found match
            }
            if (comparison < 0) {
                low = mid + 1; // Target is in right half
            } else {
                high = mid - 1; // Target is in left half
            }
        }
        return -1; // Not found
    }
}