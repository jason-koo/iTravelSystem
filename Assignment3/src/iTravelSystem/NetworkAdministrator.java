package iTravelSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NetworkAdministrator {
    private final StringProperty username;
    private final StringProperty name;
    private final StringProperty password;

    public NetworkAdministrator(String username, String name, String password) {
        this.username = new SimpleStringProperty(username);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
