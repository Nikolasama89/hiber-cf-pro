package gr.aueb.cf;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
//        Teacher teacher = new Teacher(null, "Alice", "W.", null);
//        Course java = new Course(null, "Java", null);
//        teacher.addCourse(java);

        em.getTransaction().begin();

        Course course = em.find(Course.class, 2L);
        em.remove(course);
//        Teacher alice = em.find(Teacher.class, 1L);
//        Course databases = new Course(null, "Databases", null);
//        alice.setLastname("Wonderland");
//        alice.addCourse(databases);
//
//        em.persist(databases);
//        em.merge(alice);

//        em.persist(teacher);
//        em.persist(java);

        em.getTransaction().commit();

        // ΚΛΕΙΝΟΥΜΕ ΓΤ ΕΙΝΑΙ RESOURCES
        em.close();
        emf.close();
    }
}
