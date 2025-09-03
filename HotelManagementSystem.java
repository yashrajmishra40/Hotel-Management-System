import java.util.*;

/**
 * A simple Hotel Management System in Java
 * -----------------------------------------
 * Features:
 *  - Add rooms
 *  - Book rooms
 *  - Check out
 *  - View room status
 *
 * Author: Yashraj Mishra (example)
 * Date: 2025-09-03
 */
class Room {
    private int roomNumber;
    private String type;
    private boolean isBooked;
    private String guestName;

    /**
     * Constructor to initialize room details
     * @param roomNumber - unique room number
     * @param type - type of room (Single/Double/Deluxe)
     */
    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = false;
        this.guestName = "";
    }

    // Getter methods
    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public boolean isBooked() { return isBooked; }
    public String getGuestName() { return guestName; }

    /**
     * Books the room for a guest.
     * @param guestName - name of the guest
     * @return true if booking successful, false if already booked
     */
    public boolean bookRoom(String guestName) {
        if (!isBooked) {
            this.isBooked = true;
            this.guestName = guestName;
            return true;
        }
        return false;
    }

    /**
     * Checks out the guest from the room.
     * @return true if checkout successful, false if room was not booked
     */
    public boolean checkOut() {
        if (isBooked) {
            this.isBooked = false;
            this.guestName = "";
            return true;
        }
        return false;
    }

    /**
     * Displays room details
     */
    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber +
                " | Type: " + type +
                " | Status: " + (isBooked ? "Booked by " + guestName : "Available"));
    }
}


class Hotel {
    private List<Room> rooms;

    /** Constructor initializes hotel with empty room list */
    public Hotel() {
        rooms = new ArrayList<>();
    }

    /**
     * Adds a new room to the hotel
     * @param room - Room object
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Books a room given room number and guest name
     * @param roomNumber - room number to book
     * @param guestName - guest's name
     */
    public void bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.bookRoom(guestName)) {
                    System.out.println("Room " + roomNumber + " booked successfully for " + guestName);
                } else {
                    System.out.println("Room " + roomNumber + " is already booked.");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    /**
     * Checks out a room given room number
     * @param roomNumber - room number
     */
    public void checkOut(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                if (room.checkOut()) {
                    System.out.println("Room " + roomNumber + " checked out successfully.");
                } else {
                    System.out.println("Room " + roomNumber + " was not booked.");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    /** Displays all rooms with their status */
    public void displayAllRooms() {
        for (Room room : rooms) {
            room.displayRoomInfo();
        }
    }
}


public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        // Pre-add some rooms
        hotel.addRoom(new Room(101, "Single"));
        hotel.addRoom(new Room(102, "Double"));
        hotel.addRoom(new Room(201, "Deluxe"));

        int choice;
        do {
            System.out.println("\n=== Hotel Management System ===");
            System.out.println("1. Display All Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Check Out");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAllRooms();
                    break;
                case 2:
                    System.out.print("Enter Room Number: ");
                    int rno = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Guest Name: ");
                    String guest = sc.nextLine();
                    hotel.bookRoom(rno, guest);
                    break;
                case 3:
                    System.out.print("Enter Room Number: ");
                    int roomNo = sc.nextInt();
                    hotel.checkOut(roomNo);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
