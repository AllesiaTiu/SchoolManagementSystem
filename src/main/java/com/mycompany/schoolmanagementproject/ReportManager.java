package com.mycompany.schoolmanagementproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReportManager {

    private ReportDAO reportDAO;
    private ArrayList<StudentReport> reportList; // Explicit ArrayList storage

    public ReportManager() {
        this.reportDAO = new ReportDAO();
        this.reportList = new ArrayList<>();
    }

    // Load fresh reports from database
    public ArrayList<StudentReport> getAllReports() {
        this.reportList = reportDAO.selectAllReports();
        return this.reportList;
    }

    // Filter reports by Academic Status
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

    // Manual Bubble Sort on ArrayList<StudentReport> by Student Name
    // (Crucial prerequisite for Binary Search)
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

    // Manual Binary Search algorithm (O(log n)) by Student Name
    public int binarySearchByName(ArrayList<StudentReport> list, String targetName) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return mid; // Match found
            }
            if (cmp < 0) {
                low = mid + 1; // Target is in right half
            } else {
                high = mid - 1; // Target is in left half
            }
        }
        return -1; // Not found
    }

    // Export current report records to a downloadable CSV file
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