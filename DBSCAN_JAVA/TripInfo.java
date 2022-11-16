/**
 * Mikhail Kamara 300190412
 */
public class TripInfo {
    // Attributes
    private int passengerCount;
    private String paymentType;
    private Float fareAmount;
    private Float surcharge;
    private String mta_tax;
    private Float tipAmount;
    private Float tollsAmount;
    private Float totalAmount;

    /*
    * Constructor
    */
    public TripInfo(int passengerCount, String paymentType, Float fareAmount, Float surcharge, String mta_tax,
            Float tipAmount, Float tollsAmount, Float totalAmount) {
        this.passengerCount = passengerCount;
        this.paymentType = paymentType;
        this.fareAmount = fareAmount;
        this.surcharge = surcharge;
        this.mta_tax = mta_tax;
        this.tipAmount = tipAmount;
        this.tollsAmount = tollsAmount;
        this.totalAmount = totalAmount;
    }

    // Getters and setters 

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Float getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(Float fareAmount) {
        this.fareAmount = fareAmount;
    }

    public Float getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Float surcharge) {
        this.surcharge = surcharge;
    }

    public String getMta_tax() {
        return mta_tax;
    }

    public void setMta_tax(String mta_tax) {
        this.mta_tax = mta_tax;
    }

    public Float getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Float tipAmount) {
        this.tipAmount = tipAmount;
    }

    public Float getTollsAmount() {
        return tollsAmount;
    }

    public void setTollsAmount(Float tollsAmount) {
        this.tollsAmount = tollsAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
