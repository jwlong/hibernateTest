package hibernateTest;

import hibernateTest.domain.Name;
import hibernateTest.domain.Person;
import hibernateTest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by longjinwen on 2017/3/4.
 */
public class PersonManager {
    public static void main(String[] args) {
      //  Session session = HibernateUtil.currentSession();
        Configuration cfg = new Configuration().configure();
        SessionFactory sf  = cfg.buildSessionFactory();
        Session session = HibernateUtil.currentSession();
       // Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Person person = new Person();
        person.setAge(12);
        Map<Name,Integer> nickMap = new HashMap<Name, Integer>();
        Name name = new Name();
        name.setFirst("jin");
        name.setLast("wen");
        name.setOwner(person);
        nickMap.put(name,1);

        person.setNickPower(nickMap);
        session.save(person);
                tx.commit();
                session.close();
    }
}
