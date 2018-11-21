package iTravelSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class DiscountProgramStatus {
    private final StringProperty passengerUsername;
    private final StringProperty discountAmount;
    //made it all strings so that it can easily be used in the adapter.
    //can always convert into int/float later.

    public DiscountProgramStatus(String passengerUsername, String discountAmount) {
        this.passengerUsername = new SimpleStringProperty(passengerUsername);
        this.discountAmount = new SimpleStringProperty(discountAmount);
    }

    public String getPassengerUsername() {
        return passengerUsername.get();
    }

    public StringProperty passengerUsernameProperty() {
        return passengerUsername;
    }

    public String getDiscountAmount() {
        return discountAmount.get();
    }

    public StringProperty discountAmountProperty() {
        return discountAmount;
    }

    public void setPassengerUsername(String passengerUsername) {
        this.passengerUsername.set(passengerUsername);
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount.set(discountAmount);
    }
}
