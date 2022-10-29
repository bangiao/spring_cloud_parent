package com.zhazha.springcloud.entity;

import com.zhazha.springcloud.event.UserEvent;
import lombok.*;
import org.springframework.data.domain.DomainEvents;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(name = "uc_user_username", columnNames = {"username"})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "username", nullable = false)
	@NonNull
	private String username;

	@Column(name = "password")
	@NonNull
	private String password;

	/**
	 * 这里的返回值都会被发布出去
	 *
	 * @return
	 */
	@DomainEvents
	Collection<UserEvent> domainEvents() {
		// 这里返回的都是事件
		// 跟 EventListener 注解文档说的一样, 如果该注解标注的函数有返回值, 那么返回的对象将会成为新的事件
		// 再次发送出去
		return List.of(new UserEvent(this.id));
	}

}