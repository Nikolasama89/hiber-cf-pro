package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;


public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
//        Teacher teacher = new Teacher(null, "Alice", "W.", null);
//        Course java = new Course(null, "Java", null);
//        teacher.addCourse(java);

        em.getTransaction().begin();

//        Teacher alice = em.find(Teacher.class, 1L);
//        Course databases = new Course(null, "Databases", null);
//        alice.setLastname("Wonderland");
//        alice.addCourse(databases);
//
//        em.persist(databases);
//        em.merge(alice);

//        em.persist(teacher);
//        em.persist(java);

        // SELECT all teachers
//        String sql = "SELECT t FROM Teacher t";
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        List<Teacher> teachers = query.getResultList();
//        teachers.forEach(System.out::println);

//        String sql = "SELECT c FROM Course c";
//        TypedQuery<Course> query = em.createQuery(sql, Course.class);
//        List<Course> courses = query.getResultList();
//        courses.forEach(System.out::println);

        // Select courses που διδασκει η Αννα
//        String sql = "SELECT c FROM Course c WHERE c.teacher.firstname = :firstname";
//        TypedQuery<Course> query = em.createQuery(sql, Course.class);
//        query.setParameter("firstname", "Μάρκος");
//        List<Course> courses = query.getResultList();

        // same with the above
//        List<Course> courses = em.createQuery(sql, Course.class)
//                .setParameter("firstname", "Άννα")
//                .getResultList();
//        courses.forEach(System.out::println);

        // SELECT teachers και courses που διδασκουν οι teachers
//        String sql = "SELECT t, c.title FROM Teacher t JOIN t.courses c";   // INNER JOIN
//        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
//        List<Object[]> results = query.getResultList();
//        for (Object[] result : results) {
//            Teacher teacher = (Teacher) result[0];
//            String courseTitle = (String) result[1];
//            System.out.println("Teacher: " + teacher.getLastname() + " , Course: " + courseTitle);
//        }
        /*
         * Criteria API
         */

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);   // TI ΕΠΙΣΤΡΕΦΕΙ
//        Root<Teacher> teacher = query.from(Teacher.class);              // FROM
//
//        query.select(teacher);      // θα πρεπει να ειναι τοο ιδιο με το CriteriaQuery
//        List<Teacher> teachers = em.createQuery(query).getResultList();
//        teachers.forEach(System.out::println);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<String> query = cb.createQuery(String.class);
//        Root<Course> course = query.from(Course.class);
//        query.select(course.get("title"));
//        List<String> titles = em.createQuery(query).getResultList();
//        titles.forEach(System.out::println);

        // Teachers με επώνυμο 'Μόσχος'
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
//        Root<Teacher> teacher = query.from(Teacher.class);
//        ParameterExpression<String> lastname  = cb.parameter(String.class);
//        query.select(teacher).where(cb.equal(teacher.get("lastname"), lastname));
//        List<Teacher> teachers = em.createQuery(query).setParameter(lastname, "Μόσχος").getResultList();
//        teachers.forEach(System.out::println);

        // Τα courses (title) και τους teachers (firstname, lastname)
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Course> course = query.from(Course.class);
        Join<Course, Teacher> teacher = course.join("teacher");
        query.multiselect(course.get("title"), teacher.get("lastname"), teacher.get("firstname"));
        List<Object[]> coursesTeachers = em.createQuery(query).getResultList();

        for (Object[] result : coursesTeachers) {
            String title = (String) result[0];
            String lastname = (String) result[1];
            String firstname = (String) result[2];
            System.out.println(title + ", " + lastname + ", " + firstname);
        }

        em.getTransaction().commit();

        // ΚΛΕΙΝΟΥΜΕ ΓΤ ΕΙΝΑΙ RESOURCES
        em.close();
        emf.close();
    }
}
