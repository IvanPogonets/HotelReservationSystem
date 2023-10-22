import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadWrite.readUsers();
        List<Room> roomsList = ReadWrite.readHotel();
        List<User> usersList = ReadWrite.readUsers();
        System.out.println("Please log in");
        User currentUser = Login.logIn(usersList);
        boolean exit = false;
        while (!exit) {
            Commands.printMenu();
            int command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 1:
                    Commands.printAllRooms(roomsList);
                    break;
                case 2:
                    Commands.printAvailableRooms(roomsList);
                    break;
                case 3:
                    System.out.println("Enter number of room");
                    int numberOfBookingRoom = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter date of arrive");
                    String dateEntry = scanner.nextLine();
                    System.out.println("Enter date of departure");
                    String dateDeparture = scanner.nextLine();
                    Commands.bookRoom(currentUser,roomsList,numberOfBookingRoom,dateEntry,dateDeparture);
                    break;
                case 4:
                    System.out.println("Enter number of room");
                    int numberOfRoom = Integer.parseInt(scanner.nextLine());
                    Commands.cancelBooking(currentUser,roomsList,numberOfRoom);
                    break;
                case 5:
                    System.out.println("Enter the sum");
                    double sum = scanner.nextDouble();
                    currentUser.setWallet(sum);
                    System.out.printf("Wallet contains %.2f €%n",currentUser.getUserWallet());
                    break;
                case 6:
                    currentUser = Login.logIn(usersList);
                    break;
                case 7:
                    exit = true;
                    break;
            }
        }
        ReadWrite.writeUsers(usersList);
        ReadWrite.writeHotel(roomsList);
    }
}

