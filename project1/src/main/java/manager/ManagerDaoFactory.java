package manager;



public class ManagerDaoFactory {
    private static ManagerDao dao;

    private ManagerDaoFactory() {
    }

    public static ManagerDao getManagerDao(){
        if(dao==null){
            dao = new ManagerDaoImpl();
        }
        return dao;
    }
}
