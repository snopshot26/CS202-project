// package org.example.viewmodel;

// public class GuestMenuViewModel {
//     private final DatabaseManager db;

//     public GuestMenuViewModel(DatabaseManager db) {
//         this.db = db;
//     }

//     public boolean addBooking(int userID, int roomID, Date start, Date end) {
//         return db.addNewBooking(userID, roomID, start, end);
//     }

//     public List<String> getAvailableRooms() {
//         return db.viewAvailableRooms();
//     }

//     public List<String> getMyBookings(int userID) {
//         return db.viewMyBookings(userID);
//     }

//     public boolean cancelBooking(int bookingID) {
//         return db.cancelBooking(bookingID);
//     }
// }
