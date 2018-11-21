package iTravelSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Servers {

    private final StringProperty serverID;
    private final StringProperty patchVersion;
    private final StringProperty upgradeVersion;

    public Servers(String serverID, String patchVersion, String upgradeVersion) {
        this.serverID = new SimpleStringProperty(serverID);
        this.patchVersion = new SimpleStringProperty(patchVersion);
        this.upgradeVersion = new SimpleStringProperty(upgradeVersion);
    }

    public String getServerID() {
        return serverID.get();
    }

    public StringProperty serverIDProperty() {
        return serverID;
    }

    public String getPatchVersion() {
        return patchVersion.get();
    }

    public StringProperty patchVersionProperty() {
        return patchVersion;
    }

    public String getUpgradeVersion() {
        return upgradeVersion.get();
    }

    public StringProperty upgradeVersionProperty() {
        return upgradeVersion;
    }

    public void setServerID(String serverID) {
        this.serverID.set(serverID);
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion.set(patchVersion);
    }

    public void setUpgradeVersion(String upgradeVersion) {
        this.upgradeVersion.set(upgradeVersion);
    }
}
