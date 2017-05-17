package hibernateTest;

import com.dxfjyygy.domain.News;
import hibernateTest.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by longjinwen on 17/05/2017.
 */
public class NewsManager {
    /**
     * 测试二级缓存
     * 配置了<property name="hibernate.cache.use_second_level_cache">true</property>
     * 的情况下
     */
    @Test
    public void test1(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        News news = (News) session.load(News.class,1);
        System.out.println("sesion没关时取回的值:"+news.getFullContent());
        tx.commit();
        System.out.println(session.isOpen());
        //另开一个session
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx2 = session1.beginTransaction();
        News news1 = (News) session1.load(News.class,2); // 此时会打印sql
        tx2.commit();
        System.out.println("sesion关闭后取回的值:"+news1.getTitle());
    }


    /**
     * 测试二级缓存
     * 配置了<property name="hibernate.cache.use_second_level_cache">true</property>
     * 的情况下
     */
    @Test
    public void test2(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Criteria c =  session.createCriteria(News.class);
        c.setCacheable(true);
        List<News> newsList = c.list();
        System.out.println("第一次取:"+newsList.size());
        tx.commit();
        System.out.println(session.isOpen());
        //另开一个session
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx2 = session1.beginTransaction();
        c =  session1.createCriteria(News.class);
        c.setCacheable(true);
        // 第二次读取
        System.out.println("第二次读取:");
        List<News> newsList2 = c.list(); //仍然打印
        System.out.println(newsList2.size());
        tx2.commit();
    }


    /**
     * 测试二级缓存
     * 配置了<property name="hibernate.cache.use_second_level_cache">true</property>
     * 的情况下
     */
    @Test
    public void test3(){

            Session session = null;
            try {
                /**
                 * 此时会发出一条sql取出所有的学生信息
                 */
                session = HibernateUtil.getSessionFactory().openSession();
                List<News> ls = session.createQuery("from News")
                        .setCacheable(true)  //开启查询缓存,查询缓存也是sessionFactory级别的缓存
                        .setFirstResult(0).setMaxResults(50).list();
                Iterator<News> newsIterator = ls.iterator();
                for(;newsIterator.hasNext();) {
                    News news = newsIterator.next();
                    System.out.println(news.getTitle());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HibernateUtil.closeSession();
            }
            try {
                /**
                 * 此时会发出一条sql取出所有的学生信息
                 */
                session = HibernateUtil.getSessionFactory().openSession();
                List<News> ls = session.createQuery("from News")
                        .setCacheable(true)  //开启查询缓存,查询缓存也是sessionFactory级别的缓存
                        .setFirstResult(0).setMaxResults(50).list();
                Iterator<News> newsIterator = ls.iterator();
                for(;newsIterator.hasNext();) {
                    News news = newsIterator.next();
                    System.out.println(news.getTitle());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                HibernateUtil.closeSession();
            }

    }



    /**
     * 测试二级缓存
     * 配置了<property name="hibernate.cache.use_second_level_cache">false</property>
     * 的情况下
     */
    @Test
    public void test4(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(News.class);
        c.setCacheable(true);//这句必须要有
        System.out.println("第一次读取");
        List<News> newsList = c.list();
        Iterator<News> iterator = newsList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
        //System.out.println(newsList.size());
        HibernateUtil.closeSession();

        s =HibernateUtil.getSessionFactory().openSession();
        c = s.createCriteria(News.class);
        c.setCacheable(true);//这句必须要有
        System.out.println("第二次读取");
        newsList = c.list();
        Iterator<News> iterator2 = newsList.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next().getTitle()); // 此时就没有发N条sql 出来
        }
        HibernateUtil.closeSession();
    }
}
