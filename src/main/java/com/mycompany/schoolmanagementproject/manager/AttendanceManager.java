package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.AttendanceDAO;
import com.mycompany.schoolmanagementproject.model.AttendanceRecord;
import java.util.ArrayList;

public class AttendanceManager {

    private AttendanceDAO attendanceDAO;
    private ArrayList<AttendanceRecord> classList; 

    public AttendanceManager() {
        this.attendanceDAO = new AttendanceDAO();
        this.classList = new ArrayList<>();
    }

   
    public ArrayList<AttendanceRecord> getStudentsForCourse(String courseName, String date) {
        this.classList = attendanceDAO.getStudentsByCourse(courseName, date);
        return this.classList;
    }

   
    public boolean saveAttendance(ArrayList<AttendanceRecord> records) {
        this.classList = records;
        return attendanceDAO.saveAttendanceList(records);
    }

  
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

 
    public int binarySearchByName(ArrayList<AttendanceRecord> list, String targetName) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = list.get(mid).getStudentName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return mid; 
            }
            if (comparison < 0) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }
        return -1;
    }
}