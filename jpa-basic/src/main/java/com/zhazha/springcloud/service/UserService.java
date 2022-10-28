package com.zhazha.springcloud.service;

import com.zhazha.springcloud.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
	@Resource
	private UserRepository userRepository;
	
//	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//	public void event(UserSaveEvent event) {
//		System.err.println(userRepository.getReferenceById(event.getId()));
//	}
	
}
