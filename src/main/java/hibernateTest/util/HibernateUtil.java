package hibernateTest.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by longjinwen on 2017/3/3.
 */
public class HibernateUtil {
    private static HibernateUtil instance;
    private static SessionFactory sessionFactory;
    private HibernateUtil(){

    }

    public static HibernateUtil getInstance(){
        if(instance == null){
            instance = new HibernateUtil();
        }
        return instance;
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory =  new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
    //public static
    public static Session currentSession(){
        SessionFactory currentFactory = new Configuration().configure().buildSessionFactory();
        return currentFactory.getCurrentSession();
    }
    public static void closeSession(){
        if(currentSession() != null){
            currentSession().close();
        }
    }
    public static void closeSession(Session session){

    }
    public static void main(String[] args) {
        SessionFactory currentFactory = getSessionFactory();
        SessionFactory s2 = new Configuration().configure().buildSessionFactory();
        System.out.println(getSessionFactory() == s2); //false
        System.out.println(currentFactory == getSessionFactory()); //true 这个就是单例的好处了
    }
}
