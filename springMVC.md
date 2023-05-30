# 1.配置案例

`springmvc.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.bjpowernode"/>
    
    <!--    不使用注解-->
<!--    处理器映射器-->
<!--    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>-->
<!--    处理器适配器-->
<!--    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>-->
    
<!--    开启Springmvc注解-->
    <mvc:annotation-driven />

    <!--    jsp的方式-->
    <!-- <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>-->
    
    
     <!-- 配置Thymeleaf视图解析器 -->
    <bean id="viewResolver"
          class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean
                            class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!-- 视图前缀 -->
                        <property name="prefix" value="/WEB-INF/templates/"/>
                        <!-- 视图后缀 -->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML5"/>
                        <property name="characterEncoding" value="UTF-8"/>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>
    <!-- 默认跳转到index.html页面 -->
    <mvc:view-controller path="/" view-name="index"/>
    <!--配置默认的servlet处理静态资源;
        1.若不配置<mvc:default-servlet-handler/>,
        当前工程的web.xml中,DispatcherServlet的url-pattern是 / ;
        tomcat中的DefaultServlet的url也是 / ,
        浏览器的请求会都交给DispatcherServlet进行处理,对于静态资源,前端控制器处理不了;
        2.如果只配置这个,会全部交给默认的tomcat的DefaultServlet处理;
        3. <mvc:annotation-driven/>两个都配置,首先交给DispatcherServlet处理,如果处理不了,
        会让DefaultServlet处理
    -->
    <mvc:default-servlet-handler/>
    
<!--    将安全目录(WEB-INF)下的静态资源 不可以和webapp下的文件夹重名-->
    <mvc:resources mapping="/static/**" location="/WEB-INF/static/"/>
    
    
    <!--    配置文件上传的解析器-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">

    </bean>
    
        <!--    拦截器-->
        <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/"/>
            <mvc:exclude-mapping path="/login"/>
            <mvc:exclude-mapping path="/loginTwo"/>
<!--            <mvc:exclude-mapping path="/static/**"/>-->
            <bean class="com.bjpowernode.interceptor.LoginInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

</beans>
```



`web.xml`

```xml
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
 <servlet-mapping>
     <servlet-name>springmvc</servlet-name>
     <!--<url-pattern>*.do</url-pattern>-->
     <!--    匹配所有的请求,但是不包括 .jsp的请求-->
     <url-pattern>/</url-pattern> 
     <!--    匹配所有的请求路径-->
    <!-- <url-pattern>/*</url-pattern> -->
 </servlet-mapping>

<!--编码过滤器
    注意:编码过滤器一定要设置在其他过滤器之前
-->
<filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>foreEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

<!--    处理put.delete请求-->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```





# 2.请求参数

## 1.占位符

```html
<a th:href="@{/testRest/1/admin}">测试路径中的占位符-->/testRest</a><br>
```



```java
@RequestMapping("/testRest/{id}/{username}")
public String testRest(@PathVariable("id") String id, @PathVariable("username")
String username){
System.out.println("id:"+id+",username:"+username);
return "success";
}
//最终输出的内容为-->id:1,username:admin

```



## 2.设置和请求参数同名的形参

> 在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在 DispatcherServlet中就会将请求参数赋值给相应的形参

```html
<a th:href="@{/testParam(username='admin',password=123456)}">测试获取请求参数--
>/testParam</a><br>
```



```java
@RequestMapping("/testParam")
public String testParam(String username, String password){
System.out.println("username:"+username+",password:"+password);
return "success";
}
```



## 3.@RequestParam

`value`

```
如果请求参数和控制器方法形参不一致,可以使用@RequestParam("请求参数名称")获取
```



`required`

```
设置是否必须传输此请求参数，默认值为true

若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置
defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；
若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
```



`defaultValue`

```
不管required属性值为true或false，
当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
例如:defaultValue="admin",如果前端传参了,使用传递的参数,如果没有传,使用admin
```



### @RequestHeader

> 获取请求体里面的信息

```java
@RequestMapping("login")
public String login(@RequestParam(required = false) String username,
                    String pwd, Model model,
                    @RequestHeader String referer,
                    //获取cookie里面的信息
                    @CookieValue(value = "JSESSIONID", required = false) String JSESSIONID
        , HttpServletRequest request) {
    System.out.println(username + "=====" + pwd);
    System.out.println("referer===" + referer);
    System.out.println("JSESSIONID===" + JSESSIONID);
    model.addAttribute("msg", "登陆成功!");
    request.getSession().setAttribute("loginUser", username);
    return "success";
}
```



## 4.对象接收参数

```
可以在控制器方法的形参位置设置一个实体类类型的形参，此时若浏览器传输的请求参数的参数名和实
体类中的属性名一致，那么请求参数就会为此属性赋值
```



`@RequestBody`

```
代表接收前端传来的json格式的字符串,并转成java对象;

不能和get请求一起使用,因为get请求不支持json格式的传参

该注解有个参数required,默认为true 意思是对象中的属性,在前端提交参数时,必须要有一个
```



## 5.map集合接收参数

`html`

```html
<!--    map接收-->
    <form th:action="@{/loginMap}" method="post" >
        用户名:<input type="text" name="username" /><br>
        密码:<input type="password" name="pwd" /><br>
        生日:<input type="date" name="birth" ><br>
        <input type="submit" value="提交" />
    </form>
```



`java`

```java
//注意加上@RequestParam
@RequestMapping("loginMap")
public String loginMap(@RequestParam Map<String, Objects> map, Model model) {
    System.out.println(map);
    model.addAttribute("msg", "登陆成功!");
    return "success";
}
```



## 6.数组接收参数

`html`

```html
<!--    数组接收-->
    <form th:action="@{/loginArray}" method="post" >
        <table>
            <tr>
                <td><input type="checkbox" name="ids" value="1"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ids" value="2"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="ids" value="3"></td>
            </tr>
        </table>
        <input type="submit" value="数组接收参数" />
    </form>
```



`java`

```java
@RequestMapping("loginArray")
public String loginArray(Integer[] ids) {
    System.out.println(Arrays.toString(ids));
    return "success";
}
```



`list集合同数组`



## 7.关于时间参数

```
	//规范前端向后端传的格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //后端向前端传的数据,依赖 jackson
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;
```



# 3.过滤器

## 1.字符编码过滤器

```xml
<!--编码过滤器
    注意:编码过滤器一定要设置在其他过滤器之前
-->
<filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    <!--编码过滤器
   这个有可能造成前端页面乱码
-->
        <init-param>
            <param-name>foreEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>


```



## 2.Restful风格过滤器

```xml
<!--    处理put.delete请求-->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```



# 4.拦截器

`java`

```java
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 只要入过滤器 必须然要执行的第一方法
     * @param request 请求对象
     * @param response 响应对象
     * @param handler  要请求的Controller对象
     * @return  true 放行  false拦截
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            return true;
        }
        //request.setAttribute("msg","请先登录!");
        //request.getRequestDispatcher("index").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}

```



`springmvc.xml`

```xml
<!--    配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
<!--            拦截路径, /**代表拦截所有  /*代表拦截一级目录,如子包和里面的类-->
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/login"/>
            <mvc:exclude-mapping path="/"/>
            <mvc:exclude-mapping path="/static/**"/>
            <bean class="com.bjpowernode.interceptor.LoginInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

```



# 5.ssm使用swagger测试

## 1.工具类

```java
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("cn.exrick.controller"))
                //扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ssm-single Api Documentation")
                .description("ssm-single项目后台API接口文档")
                .termsOfServiceUrl("https://www.baidu.com/")
                .license("85766@qq.com")
                .version("1.0.0")
                .build();
    }
}
```



## 2.springmvc.xml

```xml
<!--    加载swagger-->
    <bean class="com.bjpowernode.crm.commons.utils.Swagger2Config"/>
    <mvc:resources mapping="/swagger/**" location="/swagger/"/>
```



## 3.pom.xml

```xml
<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
```



## 4.静态资源

```
将关于swagger的静态资源放在webapp下的swagger文件夹下;
将其中的index.html(第40行) 修改成url = "/crm/v2/api-docs"; 其中/crm为tomcat中的上下文路径
```



# 6.总结依赖

## 1.spring

```xml
<!--        spring依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.18</version>
        </dependency>
```



## 2.springmvc

```xml
 <!--        springMvc依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.18</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.4</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
        </dependency>
```



## 3.mybatis

```xml
<dependency>
 	<groupId>org.springframework</groupId>
 	<artifactId>spring-jdbc</artifactId>
 	<version>5.3.22</version>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.28</version>
</dependency>

<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid</artifactId>
	<version>1.2.8</version>
</dependency>

<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis</artifactId>
	<version>3.5.3</version>
</dependency>

<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis-spring</artifactId>
	<version>2.0.7</version>
</dependency>

<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper</artifactId>
	<version>5.2.0</version>
</dependency>
```



## 3.使用@responseBody

```xml
<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.3</version>
        </dependency>
```



## 4.事务管理

```xml
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.22</version>
        </dependency>
```
