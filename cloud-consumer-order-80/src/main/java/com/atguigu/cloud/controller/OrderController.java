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
	
	//消费者只关系微服务名称，他不管你是什么ip什么端口，，，
	//直接这么写是不行的，因为CLOUD-PAYMENT-SERVICE里也许会有多台提供相同服务的机子（究竟是调用那一台？）
	//如果不指明调用哪一台，Eureka会懵逼的，会抛出一个UnknownHostException异常，做法就是告诉restTemplate开启负载均衡机制
	//直接在注入restTemplate类上添加注解（默认是轮询机制）
	public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
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
