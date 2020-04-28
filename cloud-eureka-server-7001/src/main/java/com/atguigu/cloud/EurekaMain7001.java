package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //标注这是一个Eureka服务器
public class EurekaMain7001 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaMain7001.class, args);
	}
}
