package board.member;

import lombok.*;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED) //리플렉션을 위해
public class MemberInfo {
    private Long id;
    private String username;
    private int age;
    private String interest;

    public static MemberInfo of(Member member) {
        return MemberInfo.builder()
                .id(member.getId())
                .username(member.getUsername())
                .age(member.getAge())
                .interest(member.getInterest())
                .build();
    }
}
