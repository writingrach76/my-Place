public class User {
    private boolean _loggedIn;
    private String _username;
    private String _name;

    public User(String username, String name)
    {
        _loggedIn = false;
        _username = username;
        _name = name;
    }

    public void setLogin(boolean login)
    {
        _loggedIn = login;
    }

    public boolean checkLogin()
    {
        return _loggedIn;
    }

    public String getUsername()
    {
        return _username;
    }

    public String getName()
    {
        return _name;
    }

}
