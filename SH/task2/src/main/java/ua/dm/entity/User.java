package ua.dm.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Макс on 20.01.2018.
 */
public class User {
    private StringProperty userName;
    private StringProperty password;
    private IntegerProperty level;
    private IntegerProperty experience;
    private StringProperty date;
    private StringProperty type;
    private IntegerProperty days;
    private IntegerProperty click;

    public User(){
        this(new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleIntegerProperty(0), new SimpleIntegerProperty(0),
                new SimpleStringProperty(""), new SimpleStringProperty(""),
                new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    }

    public User(String userName, String password, int level, int experience, String date, String type, int days,
                int click){
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        this.level = new SimpleIntegerProperty(level);
        this.experience = new SimpleIntegerProperty(experience);
        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
        this.days = new SimpleIntegerProperty(days);
        this.click = new SimpleIntegerProperty(click);
    }

    public User(SimpleStringProperty userName, SimpleStringProperty password,
                SimpleIntegerProperty level, SimpleIntegerProperty experience,
                SimpleStringProperty date, SimpleStringProperty type,
                SimpleIntegerProperty days, SimpleIntegerProperty  click) {
        this.userName = userName;
        this.password = password;
        this.level = level;
        this.experience = experience;
        this.date = date;
        this.type = type;
        this.days = days;
        this.click = click;
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
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

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public int getExperience() {
        return experience.get();
    }

    public IntegerProperty experienceProperty() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience.set(experience);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getDays() {
        return days.get();
    }

    public IntegerProperty daysProperty() {
        return days;
    }

    public void setDays(int days) {
        this.days.set(days);
    }

    public int getClick() {
        return click.get();
    }

    public IntegerProperty clickProperty() {
        return click;
    }

    public void setClick(int click) {
        this.click.set(click);
    }
}
