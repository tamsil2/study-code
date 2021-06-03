package me.kobeshow.main;

import me.kobeshow.jpql.Address;
import me.kobeshow.jpql.Member;
import me.kobeshow.jpql.MemberDTO;
import me.kobeshow.jpql.Team;

import javax.persistence.*;
import java.util.List;

public class ProjectionMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            em.createQuery("select new me.kobeshow.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
