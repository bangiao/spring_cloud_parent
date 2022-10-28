package com.zhazha.springcloud.listnener;

import com.zhazha.springcloud.event.UserEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserServiceEventPublisher {
	
	/**
	 * 再次证明 @DomainEvents 注解函数返回的是spring event事件
	 *
	 * @param event
	 */
	@EventListener(classes = {UserEvent.class})
	public void UserSaveAllEvent(UserEvent event) {
		System.err.println(event.getId());
	}
	
}
