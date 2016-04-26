import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class DBManager {
    //  Case sensitive

    public static final String password = "password";
    public static final String username = "root";
    public static final Connection conn = connect();

    public static void initializeDB() {
        System.out.println("Starting:");
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "create database if not exists learnr;";
            stmt.executeUpdate(sql);
            sql = "use learnr;";
            stmt.executeUpdate(sql);


            //A list of user IDs, mapping screen name + password to
            //a unique user ID. This will simplify the other tables

            sql = "CREATE TABLE IF NOT EXISTS " + 
                    "userIDTable " +
                    "(username varchar(16) NOT NULL, " +
                    " password varchar(6) NOT NULL, " +
                    " uid int(8) AUTO_INCREMENT, " +
                    " PRIMARY KEY (uid));";

            stmt.executeUpdate(sql);


            //similarly, for every meetup we create we're going to have an event id.
            sql = "CREATE TABLE IF NOT EXISTS " +
                    "eventTable" +
                    "(eid int(8) AUTO_INCREMENT, " +
                    "subject varchar(10) NOT NULL, " +
                    "classname varchar(10) NOT NULL, " +
                    "starttime datetime, " +
                    "endtime datetime, " +
                    "createdby int(8), " +
                    "PRIMARY KEY (eid)); ";

            stmt.executeUpdate(sql);

            //more detailed table for user bios
            //may need to add a field for "IS ADMIN", etc
            sql = "CREATE TABLE IF NOT EXISTS " +
                    "userDetails" +
                    "(uid int(8), " +
                    "name varchar(32), " +
                    "major varchar(10), " +
                    "gender char(10), " +
                    "bio text, " +
                    "joindate datetime, " +
                    "PRIMARY KEY (uid)); ";

            stmt.executeUpdate(sql);

            //The table of RSVPs.
            //I couldn't think of a better way to do this; basically will be
            //A list of doubles, telling us that a user "UID" is going to meetup "EID"
            //To retrieve who is attending, we will have to
            //"SELECT uid from RSVPS where eid = X"

            sql = "CREATE TABLE IF NOT EXISTS "
                    "RSVPS " +
                    "(eid int(8), uid int(8));";
            stmt.executeUpdate(sql);



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            //  Close resources
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Ending...");
    }

    //method to add a new study meetup to the table 
    public static bool createMeetup(subject, classname, starttime, endtime, user){

    }

    //method to add a new user
    public static bool createUser(sn, pass){

    }

    //method to add a user to the RSVP list
    //(RSVP list will be a list of doubles, ($event is going to $event))
    public static bool joinMeetup(eventID, userID){

    }

    //TODO:
    //GetAttending
    //IsAttending(User, Meetup)
    //more stuff

    public static Connection connect() {
    /*  Returns a java.sql.Connection object for accessing database assuming password, username, databaseName are valid
    */
        //  Register the JDBC Driver. Make sure CLASSPATH env variable is set correctly
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //  Attempt to connect to database. Make sure static variables password, username are valid.
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/", username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //  Problem establishing connection
        return null;
    }

    public static void main(String[] args){
        initializeDB();

    }


}




