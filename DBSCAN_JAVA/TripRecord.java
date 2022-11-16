/**
 * Mikhail Kamara 300190412
 */
public class TripRecord {
    // Attributes
    private GPScoord pickup_Location;
    private GPScoord dropoff_Location;
    private String pickup_DateTime;
    private float trip_Distance;

    // Constructor
    public TripRecord(GPScoord pickup_Location, GPScoord dropoff_Location, String pickup_DateTime,
            float trip_Distance) {
        this.pickup_Location = pickup_Location;
        this.dropoff_Location = dropoff_Location;
        this.pickup_DateTime = pickup_DateTime;
        this.trip_Distance = trip_Distance;
    }

    //Getters and setters
    
    public GPScoord getPickup_Location() {
        return pickup_Location;
    }

    public void setPickup_Location(GPScoord pickup_Location) {
        this.pickup_Location = pickup_Location;
    }

    public GPScoord getDropoff_Location() {
        return dropoff_Location;
    }

    public void setDropoff_Location(GPScoord dropoff_Location) {
        this.dropoff_Location = dropoff_Location;
    }

    public String getPickup_DateTime() {
        return pickup_DateTime;
    }

    public void setPickup_DateTime(String pickup_DateTime) {
        this.pickup_DateTime = pickup_DateTime;
    }

    public float getTrip_Distance() {
        return trip_Distance;
    }

    public void setTrip_Distance(float trip_Distance) {
        this.trip_Distance = trip_Distance;
    }

}
