package hibernateTest;

import com.dxfjyygy.domain.News;
import com.sun.org.apache.bcel.internal.generic.NEW;
import hibernateTest.domain.Name;
import hibernateTest.domain.Person;
import hibernateTest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

    /**
     * 测试Sesson1级缓存
     */
    @Test
    public void test1(){
        SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx  =  session1.beginTransaction();
        News news = new News();
        news.setContent("this is session1 about content");
        news.setFullContent("this is full  content aout the world the message");
        news.setTitle("Session first cache!!");
        session1.save(news);
        session1.save(news); //这里就不发sql ,hibernate 从session 中取回这里news 这个new 此时就是持久化状态

        session1.load(News.class,100);// 在配置文件那里没有配置是否支撑延迟加载,默认好像是load=false

//        News news1 = (News) obj;
//        news1.setTitle("load222");
//        news1.setContent("This is content!!");
//        session1.save(news1);
       // session1.get(News.class,2); 这个get就直接去数据库里把数据拿回来了。
//        //newByLoad.setFullContent("load 过来的，我要改");
//        newByLoad.setTitle("load过来的，我要改");
//        newByLoad.setContent("this is  ready to update ...");
//        session1.saveOrUpdate(newByLoad);
//        tx.commit();
//        System.out.println(session1.isOpen());
    }

    /**
     * 测试几种持久化的状态
     * 1,瞬时状态。
     */
    @Test
    public  void test2(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        News news = new News();//现在news就是瞬时状态
        news.setTitle("shunshi");
        news.setContent("this is transient");
        Session sess1 = sessionFactory.getCurrentSession(); // 这个需要在hibernate的配置文件里加上       <property name="current_session_context_class">thread</property>
        //或者<property name="current_session_context_class">jta</property> 时才能正常使用不然会报 No CurrentSessionContext configured的错误
        Transaction tx = sess1.beginTransaction();
        sess1.save(news);// news与session 关联了，此时news就是持久化对象了
        tx.commit();
        Session session2 = sessionFactory.getCurrentSession();
        Transaction tx2 = session2.beginTransaction();
        news.setTitle("the session2 and this is detac"); //此时的news是脱管状态
        session2.update(news);
        tx2.commit();
        //如果 此时再显式的去close session则会报错了
       // sess1.close();
        System.out.println(sess1.isOpen());
    }

    /**
     * 测试一些少见的session的方法
     * 如merge()
     * lock()
     * LockMode锁模式
     */
    @Test
    public void test3(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx = session1.beginTransaction();
        News news = new News();
        news.setTitle("test 测试 lock");
        news.setContent("lock 内容");
        session1.save(news);
        System.out.println(news.getFullContent());
        news.setTitle("update tile after sesion1");
        session1.update(news);//其实 这个news仍然是持久化状态,脱管是指对象关联的session关闭后的状态。
        tx.commit();
        Session session2 = sessionFactory.getCurrentSession();
        Transaction tx2 = session2.beginTransaction();
      //  news.setTitle("update title again!");
       // session2.update(news);
        session2.merge(news);
        System.out.println(session2.getCurrentLockMode(news));
        tx2.commit();

    }

    /**
     * 再测试一下1，2级缓存
     * 1级缓存的iterate()
     */
    @Test
    public void test4(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String title = (String) session.createQuery("select  n.name from Teacher n where n.id=3 ").iterate().next();
        System.out.println(title);
        String title2 = (String) session.createQuery("select  n.name from Teacher n where n.id=3 ").iterate().next();
        //仍然发了sql 语句，表示仍然去与数据库去交互了 因为 iterate()查询是普通属性，不会放在一级缓存里
        System.out.println(title2);
        tx.commit();
    }
    @Test
    public void test5(){
        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        //设置detached 状态下的load方式
        News news = new News();
        news.setTitle("这是个中文测试Session 1级缓存");
        news.setContent("这个是测试的内容内容内容内容内容内容");
        session.save(news);
        tx.commit();
        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx2 = session2.beginTransaction();
        News news1 = (News) session2.load(News.class,1);
        System.out.println(news1.getTitle());
        tx2.commit();
    }

    /**
     * Session 一级缓存Load
     */
    @Test
    public void test6(){
        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        //设置detached 状态下的load方式
        News news = new News();
        news.setTitle("这是个中文测试Session 1级缓存");
        news.setContent("这个是测试的内容内容内容内容内容内容");
        session.save(news);
        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        News news1 = (News) session2.load(News.class,1);
        System.out.println(news1.getTitle());
        session.clear(); //清除一级缓存
        News news2 = (News) session.load(News.class,1);
        System.out.println(news2.getFullContent());
        System.out.println(session == session2); //true, session = session2
        tx.commit();
    }

    /**
     *list
     * iterate()正常使用场景
     */
    @Test
    public void test7(){
        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        List<News> listNews =  session.createQuery("from  News").setFirstResult(0).setMaxResults(10).list();
        // 先list 再iterator
        //此时只会发出一条sql
        Iterator<News> newsIterator =  listNews.iterator();
        for(;newsIterator.hasNext();){
            System.out.println(newsIterator.next().getFullContent());
        }
        tx.commit();
    }

    @Test
    public void test8(){
        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx  = session.beginTransaction();
        //此时只会发出一条sql
        //只是查询对象的ID
        Iterator<News> newsIterator =  session.createQuery("from  News").setFirstResult(0).setMaxResults(10).iterate();
        // 开始 iterate
        for(;newsIterator.hasNext();){
            System.out.println(newsIterator.next().getFullContent());
        }
        tx.commit();
    }

}
