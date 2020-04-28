package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.CommonResult;
import com.atguigu.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
	
	public static final String PAYMENT_URL = "http://localhost:8001";
	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping("/consumer/payment/create")
	public CommonResult<Payment> create(Payment payment) {
		
		//用户只能发送get请求，然后来到这里之后就会转成发送post到真正干活的8001工程里面的controller
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}
	
	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}
	
	
}
