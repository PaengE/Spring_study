package spring_study.hello_spring.domain;

import javax.persistence.*;

/** 회원 객체 */
@Entity
public class Member {

    // PK 지정
    // DB 가 알아서 생성해 주는 것을 IDENTITY 라 함
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
//    DB 에 컬럼명이 username 이면 여기서의 name 과 DB 의 username 컬럼과 매핑을 시켜줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
