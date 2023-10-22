import java.util.List;
import java.util.Scanner;

public class Login {

    public static User logIn(List<User> usersList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
//            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username");
            String inpUser = scanner.nextLine();
            System.out.println("Enter password");
            String inpUserPass = scanner.nextLine();
            User foundUser = User.findUser(usersList, inpUser); // search among current users
            if (foundUser == null) {
                System.out.println("There is no such User. Wanna sign up? Type Yes or No");
                String answer = scanner.nextLine();
                if (answer.equals("Yes")) {
                    String newUsername = newUsernameVal();
                    String newUserPass = newPassVal();
                    User user = new User();
                    System.out.println("Top up your wallet:");
                    double wallet = Double.parseDouble(scanner.nextLine());
                    user.addUser(newUsername, newUserPass, wallet);
                    usersList.add(user);
                    user.printInfo();
                    return user;
                } else if (answer.equals("No")) {
                    System.out.println("Try again");
                }
            }
            //if there is an error in the password
            else if (!foundUser.getUserPass().equals(inpUserPass)) {
                System.out.println("Wrong password");

            } else {
                foundUser.printInfo(); // if the user exists, print his data
                return foundUser;
            }
        }
    }

    public static String newPassVal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter user password");
            String userPass = scanner.nextLine();
            int sumOfChar = 0;
            int sumOfInt = 0;
            for (int i = 0; i < userPass.length(); i++) {
                if (Character.isLetter(userPass.charAt(i))) {
                    sumOfChar += 1;
                }
                if (Character.isDigit(userPass.charAt(i))) {
                    sumOfInt += 1;
                }
            }
            if (sumOfChar + sumOfInt != userPass.length()) {
                System.out.println("Password must contain only digits ad letters");
            } else if (sumOfInt == 0) {
                System.out.println("Password must contain at least 1 digit");
            } else if (2 > userPass.length() || userPass.length() > 10) {
                System.out.println("Password must be between 2 and 10 characters");
            } else {
                return userPass;
            }
        }
    }


    public static String newUsernameVal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter username");
            String username = scanner.nextLine();
            boolean charIsNotLetterOrDigit = false;
            for (int i = 0; i < username.length(); i++) {
                if (!Character.isLetterOrDigit(username.charAt(i))) {
                    charIsNotLetterOrDigit = true;
                }
            }
            if (2 > username.length() || username.length() > 10) {
                System.out.println("Username must be between 2 and 10 characters");
            } else if (charIsNotLetterOrDigit) {
                System.out.println("Username must contain only letters and digits");
            } else
                return username;
        }
    }
}
