package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.ReportDAO;
import com.mycompany.schoolmanagementproject.model.StudentReport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportManager {

    private ReportDAO reportDAO;
    private ArrayList<StudentReport> reportList; 

    public ReportManager() {
        this.reportDAO = new ReportDAO();
        this.reportList = new ArrayList<>();
    }

    
    public ArrayList<StudentReport> getAllReports() {
        this.reportList = reportDAO.selectAllReports();
        return this.reportList;
    }

  
    public ArrayList<StudentReport> filterByStatus(String statusFilter) {
        ArrayList<StudentReport> all = getAllReports();
        if (statusFilter == null || statusFilter.equalsIgnoreCase("All")) {
            return all;
        }

        ArrayList<StudentReport> filtered = new ArrayList<>();
        for (StudentReport r : all) {
            if (r.getStatus().equalsIgnoreCase(statusFilter.trim())) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    public void sortReportsByName(ArrayList<StudentReport> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getName().compareToIgnoreCase(list.get(j + 1).getName()) > 0) {
                    StudentReport temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    
    public int binarySearchByName(ArrayList<StudentReport> list, String targetName) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return mid; 
            }
            if (cmp < 0) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }
        return -1; 
    }

    
    public boolean exportToCSV(String filePath, ArrayList<StudentReport> reports) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Student ID,Name,Program,GWA,Status\n");
            for (StudentReport r : reports) {
                String gwaFormatted = (r.getGwa() > 0.0) ? String.format("%.2f", r.getGwa()) : "N/A";
                writer.write(r.getStudentId() + "," 
                           + r.getName() + "," 
                           + r.getProgram() + "," 
                           + gwaFormatted + "," 
                           + r.getStatus() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("ReportManager Error [exportToCSV]: " + e.getMessage());
            return false;
        }
    }
}