/**
 * Mikhail Kamara 300190412
 */
public class GPScoord {
    private double longitude;
    private double latitude;

    /**
     * Constructor for the class GPSCoord
     * @param longitude longitude
     * @param latitude latitude
     */
    public GPScoord(double longitude, double latitude) {
        this.setLongitude(longitude);
        this.setLatitude(latitude);
    }

    // Getters and setters
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
