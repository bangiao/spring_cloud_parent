package com.zhazha.formlogin.po;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * (Authorities)实体类
 *
 * @author makejava
 * @since 2022-10-27 23:59:15
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
	private static final long serialVersionUID = -56878014697011982L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String username;
	
	private String authority;

	@MapsId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	@ToString.Exclude
	private Users users;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Authorities that = (Authorities) o;
		return username != null && Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

