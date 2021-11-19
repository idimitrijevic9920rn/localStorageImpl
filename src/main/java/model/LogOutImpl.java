package model;

public class LogOutImpl {

    public LogOutImpl() {
    }

    public void logOut(){
        if(ToolManager.getInstance().isLogged()==true){
            ToolManager.getInstance().getPrivilegeLevel().clear();
            ToolManager.getInstance().setLogged(false);
            ToolManager.getInstance().setDepedentFolderValues(null);
            ToolManager.getInstance().setForbiddenFileTypes(null);
            ToolManager.getInstance().setAdmin(null);
            System.out.println("successfully logged out");
        }else{
            System.out.println("error: user is not logged on");
        }
    }

}
