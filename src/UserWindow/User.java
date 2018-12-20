package UserWindow;

import javafx.beans.property.StringProperty;

public class User extends UserData {


    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public StringProperty usernameProperty() {
        return super.usernameProperty();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public StringProperty passwordProperty() {
        return super.passwordProperty();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public StringProperty addressProperty() {
        return super.addressProperty();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    /**
     * @param username
     * @param address
     * @param password
     */
    public User(String username, String address, String password) {
        super(username, address, password);
    }


}
