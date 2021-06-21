package me.kobeshow.main;

import me.kobeshow.jpql.Member;
import me.kobeshow.jpql.MemberDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ConditionMain {
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

            String query = "select " +
                    "case when m.age <= 10 then '학생요금'" +
                    "     when m.age >= 60 then '경로요금'" +
                    "     else '일반요금' " +
                    "end " +
                    " from Member m";
            String queryCoalesce = "select coalesce(m.username, '이름 없는 회원') as username from Member m";
            String queryNullIf = "select nullif(m.username, '관리자') as username from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for(String s : result) {
                System.out.println("s = " + s);
            }
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
