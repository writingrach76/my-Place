import java.sql.*;

public class myPlaceMain {
    //used to connect to the database being used
    private static Connection conn;
    private static Statement stmt;

    public static void main(String[] args){
        doConsoleTest();
    }

    /*
    * Tests database interactivity with Java program via console
     */
    private static void doConsoleTest()
    {
        Scanner in = new Scanner(System.in);
        String input = in.getLine();
        while(!input.equals("exit"))
        {
            if(input.equals("signup"))
            {
                signUpUser();
            }
            else if(input.equals("login"))
            {
                System.out.print("Username: ");
                String u = in.getLine();
                loginUser();
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
            input = in.getLine();
        }
    }

    /*
    * Creates a post within the database and on the website
     */
    private static void createPost()
    {

    }


    /*
    * Signs up a new user with the site, creating their information within the database.
     */
    private static void signUpUser()
    {

    }

    /*
    * Logs in a user that has already set up an account
     */
    private static void loginUser(String username, String password)
    {

    }

    /*
    * Queries database given input of a SQL query as a string that will be created before it is called,
    * allowing the database connection to only be created exactly when needed
     */
    private static void queryDatabase( String sql) throws SQLException
    {
        establishConnectionToDatabase();
        stmt = conn.createStatement();

        ResultSet rs = executeQueryStatement(sql);
        displayQueryResults(rs);

        conn.close(); //closes connection to the database
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
}
