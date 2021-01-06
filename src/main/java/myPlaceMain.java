import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
* TO Do:
* -finish console test, including all functionality from java to database
* -create actual webpage access and test
* -create html/css webpage for login and also for viewing posts
* -for login: verify valid data from user here, but then verify valid user using database functions?
* - create actual display and access to posts
 */

public class myPlaceMain {
    //used to connect to the database being used
    private static Connection conn;
    private static Statement stmt;
    private static boolean _loggedIn = false;
    private static ArrayList<User> _userList;
    private static ArrayList<User> _loggedInUsers;
    private static ArrayList<Result> _queryResult;
    private static boolean _successful = false;


    public static void main(String[] args){
        doConsoleTest();
    }

    /*
    * Console run version of website and database functionality
     */
    private static void doConsoleTest()
    {
        //getCurrentUserList();

        Scanner in = new Scanner(System.in);
        System.out.println("What would you like to do today?");
        String input = in.nextLine();
        while(!input.equals("exit"))
        {
            if(input.equals("signup"))
            {
                signUpUser();
            }
            else if(input.equals("login"))
            {
                System.out.println("Username: ");
                String u = in.nextLine();
                System.out.println("Password: ");
                String p = in.nextLine();
                loginUser(u, p);
            }
            else if(input.equals("newpost"))
            {
                createPost();
            }
            else if(input.equals("exit"))
            {
                break;
            }
            else
            {
                System.out.println("That's not a valid command. Try again.");
            }
            System.out.println("What now?");
            input = in.nextLine();
        }
    }

    /*
    *
     */
    private static void getCurrentUserList()
    {
        String sql = "";
    }

    /*
    * Creates a post within the database and on the website
     */
    private static void createPost()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you have to say?");
        String post = in.nextLine();

        String sql = "";

    }


    /*
    * Signs up a new user with the site, creating their information within the database.
     */
    private static void signUpUser()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome! First give us your name (no middle names please! only first & last): ");
        String[] n = in.nextLine().split(" ");
        System.out.println("Now, the username you'd like to use: ");
        String u = in.nextLine();
        System.out.println("And now the password: ");
        String p = in.nextLine();
        String p1 = "";
       do{
            System.out.println("Confirm the password: ");
            p1 = in.nextLine();
        }
        while(!p.equals(p1));

        System.out.println("trying...");

        try
        {
            System.out.println("entering your info!");
            String sql = "INSERT INTO [myPlaceDB].[dbo].[myUser] (userName, userPass, " +
                    "firstName, lastName) VALUES('" + u + "', '" + p +"', '" + n[0] + "', '" + n[1] + "')";
            //System.out.println(sql);
            queryDatabase(sql, false, false);
            System.out.println("You're all signed up!");
        }
        catch(SQLException e)
        {
            System.out.println("SQL issue :(");
            e.printStackTrace();
        }

    }

    /*
    * Logs in a user that has already set up an account
     */
    private static void loginUser(String username, String password)
    {
        try {
            String sql = "SELECT * FROM [myPlaceDB].[dbo].[myUser] WHERE [myUser].[userName] = '" + username + "' AND [myUser].[userPass] = '" + password + "'";
            queryDatabase(sql, false, false);

            if(_successful)
            {
                _loggedIn = true;
                //_loggedInUsers.add(new User(username, ));
            }
            System.out.println(_loggedIn);
        }
        catch(SQLException e)
        {
            System.out.println("SQL issue :(");
            e.printStackTrace();
        }
    }

    /*
    * Queries database given input of a SQL query as a string that will be created before it is called,
    * allowing the database connection to only be created exactly when needed
     */
    private static void queryDatabase( String sql, boolean display, boolean getResult) throws SQLException //, boolean display)
    {
        establishConnectionToDatabase();
        stmt = conn.createStatement();

        ResultSet rs = executeQueryStatement(sql);

        _successful = rs.next();


        if(display)
            displayQueryResults(rs);

        if(getResult)
            getQueryResults(rs);

        conn.close(); //closes connection to the database

        //return rs;
    }

    private static void establishConnectionToDatabase()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost;integratedSecurity=true;";
            conn = DriverManager.getConnection(connectionURL);
        }
        catch(ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static ResultSet executeQueryStatement(String s) throws SQLException
    {
        ResultSet rs;
        rs = stmt.executeQuery(s);
       return rs;
    }

    public static void displayQueryResults(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int numOfCols = rsmd.getColumnCount();
        while(rs.next())
        {
            for(int i = 1; i < numOfCols + 1; i++)
            {
                System.out.print((rsmd.getColumnLabel(i) + ": " + rs.getString(i) + " "));
            }
            System.out.println();
        }
    }

    public static ArrayList<Result> getQueryResults(ResultSet rs) throws SQLException
    {
        ArrayList<Result> results = new ArrayList<Result>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int numOfCols = rsmd.getColumnCount();
        while(rs.next())
        {
            for(int i = 1; i < numOfCols + 1; i++)
            {
                results.add(new Result(rsmd.getColumnLabel(i), rs.getString(i)));
            }
        }

        return results;
    }
}
