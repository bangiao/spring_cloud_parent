package com.zhazha.springcloud.dao;

import com.zhazha.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface PaymentDao {
	
	@Insert("insert into payment(serial) values (#{serial})")
	Integer create(Payment payment);
	
	@Select("select * from payment p where p.id = #{id}")
	Payment getPaymentById(Long id);
	
}