package com.zhazha.formlogin.config;

import com.zhazha.formlogin.entity.Users;
import com.zhazha.formlogin.repository.AuthoritiesRepository;
import com.zhazha.formlogin.repository.UsersRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Resource
	private UsersRepository usersRepository;
	
	@Resource
	private AuthoritiesRepository authoritiesRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		usersRepository.findOne((root, query, criteriaBuilder) -> {
			ArrayList<Predicate> list = new ArrayList<>();
			list.add(criteriaBuilder.equal(root.get("username"), username));
			return query.where(list.toArray(new Predicate[0])).getRestriction();
		});
		Users users = new Users();
		users.setUsername(username);
		Example<Users> example = Example.of(users);
		Optional<Users> optUser = usersRepository.findOne(example);
		if (optUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return new User(optUser.get().getUsername(), optUser.get().getPassword(), optUser.get().getEnabled(), true, true, true, Collections.emptyList());
	}
}
