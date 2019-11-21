package com.xiangyang.zuul.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import io.netty.handler.codec.CodecException;



@RestController
public class TestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
//	@RequestMapping(value = "/v1/aaa", method = RequestMethod.GET)
	@GetMapping(value = "/v1/aaa")
	@SentinelResource(value = "/v1/aaa" ,fallback = "getS",exceptionsToIgnore = {CodecException.class})
	public String getConsumesAchievement(@RequestParam("type") String type) {
		if(type.equals("1")) {
			return "这个是正常返回";
		}
		if(type.equals("2")) {
			throw new CodecException("eeeeeeeeeeeeeeeeee");
		}
		throw new RuntimeException("出错了  ");
//		return "aaaa";
	}
	
	/**
	@SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：

value：资源名称，必需项（不能为空）
entryType：entry 类型，可选项（默认为 EntryType.OUT）
blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
返回值类型必须与原函数返回值类型一致；
方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
返回值类型必须与原函数返回值类型一致；
方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出
	 *
	 */
	
	public static String getS( String type) {
		LOGGER.info("这个是降级时候发生的错误：{}");
		return "服务发生了降级";
	}
	
	public static void main(String[] args) {
	    // 不断进行资源调用.
	    while (true) {
	        Entry entry = null;
	        try {
		    entry = SphU.entry("HelloWorld");
	            // 资源中的逻辑.
	            System.out.println("hello world");
		} catch (BlockException e1) {
		    System.out.println("blocked!");
		} finally {
		   if (entry != null) {
		       entry.exit();
		   }
		}
	    }
	}
}
