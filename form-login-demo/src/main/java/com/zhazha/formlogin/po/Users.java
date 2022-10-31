package com.zhazha.formlogin.po;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2022-10-27 23:08:09
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {
    private static final long serialVersionUID = -12974772985897054L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    
    private String password;
    
    private Boolean enabled;

    @OneToMany(mappedBy = "users")
    private Set<Authorities> authorities = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Users users = (Users) o;
        return username != null && Objects.equals(username, users.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

