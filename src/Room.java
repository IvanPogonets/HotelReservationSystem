import java.util.List;

public class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private double cancellationFee; // expressed as a percentage of the credit
    private String status;
    private int bookedUserID;
    private String dateOfEntry;
    private String dateOfDeparture;

    // new room from file line
    public void addRoom(String[] input) {
        this.roomNumber = Integer.parseInt(input[0]);
        this.roomType = input[1];
        this.pricePerNight = Double.parseDouble(input[2]);
        this.cancellationFee = Double.parseDouble(input[3]);
        this.status = input[4];
        //if there is a booking record
        if (input.length > 5) {
            this.bookedUserID = Integer.parseInt(input[5]);
            this.dateOfEntry = input[6];
            this.dateOfDeparture = input[6];
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getBookedUserID() {
        return bookedUserID;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public String getStatus() {
        return status;
    }

    public static Room findRoom(List<Room> roomsList, int roomNumber) {
        for (Room room : roomsList) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }


    public void setBooked(int bookedUserID, String dateEntry, String dateDeparture) {
        this.bookedUserID = bookedUserID;
        this.dateOfEntry = dateEntry;
        this.dateOfDeparture = dateDeparture;
        this.status = "Booked";
    }

    public void setUnbooked() {
        this.bookedUserID = 0;
        this.dateOfEntry = null;
        this.dateOfDeparture = null;
        this.status = "Available";
    }

    public String roomToCSVString() {
        return roomNumber + "," + roomType + "," + pricePerNight + "," + cancellationFee + "," + status + "," + bookedUserID + "," + dateOfEntry + "," + dateOfDeparture;

    }
}
