package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
// 构建一个有参（所有的已经声明的字段）构造行数
@AllArgsConstructor
// 构建一个无参构造行数
@NoArgsConstructor
public class Payment implements Serializable {
	
	private Long id;
	private String serial;
}
