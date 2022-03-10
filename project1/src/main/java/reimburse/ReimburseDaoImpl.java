package reimburse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReimburseDaoImpl implements ReimburseDao{

    @Override
    public void updateReimburseStatus(int id) {

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Reimburse reimburse = session.createQuery("from Reimburse where id='"+id+"'", Reimburse.class).getSingleResult();

        reimburse.setStatus("approved");

        session.update(reimburse);

        session.getTransaction().commit();

        session.close();

    }

    @Override
    public void deleteReimburse(int id) {
        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Reimburse reimburse = session.createQuery("from Reimburse where id='"+id+"'", Reimburse.class).getSingleResult();

        session.delete(reimburse);

        session.getTransaction().commit();

        session.close();

    }

    @Override
    public List<Reimburse> getReimbursesByPending() {

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        List <Reimburse> list = session.createQuery("from Reimburse where status='pending'", Reimburse.class).list();

        return list;
    }

    @Override
    public List<Reimburse> getReimbursesByApproved() {

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        List <Reimburse> list = session.createQuery("from Reimburse where status='approved'", Reimburse.class).list();

        return list;
    }
}
