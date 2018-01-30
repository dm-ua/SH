package ua.dm.db;

import ua.dm.controller.SceneController;
import ua.dm.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Макс on 20.01.2018.
 */
public class UserDB {
    private final static Logger LOGGER = Logger.getLogger("UsersDB.class");
    private Connection conn;
    private String userName;
    private String password;
    private int level;
    private int experience;
    private String date ;
    private String type ;
    private int days;
    private int click;
    private LocalDate todayDate = LocalDate.now();


    public static Connection getConn() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\SH\\task2\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void CreateDB(){
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT, userName TEXT," +
                " password TEXT, level INTEGER, experience INTEGER," +
                " date TEXT, type TEXT, days INTEGER, " +
                "click INTEGER)";
        try (Connection connection = getConn();
             Statement statement = connection.createStatement();) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoDB(User user) {
        System.out.println("111111111111111111111111111111111111");
        this.insertIntoDB(user.getUserName(), user.getPassword(), user.getLevel(),user.getExperience(), user.getDate(),
                user.getType(), user.getDays(),user.getClick());
    }

    public void insertIntoDB(String userName, String password, Integer level, Integer experience, String date,
                             String type, Integer days, Integer click) {
        System.out.println("8888888888888888888888888888888888888888888888888888");
        String sql = ("INSERT INTO users(userName,password,level,experience,date,type,days,click)" +
                " VALUES (?,?,?,?,?,?,?,?); ");
        try(Connection connection = getConn();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, userName);
            statement.setString(2, password);
            statement.setInt(3,level);
            statement.setInt(4,experience);
            statement.setString(5,date);
            statement.setString(6,type);
            statement.setInt(7,days);
            statement.setInt(8,click);
            statement.execute();
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }

        System.out.println("Таблица заполнена");
    }

    public void UpdateExp(String userName, String password) {
        System.out.println(userName + password+"                      |||||||| ");
        allGet(userName, password);
        String string1 = ("UPDATE users SET experience =? WHERE userName = '" + userName
                + "' and  password = '" + password + "'");
        String string2= ("UPDATE users SET experience =?, click =? WHERE userName = '" + userName
                + "' and  password = '" + password + "'");
        System.out.println(getType()+" ! "+ getDate()+" ! "+getExperience());
        if (getType().equals("paid")) {
            try (Connection conn = getConn();
                 PreparedStatement preparedStatement = conn.prepareStatement(string1)) {
                preparedStatement.setInt(1, (getExperience()) + 25);
                preparedStatement.executeUpdate();
                System.out.println(getExperience());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(getType().equals("free")&& getClick()!=5){
            try (Connection conn = getConn();
                 PreparedStatement preparedStatement = conn.prepareStatement(string2)) {
                preparedStatement.setInt(1, (getExperience()) + 25);
                preparedStatement.setInt(2, (getClick() + 1));
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void allGet(String userName, String password){
        String sql = "SELECT * FROM users WHERE userName = '" + userName + "' AND password = '"
                + password +"'";
        System.out.println("+++++++++++++++++");
        try(Connection conn = getConn();
        Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("__________________" + userName +"__________"+password);
            if (resultSet.next()){
                setExperience(resultSet.getInt("experience"));
                setLevel(resultSet.getInt("level"));
                setDate(resultSet.getString("date"));
                setType(resultSet.getString("type"));
                setDays(resultSet.getInt("days"));
                setClick(resultSet.getInt("click"));
                System.out.println(getExperience() + "   ? "+ getType() + "   ? " + getDays());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void newDay(String userName, String password) {
        allGet(userName, password);
        String sql1 = ("UPDATE users SET level =?, experience=?, date =?, days =? WHERE userName = '" + userName
                + "' and  password = '" + password + "'");
        String sql2 = ("UPDATE users SET type =? WHERE userName = '" + userName
                + "' and  password = '" + password + "'");
        String sql3 = ("UPDATE users SET level =?, experience=?, date =?, click=? WHERE userName = '" + userName
                + "' and  password = '" + password + "'");
        if (getType().equals("paid") && getDays()==0){
            try (Connection connection = getConn();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
                preparedStatement.setString(1, "free");
                preparedStatement.executeUpdate();
            }catch (SQLException sqlexception){
                LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
            }
        }
        if (getTodayDate().toString().compareTo(getDate())!=0 && getType().equals("paid")) {
            try (Connection connection = getConn();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setInt(1, ((getExperience() + 500)/100));
                preparedStatement.setInt(2, (getExperience() + 500));
                preparedStatement.setString(3, getTodayDate().toString());
                preparedStatement.setInt(4, (getDays()-1));
                preparedStatement.executeUpdate();
            } catch (SQLException sqlexception) {
                LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
            }
        }
        if (getTodayDate().toString().compareTo(getDate())!=0 && getType().equals("free")){
            try (Connection connection = getConn();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql3)) {
                preparedStatement.setInt(1, ((getExperience() + 500)/100));
                preparedStatement.setInt(2, (getExperience() + 500));
                preparedStatement.setString(3, getTodayDate().toString());
                preparedStatement.setInt(4, 0);
                preparedStatement.executeUpdate();
            } catch (SQLException sqlexception) {
                LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
            }
        }
    }

    public String day(String userName, String password){
        allGet(userName,password);
        System.out.println(getDays());
        return String.valueOf(getDays());
    }

    public boolean logIn(String userNameField, String passwordField){
        boolean resoult = false;
        String sql = "SELECT * FROM users";
        try (Connection connection = getConn();
        Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("userName");
                String password = resultSet.getString("password");
                if (userNameField.equals(name) && passwordField.equals(password)) {
                    setUserName(userNameField);
                    setPassword(passwordField);
                    SceneController sceneController = new SceneController();
                    sceneController.str(userNameField, password);
                    System.out.println(userNameField + "!!!!"+password);
                    resoult = true;
                }
            }
        }catch (SQLException sqlexception) {
            LOGGER.log(Level.WARNING, sqlexception.getMessage(), sqlexception);
        }
        return resoult;
    }

    public int getDaysToEnd(String userName, String password){
        allGet(userName, password);
        return getDays();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public UserDB() {

    }
}