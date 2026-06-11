import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BusFI {
    String busNumber;
    String driverName;
    int availableSeats;
    double fare;


    public BusFI(String busNumber, String driverName, int availableSeats, double fare) {
        this.busNumber = busNumber;
        this.driverName = driverName;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    public static void main(String[] args) {
        List<BusFI> buses = Arrays.asList(
                new BusFI("BA123", "Ram", 10, 500),
                new BusFI("BA456", "Hari", 0, 700),
                new BusFI("BA789", "Sita", 5, 600)
        );

        Supplier<String> busFISupplier = () -> "Bus Created Successfully";
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}

