package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.CommonResult;
import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j //打印点日志
public class PaymentController {
	
	@Resource
	PaymentServiceImpl paymentService;
	
	@PostMapping(value = "/payment/create")
	public CommonResult create(@RequestBody Payment payment) {
		
		int result = paymentService.create(payment);
		log.info("*****插入结果：" + result);
		return result > 0 ? new CommonResult(200, "插入成功",result) :
					   new CommonResult(444,"插入数据库失败");
	}
	
	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****查询结果：" + payment + "haha");
		
		if (StringUtils.isEmpty(payment)) {
			return new CommonResult(444, "数据库中不存在记录："+id,null);
		} else {
			return new CommonResult(200, "查询成功",payment);
		}
	}
}
