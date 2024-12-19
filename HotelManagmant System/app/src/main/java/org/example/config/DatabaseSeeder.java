package org.example.config;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseSeeder {

    public static void run() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (isDatabaseEmpty(session)) {
                System.out.println("Database is empty. Inserting test data...");
                insertTestData(session);
                System.out.println("Test data inserted successfully!");
            } else {
                System.out.println("Database already has data. No need to insert test data.");
            }
        }
    }
    

    private static boolean isDatabaseEmpty(Session session) {
        String hql = "SELECT COUNT(*) FROM Hotel";
        Long count = (Long) session.createQuery(hql).uniqueResult();
        return count == 0;
    }

    public static void insertTestData(Session session) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Insert data into Hotel table
            session.createNativeQuery("""
                INSERT INTO Hotel (hotelName, address) VALUES
                ('Grand Hotel', '123 Main St, Springfield'),
                ('Oceanview Resort', '456 Ocean Ave, Beachside'),
                ('Mountain Lodge', '789 Mountain Rd, Hilltown'),
                ('City Lights Hotel', '1010 Broadway, Metropolis'),
                ('Sunset Inn', '202 Sunset Blvd, Lakeside'),
                ('Royal Plaza', '505 Royal Rd, Downtown')
            """).executeUpdate();

            // Insert data into User table (admins, receptionists, guests)
            session.createNativeQuery("""
                INSERT INTO User (name, email, phoneNumber, password, userType) VALUES
                -- Admins
                ('Admin 1', 'admin1@example.com', '555-1001', '12345', 'administrator'),
                ('Admin 2', 'admin2@example.com', '555-1002', '12345', 'administrator'),
                ('Admin 3', 'admin3@example.com', '555-1003', '12345', 'administrator'),
                ('Admin 4', 'admin4@example.com', '555-1004', '12345', 'administrator'),
                ('Admin 5', 'admin5@example.com', '555-1005', '12345', 'administrator'),
                ('Admin 6', 'admin6@example.com', '555-1006', '12345', 'administrator'),
                -- Receptionists
                ('Receptionist 1', 'rec1@example.com', '555-2001', '12345', 'receptionist'),
                ('Receptionist 2', 'rec2@example.com', '555-2002', '12345', 'receptionist'),
                ('Receptionist 3', 'rec3@example.com', '555-2003', '12345', 'receptionist'),
                ('Receptionist 4', 'rec4@example.com', '555-2004', '12345', 'receptionist'),
                ('Receptionist 5', 'rec5@example.com', '555-2005', '12345', 'receptionist'),
                ('Receptionist 6', 'rec6@example.com', '555-2006', '12345', 'receptionist'),
                ('Receptionist 7', 'rec7@example.com', '555-2007', '12345', 'receptionist'),
                ('Receptionist 8', 'rec8@example.com', '555-2008', '12345', 'receptionist'),
                ('Receptionist 9', 'rec9@example.com', '555-2009', '12345', 'receptionist'),
                ('Receptionist 10', 'rec10@example.com', '555-2010', '12345', 'receptionist'),
                ('Receptionist 11', 'rec11@example.com', '555-2011', '12345', 'receptionist'),
                ('Receptionist 12', 'rec12@example.com', '555-2012', '12345', 'receptionist'),
                -- Guests
                ('Guest 1', 'guest1@example.com', '555-3001', '12345', 'guest'),
                ('Guest 2', 'guest2@example.com', '555-3002', '12345', 'guest'),
                ('Guest 3', 'guest3@example.com', '555-3003', '12345', 'guest'),
                ('Guest 4', 'guest4@example.com', '555-3004', '12345', 'guest'),
                ('Guest 5', 'guest5@example.com', '555-3005', '12345', 'guest'),
                ('Guest 6', 'guest6@example.com', '555-3006', '12345', 'guest')
            """).executeUpdate();

            // Assign Guests with loyalty points
            session.createNativeQuery("""
                INSERT INTO Guest (loyaltyPoints, userID) VALUES
                (500, 19), (300, 20), (200, 21), (150, 22), (100, 23), (400, 24)
            """).executeUpdate();

            // Assign Admins to Hotels
            session.createNativeQuery("""
                INSERT INTO Administrator (userID) VALUES
                (1), (2), (3), (4), (5), (6)
            """).executeUpdate();

            // Assign Receptionists to Hotels
            session.createNativeQuery("""
                INSERT INTO Receptionist (workShift, userID) VALUES
                ('morning', 7), ('night', 8),
                ('morning', 9), ('night', 10),
                ('morning', 11), ('night', 12),
                ('morning', 13), ('night', 14),
                ('morning', 15), ('night', 16),
                ('morning', 17), ('night', 18)
            """).executeUpdate();

            // Insert Rooms with statuses and guests assigned
            session.createNativeQuery("""
                INSERT INTO Room (hotelID, roomNumber, roomType, price, status) VALUES
                -- Grand Hotel
                (1, '101', 'single', 100.00, 'available'),
                (1, '102', 'double', 150.00, 'booked'),
                (1, '103', 'suite', 300.00, 'available'),
                -- Oceanview Resort
                (2, '201', 'single', 120.00, 'booked'),
                (2, '202', 'double', 200.00, 'booked'),
                -- Mountain Lodge
                (3, '301', 'family', 250.00, 'available'),
                (3, '302', 'single', 110.00, 'maintenance'),
                -- City Lights Hotel
                (4, '401', 'suite', 350.00, 'booked'),
                (4, '402', 'double', 180.00, 'available'),
                -- Sunset Inn
                (5, '501', 'family', 275.00, 'booked'),
                (5, '502', 'single', 95.00, 'available'),
                -- Royal Plaza
                (6, '601', 'suite', 400.00, 'available'),
                (6, '602', 'double', 170.00, 'booked')
            """).executeUpdate();

            // Bookings linking rooms and guests with hotelID
            session.createNativeQuery("""
                INSERT INTO Booking (guestID, roomID, hotelID, checkInDate, checkOutDate, status, paymentStatus) VALUES
                (19, 2, 1, '2024-12-01', '2024-12-05', 'confirmed', 'completed'),
                (20, 5, 2, '2024-12-02', '2024-12-08', 'confirmed', 'pending'),
                (21, 8, 4, '2024-12-10', '2024-12-15', 'confirmed', 'completed'),
                (22, 11, 5, '2024-12-12', '2024-12-18', 'confirmed', 'pending'),
                (23, 12, 6, '2024-12-20', '2024-12-25', 'confirmed', 'pending')
            """).executeUpdate();

            transaction.commit();
            System.out.println("Test data inserted successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }


    }
}
