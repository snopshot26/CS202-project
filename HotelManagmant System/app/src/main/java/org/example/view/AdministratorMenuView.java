package org.example.view;



public class AdministratorMenuView {
//     private final DatabaseManager db;

//     public AdminMenu(DatabaseManager db) {
//         this.db = db;
//     }

//     public void showAdminMenu() {
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.println("\n=== Admin Menu ===");
//             System.out.println("1. Add Room");
//             System.out.println("2. Delete Room");
//             System.out.println("3. Update Room Status");
//             System.out.println("4. View All Booking Records");
//             System.out.println("5. Back to Main Menu");
//             System.out.print("Choose an option: ");

//             int choice = scanner.nextInt();
//             switch (choice) {
//                 case 1 -> addRoom(scanner);
//                 case 2 -> deleteRoom(scanner);
//                 case 3 -> updateRoomStatus(scanner);
//                 case 4 -> viewAllBookingRecords();
//                 case 5 -> { return; }
//                 default -> System.out.println("Invalid choice. Try again.");
//             }
//         }
//     }

//     private void addRoom(Scanner scanner) {
//         System.out.print("Enter Room Type: ");
//         String roomType = scanner.next();
//         System.out.print("Enter Price: ");
//         double price = scanner.nextDouble();

//         if (db.addRoom(roomType, price)) {
//             System.out.println("Room added successfully.");
//         } else {
//             System.out.println("Failed to add room.");
//         }
//     }

//     private void deleteRoom(Scanner scanner) {
//         System.out.print("Enter Room ID: ");
//         int roomID = scanner.nextInt();
//         if (db.deleteRoom(roomID)) {
//             System.out.println("Room deleted successfully.");
//         } else {
//             System.out.println("Failed to delete room.");
//         }
//     }

//     private void updateRoomStatus(Scanner scanner) {
//         System.out.print("Enter Room ID: ");
//         int roomID = scanner.nextInt();
//         System.out.print("Enter Status (Available/Booked): ");
//         String status = scanner.next();

//         if (db.updateRoomStatus(roomID, status)) {
//             System.out.println("Room status updated.");
//         } else {
//             System.out.println("Failed to update status.");
//         }
//     }

//     private void viewAllBookingRecords() {
//         List<String> bookings = db.viewAllBookingRecords();
//         bookings.forEach(System.out::println);
//     }
}
