package reimburse;

public class ReimburseDaoFactory {
    private static ReimburseDao dao;

    private ReimburseDaoFactory(){}

    public static ReimburseDao getReimburseDao(){
        if(dao==null){
            dao = new ReimburseDaoImpl();
        }
        return dao;
    }
}
