package UserWindow;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {

    private final StringProperty username;
    //private final StringProperty category;
    private final StringProperty password;
    private final StringProperty address;


    /**
     *
     * @param username
     * @param address
     * @param password
     */

    public UserData(String username, String address, String password) {
        this.username = new SimpleStringProperty(username);
        // this.category=new SimpleStringProperty(category);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);

    }


    /**
     *
     * @return
     */

    public String getUsername() {
        return username.get();
    }

    /**
     *
     * @return
     */
    public StringProperty usernameProperty() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     *
     * @return
     */

    public String getPassword() {
        return password.get();
    }

    /**
     *
     * @return
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address.get();
    }

    /**
     *
     * @return
     */
    public StringProperty addressProperty() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address.set(address);
    }
}
