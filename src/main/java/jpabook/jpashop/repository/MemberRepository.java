package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈으로 등록
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext //엔티티 매니저를 스스로 주입해줌 (스프링 부트가) // @RequiredArgsConstructor 쓰먄 이거 안 씀
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
        // JPQL임 SQL과 차이 있음, from의 대상이 테이블이 아니라 엔티티라는 점이 jpql의 특징
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }

}
