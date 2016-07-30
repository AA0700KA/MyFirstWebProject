package finalproject.entity.users;

/**
 * User discription in abonent.schema.users
 *
 * @author Andrew
 * @version 1.0
 */

public abstract class User {

    /**
     * Column id in table users
     */

    private int id;

    /**
     * Column login in table users main characteristics in singin
     */

    private String login;

    /**
     * Column password in table users main characteristic in singin
     */

    private String password;

    /**
     * Column name in table users
     */

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
