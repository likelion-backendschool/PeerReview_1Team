package com.example.ebook.domain.member.entities;

import com.example.ebook.domain.post.entities.Post;
import com.example.ebook.domain.product.entities.Product;
import com.example.ebook.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@SuperBuilder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    @Column()
    private String password;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    @Column()
    @ColumnDefault("1")
    private Short authLevel;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Product> products;

    public void changePassword(String password){
        this.password = password;
    }
    public void changeEmail(String email) {this.email = email; }
    public void changeNickname(String nickname) {this.nickname = nickname; }
}
