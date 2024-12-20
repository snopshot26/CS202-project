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

            // Вставка данных в таблицу Hotel
        session.createNativeQuery("""
            INSERT INTO Hotel (hotelName, address) VALUES
            ('Grand Hotel', '123 Main St, Springfield'),
            ('Oceanview Resort', '456 Ocean Ave, Beachside'),
            ('Mountain Lodge', '789 Mountain Rd, Hilltown'),
            ('City Lights Hotel', '1010 Broadway, Metropolis'),
            ('Sunset Inn', '202 Sunset Blvd, Lakeside'),
            ('Royal Plaza', '505 Royal Rd, Downtown')
        """).executeUpdate();

        // Вставка данных в таблицу User (администраторы, ресепшионисты, гости)
        session.createNativeQuery("""
            INSERT INTO User (name, email, phoneNumber, password, userType) VALUES
            -- Администраторы
            ('Admin 1', 'admin1@example.com', '555-1001', '12345', 'ADMINISTRATOR'),
            ('Admin 2', 'admin2@example.com', '555-1002', '12345', 'ADMINISTRATOR'),
            ('Admin 3', 'admin3@example.com', '555-1003', '12345', 'ADMINISTRATOR'),
            ('Admin 4', 'admin4@example.com', '555-1004', '12345', 'ADMINISTRATOR'),
            ('Admin 5', 'admin5@example.com', '555-1005', '12345', 'ADMINISTRATOR'),
            ('Admin 6', 'admin6@example.com', '555-1006', '12345', 'ADMINISTRATOR'),
            -- Ресепшионисты
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
            -- Гости
            ('Guest 1', 'guest1@example.com', '555-3001', '12345', 'GUEST'),
            ('Guest 2', 'guest2@example.com', '555-3002', '12345', 'GUEST'),
            ('Guest 3', 'guest3@example.com', '555-3003', '12345', 'GUEST'),
            ('Guest 4', 'guest4@example.com', '555-3004', '12345', 'GUEST'),
            ('Guest 5', 'guest5@example.com', '555-3005', '12345', 'GUEST'),
            ('Guest 6', 'guest6@example.com', '555-3006', '12345', 'GUEST'),
            -- Горничные
            ('Housekeeper 1', 'hk1@example.com', '555-4001', '12345', 'HOUSEKEEPING'),
            ('Housekeeper 2', 'hk2@example.com', '555-4002', '12345', 'HOUSEKEEPING'),
            ('Housekeeper 3', 'hk3@example.com', '555-4003', '12345', 'HOUSEKEEPING'),
            ('Housekeeper 4', 'hk4@example.com', '555-4004', '12345', 'HOUSEKEEPING'),
            ('Housekeeper 5', 'hk5@example.com', '555-4005', '12345', 'HOUSEKEEPING'),
            ('Housekeeper 6', 'hk6@example.com', '555-4006', '12345', 'HOUSEKEEPING')
        """).executeUpdate();

        // Вставка данных в таблицу Guest (loyaltyPoints, userID)
        session.createNativeQuery("""
            INSERT INTO Guest (loyaltyPoints, userID) VALUES
            (500, 19), (300, 20), (200, 21), (150, 22), (100, 23), (400, 24)
        """).executeUpdate();

        // Вставка данных в таблицу Administrator (userID)
        session.createNativeQuery("""
            INSERT INTO Administrator (userID) VALUES
            (1), (2), (3), (4), (5), (6)
        """).executeUpdate();

        // Вставка данных в таблицу Receptionist (workShift, userID)
        session.createNativeQuery("""
            INSERT INTO Receptionist (workShift, userID) VALUES
            ('MORNING', 7), ('NIGHT', 8),
            ('MORNING', 9), ('NIGHT', 10),
            ('MORNING', 11), ('NIGHT', 12),
            ('MORNING', 13), ('NIGHT', 14),
            ('MORNING', 15), ('NIGHT', 16),
            ('MORNING', 17), ('NIGHT', 18)
        """).executeUpdate();

        // Вставка данных в таблицу Housekeeping (assignedFloors, userID)
        session.createNativeQuery("""
            INSERT INTO Housekeeping (assignedFloors, userID) VALUES
            ('1,2,3', 25),
            ('4,5,6', 26),
            ('7,8,9', 27),
            ('10,11,12', 28),
            ('13,14,15', 29),
            ('16,17,18', 30)
        """).executeUpdate();

        // Вставка данных в таблицу Employee (hotelID, userID, role, startDate, salary)
        session.createNativeQuery("""
            INSERT INTO Employee (hotelID, userID, role, startDate, salary) VALUES
            -- Администраторы (1-6 userID), каждому свой отель:
            (1, 1, 'ADMINISTRATOR', '2023-01-01', 3000.00),
            (2, 2, 'ADMINISTRATOR', '2023-01-02', 3100.00),
            (3, 3, 'ADMINISTRATOR', '2023-01-03', 3200.00),
            (4, 4, 'ADMINISTRATOR', '2023-01-04', 3300.00),
            (5, 5, 'ADMINISTRATOR', '2023-01-05', 3400.00),
            (6, 6, 'ADMINISTRATOR', '2023-01-06', 3500.00),

            -- Ресепшионисты (7-18 userID), по два на отель:
            -- Отель 1 (userID 7,8)
            (1, 7, 'RECEPTIONIST', '2023-02-01', 2000.00),
            (1, 8, 'RECEPTIONIST', '2023-02-02', 2050.00),

            -- Отель 2 (userID 9,10)
            (2, 9, 'RECEPTIONIST', '2023-02-03', 2100.00),
            (2, 10, 'RECEPTIONIST', '2023-02-04', 2150.00),

            -- Отель 3 (userID 11,12)
            (3, 11, 'RECEPTIONIST', '2023-02-05', 2200.00),
            (3, 12, 'RECEPTIONIST', '2023-02-06', 2250.00),

            -- Отель 4 (userID 13,14)
            (4, 13, 'RECEPTIONIST', '2023-02-07', 2300.00),
            (4, 14, 'RECEPTIONIST', '2023-02-08', 2350.00),

            -- Отель 5 (userID 15,16)
            (5, 15, 'RECEPTIONIST', '2023-02-09', 2400.00),
            (5, 16, 'RECEPTIONIST', '2023-02-10', 2450.00),

            -- Отель 6 (userID 17,18)
            (6, 17, 'RECEPTIONIST', '2023-02-11', 2500.00),
            (6, 18, 'RECEPTIONIST', '2023-02-12', 2550.00),

            -- Горничные (25-30 userID), по одному на каждый отель:
            (1, 25, 'HOUSEKEEPING', '2023-03-01', 1800.00),
            (2, 26, 'HOUSEKEEPING', '2023-03-02', 1850.00),
            (3, 27, 'HOUSEKEEPING', '2023-03-03', 1900.00),
            (4, 28, 'HOUSEKEEPING', '2023-03-04', 1950.00),
            (5, 29, 'HOUSEKEEPING', '2023-03-05', 2000.00),
            (6, 30, 'HOUSEKEEPING', '2023-03-06', 2050.00)
        """).executeUpdate();

        // Вставка данных в таблицу Room (hotelID, roomNumber, roomType, price, status)
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

        // Вставка данных в таблицу Booking (guestID, roomID, hotelID, checkInDate, checkOutDate, status, paymentStatus)
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
