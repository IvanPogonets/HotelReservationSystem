import java.util.List;

public class User {
    private static int ID = 1;
    private int userID;
    private String userName;
    private String userPass;
    private double wallet;
    private double credit;
    private int bookedRoom;

    public static void setID(int ID) {
        User.ID = ID;
    }

    // adding a new user to the program, only login and password
    public void addUser(String userName, String userPass, double wallet) {
        this.userID = ID++;
        this.userName = userName;
        this.userPass = userPass;
        this.wallet = wallet;
        this.credit = 0;
        this.bookedRoom = -1;

    }

    //reading a line with the user
    public void readUser(String[] input) {
        this.userID = Integer.parseInt(input[0]);
        this.userName = input[1];
        this.userPass = input[2];
        this.wallet = Double.parseDouble(input[3]);
        if (input.length > 4) {
            this.credit = Double.parseDouble(input[4]);
        }
        if (input.length > 5) {
            this.bookedRoom = Integer.parseInt(input[5]);
            if (userID > ID) {
                ID = userID;
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public int getUserID() {
        return userID;
    }

    public double getUserWallet() {
        return wallet;
    }

    public double getCredit() {
        return credit;
    }

    public int getBookedRoom() {
        return bookedRoom;
    }

    public void setWallet(double sum) {
        this.wallet += sum;
    }

    public static User findUser(List<User> usersList, String currentUser) {
        for (User user : usersList) {
            if (user.getUserName().equals(currentUser)) {
                return user;
            }
        }
        return null;
    }

    public void printInfo() {
        System.out.println("Username: " + userName);
        System.out.println("Wallet: " + wallet);
        if (credit > 0) {
            System.out.println("Credit: " + credit);
        }
        if (bookedRoom > 0) {
            System.out.println("You booked room № " + bookedRoom);
        }
        System.out.println();
    }

    public void setBooked(int roomID, Double cost) {
        this.bookedRoom = roomID;
        this.credit = credit + cost;
        this.wallet = wallet - cost;
    }

    public void setUnbooked(Double CancellationFee) {
        this.bookedRoom = 0;
        this.wallet = wallet + credit - CancellationFee;
        this.credit = 0;
    }

    public String userToCSVString() {
        return userID + "," + userName + "," + userPass + "," + wallet + "," + credit + "," + bookedRoom;
    }
}


