package me.ztiany.test.autowiring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.6.12 23:21
 */
@Configuration
// 默认规则，以配置类所在的包作为基础包（base package）来扫描组件
@ComponentScan(basePackages = "me.ztiany.test.autowiring")
//@ComponentScan(basePackageClasses = Service.class)// 使用basePackageClasses 指定扫描的组件
public class AutowiringConfig {
}
