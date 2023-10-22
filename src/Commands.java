import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Commands {

    public static void printAllRooms(List<Room> roomsList) {
        for (Room room : roomsList) {
            System.out.printf("Room № %s; Room Type: %s; Price per night: %.2f; " +
                            "Cancellation fee: %.0f %% from full cost; Status: %s; %n",
                    room.getRoomNumber(), room.getRoomType(), room.getPricePerNight(),
                    room.getCancellationFee(), room.getStatus());
        }
    }

    public static void printAvailableRooms(List<Room> roomsList) {
        for (Room room : roomsList) {
            if (room.getStatus().equals("Available"))
                System.out.printf("Room № %s; Room Type: %s; Price per night: %.2f €; " +
                                "Cancellation fee: %.0f %% from full cost; %n",
                        room.getRoomNumber(), room.getRoomType(), room.getPricePerNight(),
                        room.getCancellationFee());
        }
    }

    public static void bookRoom(User user, List<Room> roomsList,
                                int roomNumber, String dateEntry, String dateDeparture) {
        Room room = Room.findRoom(roomsList, roomNumber);
        if (room == null) {
            System.out.println("Wrong room");
        } else if (room.getStatus().equals("Booked")) {
            System.out.println("The room is booked");
        } else {
            int daysBooked = Commands.countDays(dateDeparture, dateEntry);
            double price = room.getPricePerNight();
            double cost = daysBooked * price;
            //check if there is enough money
            if (user.getUserWallet() < cost) {
                System.out.println("You haven't enough money");
            } else {
                user.setBooked(roomNumber, cost);
                room.setBooked(user.getUserID(), dateEntry, dateDeparture);
                System.out.printf("You booked room № %d , total cost is %.2f €%n", roomNumber, cost);
            }
        }
    }

    public static int countDays(String date1, String date2) {
        // format dd.mm.yyyy
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date dateOne = null;
        Date dateTwo = null;

        try {
            dateOne = format.parse(date1);
            dateTwo = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Number of days between dates in milliseconds
        assert dateOne != null;
        assert dateTwo != null;
        long difference = dateOne.getTime() - dateTwo.getTime();
        // Converting the number of days between dates from milliseconds to days
        return (int) (difference / (24 * 60 * 60 * 1000));
    }

    public static void cancelBooking(User user, List<Room> roomsList, int roomNumber) {
        Room room = Room.findRoom(roomsList, roomNumber);
        if (room == null || user.getBookedRoom() != roomNumber || room.getBookedUserID() != user.getUserID()) {
            System.out.println("Wrong room");
            //check whether this room is reserved by this user
        } else if (user.getBookedRoom() == roomNumber && room.getBookedUserID() == user.getUserID()) {
            //calculate the cancellation fee
            double cancellationFee = user.getCredit() * room.getCancellationFee() * 0.01;
            user.setUnbooked(cancellationFee);
            room.setUnbooked();
            System.out.printf("You canceled your reservation. %n " +
                    "Cancellation fee is %.2f € %n " +
                    "Balance of your wallet is %.2f €%n%n", cancellationFee, user.getUserWallet());
        }
    }

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - View all rooms");
        System.out.println("2 - View available rooms");
        System.out.println("3 - Book a room");
        System.out.println("4 - Cancel booking");
        System.out.println("5 - Top up the wallet");
        System.out.println("6 - Re login");
        System.out.println("7 - Exit");
        System.out.println("Select an option: ");
    }
}
