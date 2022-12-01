package modelos;


/**
 *
 * @author juanj
 */
public class Employees {

    private int id;
    private String lastname;
    private String firsttname;
    private String title;
    private String pass;
    private String usuario;

    public Employees(int id, String lastname, String firsttname, String title, String pass, String usuario) {
        this.id = id;
        this.lastname = lastname;
        this.firsttname = firsttname;
        this.title = title;
        this.pass = pass;
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirsttname() {
        return firsttname;
    }

    public void setFirsttname(String firsttname) {
        this.firsttname = firsttname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
