package manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManagerDaoImpl implements ManagerDao{
    @Override
    public Manager getManagerByUsername(String username) {

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        Manager manager = session.createQuery("from Manager where username='"+username+"'", Manager.class).getSingleResultOrNull(); //getSingleResult();

        return manager;
    }
}
