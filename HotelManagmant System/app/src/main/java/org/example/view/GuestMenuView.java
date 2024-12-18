package org.example.view;

public class GuestMenuView {
//     private final GuestMenuViewModel viewModel;

//     public GuestMenuView(GuestMenuViewModel viewModel) {
//         this.viewModel = viewModel;
//     }

//     public void showMenu() {
//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("\n=== Guest Menu ===");
//             System.out.println("1. Add New Booking");
//             System.out.println("2. View Available Rooms");
//             System.out.println("3. View My Bookings");
//             System.out.println("4. Cancel Booking");
//             System.out.println("5. Back to Main Menu");

//             System.out.print("Choose an option: ");
//             int choice = scanner.nextInt();

//             switch (choice) {
//                 case 1 -> addBooking(scanner);
//                 case 2 -> viewAvailableRooms();
//                 case 3 -> viewMyBookings(scanner);
//                 case 4 -> cancelBooking(scanner);
//                 case 5 -> { return; }
//                 default -> System.out.println("Invalid choice.");
//             }
//         }
//     }

//     private void addBooking(Scanner scanner) {
//         System.out.print("Enter User ID: ");
//         int userID = scanner.nextInt();
//         System.out.print("Enter Room ID: ");
//         int roomID = scanner.nextInt();
//         System.out.print("Enter Start Date (yyyy-mm-dd): ");
//         Date startDate = Date.valueOf(scanner.next());
//         System.out.print("Enter End Date (yyyy-mm-dd): ");
//         Date endDate = Date.valueOf(scanner.next());

//         if (viewModel.addBooking(userID, roomID, startDate, endDate)) {
//             System.out.println("Booking added successfully.");
//         } else {
//             System.out.println("Failed to add booking.");
//         }
//     }

//     private void viewAvailableRooms() {
//         List<String> rooms = viewModel.getAvailableRooms();
//         rooms.forEach(System.out::println);
//     }

//     private void viewMyBookings(Scanner scanner) {
//         System.out.print("Enter User ID: ");
//         int userID = scanner.nextInt();
//         List<String> bookings = viewModel.getMyBookings(userID);
//         bookings.forEach(System.out::println);
//     }

//     private void cancelBooking(Scanner scanner) {
//         System.out.print("Enter Booking ID: ");
//         int bookingID = scanner.nextInt();
//         if (viewModel.cancelBooking(bookingID)) {
//             System.out.println("Booking canceled successfully.");
//         } else {
//             System.out.println("Failed to cancel booking.");
//         }
//     }
}
