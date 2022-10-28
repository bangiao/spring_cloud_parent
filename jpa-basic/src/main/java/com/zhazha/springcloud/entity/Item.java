package com.zhazha.springcloud.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	@ToString.Exclude
	private Cart cart;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Item item = (Item) o;
		return id != null && Objects.equals(id, item.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}