import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWrite {

    static final String separator = ",";

    public static List<Room> readHotel() {
        List<Room> rooms = new ArrayList<>();
        String path = "C:\\Users\\HP\\IdeaProjects\\HotelBooking\\Hotel.csv";
        try (Scanner scanner = new Scanner(new File(path))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                rooms.add(getRoomFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    private static Room getRoomFromLine(String line) {
        Room room = new Room();
        String[] input = line.split(separator);
        room.addRoom(input);
        return room;
    }

    public static void writeHotel(List<Room> roomsList) {
        String path = "C:\\Users\\HP\\IdeaProjects\\HotelBooking\\Hotel.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Number,Type,Price per night,Cancellation fee,Status,UserID,Date of arrive,Date of departure");
            writer.newLine();
            for (Room room : roomsList) {
                writer.write(room.roomToCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        String path = "C:\\Users\\HP\\IdeaProjects\\HotelBooking\\User.csv";
        try (Scanner scanner = new Scanner(new File(path))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                users.add(readUserLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private static User readUserLine(String line) {
        User user = new User();
        String[] input = line.split(separator);
        user.readUser(input);
        return user;
    }

    public static void writeUsers(List<User> userList) {
        String path = "C:\\Users\\HP\\IdeaProjects\\HotelBooking\\User.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("User ID,Login,Password,Wallet,Credit,BookedRoom");
            writer.newLine();
            for (User user : userList) {
                writer.write(user.userToCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}