package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장 타입을 포함했다는 어노테이션 맵핑?
    private Address address;

    @OneToMany(mappedBy = "member") // 나는 연관관계의 주인이 아니라 map 된거야!
    private List<Order> orders = new ArrayList<>(); //초기화 문제에 대해 걱정할 일이 없음, hibernate에 대해 어쩌구...
}
