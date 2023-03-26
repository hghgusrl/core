package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
            기존에 작업한 내용으로 작업하게 되면 DIP를 위반한다.
            1. 객체를 생성하고 연결
            2. 실행
            이 2가지를 분리를 해야되는데
            MemberService memberService = new MemberServiceImpl();
            이 방법을 사용하게 되면 MemberServiceImpl이 객체를 생성하고 연결하고 실행을 시키기 때문에 책임이 너무 커지므로 DIP를 위반한다.

            그러므로 AppConfig를 사용해서 config에서 어떤 객체를 의존관계 주입을 할지 정해주고 serviceImpl에서는 실행을 하는데 집중하게 한다.
        */
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = "+ findMember.getName());

    }
}
