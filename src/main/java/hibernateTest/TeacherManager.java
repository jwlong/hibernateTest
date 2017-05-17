package hibernateTest;

import com.dxfjyygy.domain.Student;
import com.dxfjyygy.domain.Teacher;
import hibernateTest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longjinwen on 2017/3/3.
 */
public class TeacherManager {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        session.save(t);// Teacher 设置与Student的级联关系
        t.setAge(12);
        tx.commit();
    }

    @Test
    public void deleteTeacher(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = new Teacher();
        Student student = new Student();
        teacher.setAge(50);
        student.setAge(11);
        student.setName("111");
        session.save(student);
        tx.commit();
    }
}
