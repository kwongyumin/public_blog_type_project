package com.example.blog.model.user;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FIXME : sq 가 아닌 , 고유 id 값이 필요할듯?!...
    @Column
    private String guestId;

    @Column
    private String guestName;

    @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setGuest(this));
    }

}
