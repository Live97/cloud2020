package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@NoArgsConstructor
//通用的，先设置成T
public class CommonResult<T> {
	
	private Integer code;
	private String message;
	
	//返回T类型的数据，这里的T类型是未知的，待到调用的时候我们才会知道传入的是什么类型 例如 Payment Order...
	private T data;
	
	public CommonResult(Integer code,String message) {
		this(code,message,null);
	}
}
