package com.zhazha.formlogin.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhazha.formlogin.po.QUsers;
import com.zhazha.formlogin.po.Users;
import com.zhazha.formlogin.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Resource
	private UsersRepository usersRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		usersRepository.findOne((root, query, criteriaBuilder) -> {
//			ArrayList<Predicate> list = new ArrayList<>();
//			list.add(criteriaBuilder.equal(root.get("username"), username));
//			return query.where(list.toArray(new Predicate[0])).getRestriction();
//		});
//		Users users = new Users();
//		users.setUsername(username);
//		Example<Users> example = Example.of(users);
//		Optional<Users> optUser = usersRepository.findOne(example);
//		if (optUser.isEmpty()) {
//			throw new UsernameNotFoundException(username);
//		}
		
		QUsers qUsers = QUsers.users;
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		Optional<Users> optUser = Optional.ofNullable(jpaQueryFactory.selectFrom(qUsers)
				.where(qUsers.username.eq(username))
				.fetchFirst());
		if (optUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return new User(optUser.get().getUsername(), optUser.get().getPassword(), optUser.get().getEnabled(), true, true, true, Collections.emptyList());
	}
}
