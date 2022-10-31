package com.zhazha.formlogin.repository;

import com.zhazha.formlogin.dto.UsersDTO;
import com.zhazha.formlogin.po.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsersRepositoryTest {
	
	@Resource
	private UsersRepository usersRepository;
	
	@Test
	public void test04() throws Exception {
		Optional<UsersDTO> user = usersRepository.findUsersDTOByUsername("user", UsersDTO.class);
		System.err.println(user);
	}
	
	@Test
	public void test03() throws Exception {
		Optional<Users> optionalUsers = usersRepository.findOne((root, query, criteriaBuilder) -> {
			ArrayList<Predicate> predicates = new ArrayList<>();
			predicates.add(criteriaBuilder.equal(root.get("username"), "user"));
			return query.where(predicates.toArray(new Predicate[0])).getRestriction();
		});
		System.err.println(optionalUsers.orElseGet(Users::new));
	}
	
	@Test
	public void test02() throws Exception {
		Users user = usersRepository.findByEnabledIsTrueAndUsername("user");
		System.out.println(user);
	}
	
	/**
	 * 介绍ExampleMatcher怎么用?
	 *
	 * @throws Exception
	 */
	@Test
	public void test01() throws Exception {
		Users users = new Users();
		users.setUsername("use");
		ExampleMatcher matching = ExampleMatcher.matching()
				.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("enabled");
		Example<Users> example = Example.of(users, matching);
		List<Users> usersList = usersRepository.findAll(example);
		usersList.forEach(System.out::println);
	}

    @Test
    void testFindUsersByUsername02() {
		UsersDTO admin = usersRepository.findUsersByUsername02("admin");
		System.err.println(admin);
	}
}