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
                ('Admin 1', 'admin1@example.com', '555-1001', '12345', 'ADMINISTRATOR'),
                ('Admin 2', 'admin2@example.com', '555-1002', '12345', 'ADMINISTRATOR'),
                ('Admin 3', 'admin3@example.com', '555-1003', '12345', 'ADMINISTRATOR'),
                ('Admin 4', 'admin4@example.com', '555-1004', '12345', 'ADMINISTRATOR'),
                ('Admin 5', 'admin5@example.com', '555-1005', '12345', 'ADMINISTRATOR'),
                ('Admin 6', 'admin6@example.com', '555-1006', '12345', 'ADMINISTRATOR'),
                -- Receptionists
                ('Receptionist 1', 'rec1@example.com', '555-2001', '12345', 'RECEPTIONIST'),
                ('Receptionist 2', 'rec2@example.com', '555-2002', '12345', 'RECEPTIONIST'),
                ('Receptionist 3', 'rec3@example.com', '555-2003', '12345', 'RECEPTIONIST'),
                ('Receptionist 4', 'rec4@example.com', '555-2004', '12345', 'RECEPTIONIST'),
                ('Receptionist 5', 'rec5@example.com', '555-2005', '12345', 'RECEPTIONIST'),
                ('Receptionist 6', 'rec6@example.com', '555-2006', '12345', 'RECEPTIONIST'),
                ('Receptionist 7', 'rec7@example.com', '555-2007', '12345', 'RECEPTIONIST'),
                ('Receptionist 8', 'rec8@example.com', '555-2008', '12345', 'RECEPTIONIST'),
                ('Receptionist 9', 'rec9@example.com', '555-2009', '12345', 'RECEPTIONIST'),
                ('Receptionist 10', 'rec10@example.com', '555-2010', '12345', 'RECEPTIONIST'),
                ('Receptionist 11', 'rec11@example.com', '555-2011', '12345', 'RECEPTIONIST'),
                ('Receptionist 12', 'rec12@example.com', '555-2012', '12345', 'RECEPTIONIST'),
                -- Guests
                ('Guest 1', 'guest1@example.com', '555-3001', '12345', 'GUEST'),
                ('Guest 2', 'guest2@example.com', '555-3002', '12345', 'GUEST'),
                ('Guest 3', 'guest3@example.com', '555-3003', '12345', 'GUEST'),
                ('Guest 4', 'guest4@example.com', '555-3004', '12345', 'GUEST'),
                ('Guest 5', 'guest5@example.com', '555-3005', '12345', 'GUEST'),
                ('Guest 6', 'guest6@example.com', '555-3006', '12345', 'GUEST')
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
                ('MORNING', 7), ('NIGHT', 8),
                ('MORNING', 9), ('NIGHT', 10),
                ('MORNING', 11), ('NIGHT', 12),
                ('MORNING', 13), ('NIGHT', 14),
                ('MORNING', 15), ('NIGHT', 16),
                ('MORNING', 17), ('NIGHT', 18)
            """).executeUpdate();


            // Insert Rooms with statuses and guests assigned
            session.createNativeQuery("""
                INSERT INTO Room (hotelID, roomNumber, roomType, price, status) VALUES
                -- Grand Hotel
                (1, '101', 'SINGLE', 100.00, 'AVAILABLE'),
                (1, '102', 'DOUBLE', 150.00, 'BOOKED'),
                (1, '103', 'SUITE', 300.00, 'AVAILABLE'),
                -- Oceanview Resort
                (2, '201', 'SINGLE', 120.00, 'BOOKED'),
                (2, '202', 'DOUBLE', 200.00, 'BOOKED'),
                -- Mountain Lodge
                (3, '301', 'FAMILY', 250.00, 'AVAILABLE'),
                (3, '302', 'SINGLE', 110.00, 'MAINTENANCE'),
                -- City Lights Hotel
                (4, '401', 'SUITE', 350.00, 'BOOKED'),
                (4, '402', 'DOUBLE', 180.00, 'AVAILABLE'),
                -- Sunset Inn
                (5, '501', 'FAMILY', 275.00, 'BOOKED'),
                (5, '502', 'SINGLE', 95.00, 'AVAILABLE'),
                -- Royal Plaza
                (6, '601', 'SUITE', 400.00, 'AVAILABLE'),
                (6, '602', 'DOUBLE', 170.00, 'BOOKED')
            """).executeUpdate();

            // Bookings linking rooms and guests with hotelID
            session.createNativeQuery("""
                INSERT INTO Booking (guestID, roomID, hotelID, checkInDate, checkOutDate, status, paymentStatus) VALUES
                (19, 2, 1, '2024-12-01', '2024-12-05', 'CONFIRMED', 'COMPLETED'),
                (20, 5, 2, '2024-12-02', '2024-12-08', 'CONFIRMED', 'PENDING'),
                (21, 8, 4, '2024-12-10', '2024-12-15', 'CONFIRMED', 'COMPLETED'),
                (22, 11, 5, '2024-12-12', '2024-12-18', 'CONFIRMED', 'PENDING'),
                (23, 12, 6, '2024-12-20', '2024-12-25', 'CONFIRMED', 'PENDING')
            """).executeUpdate();

            transaction.commit();
            System.out.println("Test data inserted successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }


    }
}
