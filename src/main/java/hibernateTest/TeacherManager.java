package hibernateTest;

import com.dxfjyygy.domain.Student;
import com.dxfjyygy.domain.Teacher;
import hibernateTest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longjinwen on 2017/3/3.
 */
public class TeacherManager {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf  = cfg.buildSessionFactory();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Teacher t = new Teacher();
        t.setAge(50);
        t.setName("john");
        List<Student> students = new ArrayList<Student>();
        Student s1 = new Student();
        s1.setName("学生1");
        s1.setAge(20);
        Student s2 = new Student();
        s2.setName("学生2");
        s2.setAge(20);
        students.add(s1);
        students.add(s2);
        t.setStudents(students);
        session.save(t);
        tx.commit();
        session.close();
    }
}
