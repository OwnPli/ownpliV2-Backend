package db.project.ownpliv2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String memberId;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column
    private int age;

    @Column
    private int sex;

    @Column
    private String profileImage;

    @Builder
    public Member(String email, String password, String nickname, String profileImage) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public Member update(String password, String nickname, String profileImage) {
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;

        return this;
    }
}
