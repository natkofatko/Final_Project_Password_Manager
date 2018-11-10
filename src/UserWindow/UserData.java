package UserWindow;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserData {

private final StringProperty username;
//private final StringProperty category;
private final StringProperty password;
private final StringProperty address;

public UserData(String username, String address, String password)
{
    this.username=new SimpleStringProperty(username);
   // this.category=new SimpleStringProperty(category);
    this.password=new SimpleStringProperty(password);
    this.address=new SimpleStringProperty(address);

}


    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }




    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
