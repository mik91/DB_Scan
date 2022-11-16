/**
 * Mikhail Kamara 300190412
 */
public class Taxi {
    private int ID;
    private String vendorName;
    private TripInfo tripInfo;
    private TripRecord tripRecord;
    private String rateCode;
    private String StoreAndForward;
    private labelStatus eStatus;

    /**
     * Enum of the different type of status
     */
    public enum labelStatus {
        UNDEFINED,
        NOISE,
        PART_CLUSTER,
        BORDER
    }

    // Constructor 
    public Taxi(int iD, String vendorName, TripInfo tripInfo, TripRecord tripRecord, String rateCode, String storeAndForward) {
        ID = iD;
        this.vendorName = vendorName;
        this.tripInfo = tripInfo;
        this.tripRecord = tripRecord;
        this.rateCode = rateCode;
        StoreAndForward = storeAndForward;
        eStatus = labelStatus.UNDEFINED;
    }

    // Getters and setters

    public labelStatus getStatus() {
        return eStatus;
    }

    public void setStatus(labelStatus eStatus) {
        this.eStatus = eStatus;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public TripInfo getTripInfo() {
        return tripInfo;
    }

    public void setTripInfo(TripInfo tripInfo) {
        this.tripInfo = tripInfo;
    }

    public TripRecord getTripRecord() {
        return tripRecord;
    }

    public void setTripRecord(TripRecord tripRecord) {
        this.tripRecord = tripRecord;
    }

    public String getRateCode() {
        return rateCode;
    }

    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    public String getStoreAndForward() {
        return StoreAndForward;
    }

    public void setStoreAndForward(String storeAndForward) {
        StoreAndForward = storeAndForward;
    }

}
