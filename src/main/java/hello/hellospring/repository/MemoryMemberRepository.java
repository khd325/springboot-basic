package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //메모리에서 저장하기 위한 Map
    private static Map<Long,Member> store = new HashMap<>();

    //store에 저장될 key
    private static long sequence = 0l;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //반환값이 null일 경우를 대비해서 Optional로 감싼다.
        //null이면 빈 Optinal 객체를 반환한다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();

    }
}
