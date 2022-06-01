package me.kobeshow.main;

import me.kobeshow.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExampleMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 엔티티를 생성한 상태(비영속)
            Member member1 = new Member();
            member1.setId(1L);
            member1.setUsername("hong");

            Member member2 = new Member();
            member2.setId(2L);
            member2.setUsername("hong2");

            em.persist(member1);
            em.persist(member2);

            tx.commit();

            // 영속 엔티티 조회
            Member findMember1 = em.find(Member.class, 1L);

            // 영속 엔티티 데이터 수정
            findMember1.setUsername("hong2");

            em.merge(findMember1); // 이렇게 코드를 날리지 않아도 된다

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
