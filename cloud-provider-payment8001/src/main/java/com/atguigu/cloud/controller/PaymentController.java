package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.CommonResult;
import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j //打印点日志
public class PaymentController {
	
	@Resource
	PaymentServiceImpl paymentService;
	
	@Value("${server.port}")
	private String serverPort;
	
	//很重要
	@Resource
	private DiscoveryClient discoveryClient;
	
	@PostMapping(value = "payment/create")
	public CommonResult create(@RequestBody Payment payment) {
		
		int result = paymentService.create(payment);
		log.info("*****插入结果：" + result);
		return result > 0 ? new CommonResult(200, "插入成功,serverPort：" + serverPort,result) :
					   new CommonResult(444,"插入数据库失败");
	}
	
	@GetMapping(value = "payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		
		Payment payment = paymentService.getPaymentById(id);
		log.info("*****查询结果：" + payment + "haha");
		
		if (StringUtils.isEmpty(payment)) {
			return new CommonResult(444, "数据库中不存在记录："+id,null);
		} else {
			return new CommonResult(200, "查询成功,serverPort: " + serverPort,payment);
		}
	}
	
	@GetMapping(value = "/payment/discovery")
	public Object discovery(){
		
		//获取当前Eureka上注册的所有服务
		List<String> services = discoveryClient.getServices();
		for (String service : services) {
			log.info("************注册的服务有：" + service);
		}
		
		//获取指定服务下的所有信息
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
		for (ServiceInstance instance : instances) {
			log.info(instance.getServiceId() + "\t" + instance.getInstanceId() + "\t" + instance.getUri() + "\t" + instance.getMetadata());
		}
		return this.discoveryClient;
	}
}
