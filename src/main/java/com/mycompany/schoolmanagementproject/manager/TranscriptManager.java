package com.mycompany.schoolmanagementproject.manager;

import com.mycompany.schoolmanagementproject.databaseAccess.TranscriptDAO;
import com.mycompany.schoolmanagementproject.model.TranscriptRecord;
import com.mycompany.schoolmanagementproject.model.Student;
import java.util.ArrayList;

public class TranscriptManager {

    private TranscriptDAO transcriptDAO;
    private ArrayList<TranscriptRecord> currentTranscript; // Explicit ArrayList storage

    public TranscriptManager() {
        this.transcriptDAO = new TranscriptDAO();
        this.currentTranscript = new ArrayList<>();
    }

    // Fetches live records from MySQL
    public ArrayList<TranscriptRecord> getStudentTranscript(String studentId) {
        this.currentTranscript = transcriptDAO.getTranscriptByStudentId(studentId);
        return this.currentTranscript;
    }

    // Sums up total units
    public int calculateTotalUnits(ArrayList<TranscriptRecord> records) {
        int total = 0;
        for (TranscriptRecord r : records) {
            total += r.getUnits();
        }
        return total;
    }

    // Converts numerical GWA into official collegiate equivalent rating
    public String getEquivalent(double gwa) {
        if (gwa <= 0.0) return "N/A";
        if (gwa >= 97.0 || (gwa <= 1.00 && gwa > 0)) return "1.00 (Excellent)";
        if (gwa >= 93.0 || (gwa <= 1.25 && gwa > 1.00)) return "1.25 (Superior)";
        if (gwa >= 89.0 || (gwa <= 1.50 && gwa > 1.25)) return "1.50 (Very Good)";
        if (gwa >= 85.0 || (gwa <= 1.75 && gwa > 1.50)) return "1.75 (Good)";
        if (gwa >= 80.0 || (gwa <= 2.25 && gwa > 1.75)) return "2.00 - 2.25 (Satisfactory)";
        if (gwa >= 75.0 || (gwa <= 3.00 && gwa > 2.25)) return "3.00 (Passing)";
        return "5.00 (Failed)";
    }

    // Manual Bubble Sort on ArrayList<Student> by Student ID
    // (Prerequisite for Binary Search!)
    public void sortStudentsById(ArrayList<Student> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getID().compareToIgnoreCase(list.get(j + 1).getID()) > 0) {
                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // Manual Binary Search algorithm (O(log n)) by Student ID
    public int binarySearchStudentById(ArrayList<Student> list, String targetId) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getID().compareToIgnoreCase(targetId);

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
}