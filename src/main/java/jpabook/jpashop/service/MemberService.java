package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // public 메소드가 기본적으로 transactional에 걸려 들어감
@RequiredArgsConstructor // final 있는 필드만 생성자를 만들어준다 이거 때문에 저기 어투와이어드 생성자주입 안 써도 되는겨
public class MemberService {
    private final MemberRepository memberRepository; //final 넣으면 변경 불가 하니까 써주자

    /**
    @Autowired //생성자 인셉션이 요즘 트렌드~ //생성자가 하나만 있을 경우 @Autowired 안 써줘도 된다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */


    /**
    * 회원가입
     **/
    @Transactional(readOnly = false) //false가 디폴트 값
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
