package user;

public class Role {

    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(){

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
