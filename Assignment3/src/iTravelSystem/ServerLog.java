package iTravelSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServerLog {
    private final StringProperty serverID;
    private final StringProperty errorLogs;
    private final StringProperty hardwareLogs;

    public ServerLog(String serverID, String errorLogs, String hardwareLogs) {
        this.serverID = new SimpleStringProperty(serverID);
        this.errorLogs = new SimpleStringProperty(errorLogs);
        this.hardwareLogs = new SimpleStringProperty(hardwareLogs);
    }

    public String getServerID() {
        return serverID.get();
    }

    public StringProperty serverIDProperty() {
        return serverID;
    }

    public String getErrorLogs() {
        return errorLogs.get();
    }

    public StringProperty errorLogsProperty() {
        return errorLogs;
    }

    public String getHardwareLogs() {
        return hardwareLogs.get();
    }

    public StringProperty hardwareLogsProperty() {
        return hardwareLogs;
    }

    public void setServerID(String serverID) {
        this.serverID.set(serverID);
    }

    public void setErrorLogs(String errorLogs) {
        this.errorLogs.set(errorLogs);
    }

    public void setHardwareLogs(String hardwareLogs) {
        this.hardwareLogs.set(hardwareLogs);
    }
}
