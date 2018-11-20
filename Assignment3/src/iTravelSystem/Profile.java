package iTravelSystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Profile {

    private final IntegerProperty UserNumber;
    private final StringProperty FirstName;
    private final StringProperty LastName;

    public Profile(int userNumber, String firstName, String lastName){
        this.UserNumber = new SimpleIntegerProperty(userNumber);
        this.FirstName = new SimpleStringProperty(firstName);
        this.LastName = new SimpleStringProperty(lastName);
    }
    public void setUserNumber(int userNumber) {UserNumber.set(userNumber); }
    public IntegerProperty UserNumberProperty() {
        return UserNumber;
    }
    public int getUserNumber() {return UserNumber.get(); }

    public void setFirstName(String firstName) {FirstName.set(firstName); }
    public StringProperty FirstNameProperty() {
        return FirstName;
    }
    public String getFirstName() { return FirstName.get();
    }

    public void setLastName(String lastName) {LastName.set(lastName); }
    public StringProperty LastNameProperty() {
        return LastName;
    }
    public String getLastName() {return LastName.get(); }


}


