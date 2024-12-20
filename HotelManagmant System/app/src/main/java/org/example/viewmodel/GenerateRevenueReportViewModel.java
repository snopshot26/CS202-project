package org.example.viewmodel;

import org.example.dao.BookingDAO;

import java.time.LocalDate;

public class GenerateRevenueReportViewModel {
    private final BookingDAO bookingDAO;

    public GenerateRevenueReportViewModel() {
        this.bookingDAO = new BookingDAO();
    }

    public double generateRevenueReport(LocalDate startDate, LocalDate endDate) {
        return bookingDAO.calculateRevenue(startDate, endDate);
    }
}
