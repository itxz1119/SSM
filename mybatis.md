# 1.配置案例

## 1.mybatis.xml

`连接配置`

```xml
<configuration>
    <!--配置 日志打印-->
     <settings>
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>
    
    <typeAliases>
        <!--配置别名, 别名为类名的首字母大写,小写都可以-->
        <package name="com.bjpowernode.pojo"/>
    </typeAliases>
    
    <!--配置 mybatis 环境-->
    <environments default="mysql">
        <!--id:数据源的名称-->
        <environment id="mysql">
            <!--配置事务类型：使用 JDBC 事务（使用 Connection 的提交和回滚）-->
            <transactionManager type="JDBC"/>
            <!--数据源 dataSource：创建数据库 Connection 对象
            type: POOLED 使用数据库的连接池
            -->
            <dataSource type="POOLED">
                <!--连接数据库的四个要素-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/db_atm?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=true&amp;serverTimezone=Asia/Shanghai"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <!--告诉 mybatis 要执行的 sql 语句的位置-->
        <mapper resource="dao/UserDao.xml"/>
    </mappers>
</configuration>
```



## 2.创建对象-util

```java
public class MybatisUtil {
    //定义 SqlSessionFactory
    private static SqlSessionFactory factory = null;

    static {
        //使用 静态块 创建一次 SqlSessionFactory
        try {
            String config = "mybatis.xml";
            //读取配置文件
            InputStream in = Resources.getResourceAsStream(config);
            //创建 SqlSessionFactory 对象
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            factory = null;
            e.printStackTrace();
        }
    }

    /* 获取 SqlSession 对象 */
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        if (factory != null) {
            session = factory.openSession();
        }
        return session;
    }
}
```



## 3.mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
 namespace：必须有值，自定义的唯一字符串
 推荐使用：dao 接口的全限定名称
-->
<mapper namespace="dao.UserDao">
    <!--
   特别注意,如果数据表列名称和实体类属性名不一致,查不出来数据,需要定义resultMap
    -->
    <resultMap id="userMap" type="pojo.User">
        <id column="id" property="id"/>
        <result column="name" property="username"/>
        <result column="password" property="pwd"/>
    </resultMap>
    <!--
    <select>: 查询数据， 标签中必须是 select 语句
    id: sql 语句的自定义名称，推荐使用 dao 接口中方法名称，
    使用名称表示要执行的 sql 语句
    resultType: 查询语句的返回结果数据类型，使用全限定类名
    -->
    <select id="selectAll" resultType="pojo.User">
        <!--要执行的 sql 语句-->
        select * from tb_user;
    </select>
</mapper>
```



# 2.传递参数

## 1.对象传参

`1.xml`

```xml
<!-- 设置resultType后,{}里面的字段和实体类一致即可
	如果数据表的列名称和实体类不一致,需要配置resultMap,否则查询出的数据为null
-->
<select id="selectById2"  resultMap="userMap" resultType="pojo.User">
    select * from tb_user where id =#{id} and password = #{pwd} and name = #{username}
</select>
```



`2.test`

```java
 @Test
    public void test04() {
        User user1 = new User();
        user1.setId(3);
        user1.setUsername("rose");
        user1.setPwd("123456");
        User user = userDao.selectById2(user1);
        System.out.println(user);
    }
```



## 2.多个参数-使用@Param

```java
//xml中 {}写id,username即可
List selectMultiParam(@Param("id") String id, @Param("username") int name);
```



## 3.Map传参



```java
//dao
List selectMultiMap(Map map);


//test  
Map<String,Object> data = new HashMap<String,Object>();
data.put(“id”,”3”);
data.put(“name”,"rose");

//xml  {}中,与map的key一致即可
<select id="selectMultiMap" resultType="com.bjpowernode.domain.Student">
 select * from tb_user where name=#{name} and id =#{id}
</select>

```



# 3.mybatis.xml配置

```xml
<!-- settings是 MyBatis 中全局的调整设置，它们会改变 MyBatis 的运行时行为,应谨慎设置 -->  
	    <settings>  
	        <!-- 该配置影响的所有映射器中配置的缓存的全局开关。默认值true -->  
	      <setting name="cacheEnabled" value="true"/>  
            
	      <!--延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置fetchType属性来覆盖该项的开关状态。默认值false  --> 
	      <setting name="lazyLoadingEnabled" value="true"/>  
            
	        <!-- 是否允许单一语句返回多结果集（需要兼容驱动）。 默认值true -->  
	      <setting name="multipleResultSetsEnabled" value="true"/>  
            
	      <!-- 使用列标签代替列名。不同的驱动在这方面会有不同的表现， 具体可参考相关驱动文档或通过测试这两种不同的模式来观察所用驱动的结果。默认值true -->  
	      <setting name="useColumnLabel" value="true"/>  
            
	      <!-- 允许 JDBC 支持自动生成主键，需要驱动兼容。 如果设置为 true 则这个设置强制使用自动生成主键，尽管一些驱动不能兼容但仍可正常工作（比如 Derby）。 默认值false  -->  
	      <setting name="useGeneratedKeys" value="false"/>  
            
	     <!--  指定 MyBatis 应如何自动映射列到字段或属性。 NONE 表示取消自动映射；PARTIAL 只会自动映射没有定义嵌套结果集映射的结果集。 FULL 会自动映射任意复杂的结果集（无论是否嵌套）。 -->   
	     <!-- 默认值PARTIAL -->  
	      <setting name="autoMappingBehavior" value="PARTIAL"/>  
	      <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>  
            
	     <!--  配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新。默认SIMPLE  -->  
	      <setting name="defaultExecutorType" value="SIMPLE"/>  
            
	      <!-- 设置超时时间，它决定驱动等待数据库响应的秒数。 -->  
	      <setting name="defaultStatementTimeout" value="25"/>   
	      <setting name="defaultFetchSize" value="100"/> 
            
	      <!-- 允许在嵌套语句中使用分页（RowBounds）默认值False -->  
	      <setting name="safeRowBoundsEnabled" value="false"/>  
            
	      <!-- 是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射。  默认false --> 
	      <setting name="mapUnderscoreToCamelCase" value="false"/>  
            
	      <!-- MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。  
	             默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。  
	            若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。  -->  
	      <setting name="localCacheScope" value="SESSION"/>  
            
	      <!-- 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR 或 OTHER。  -->  
	      <setting name="jdbcTypeForNull" value="OTHER"/>  
            
	    <!--   指定哪个对象的方法触发一次延迟加载。  -->  
	      <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>  
	    </settings> 

```



# 4.别名

```
别名				映射的类型
byte 				byte 
long 				long 
short 				short 
int					int
integer 			int
double 				double 
float 				float 
boolean				boolean
string 				String 
byte 				Byte 
long 				Long 
short 				Short 
int					Integer 
integer 			Integer 
double 				Double 
float 				Float 
boolean				Boolean 
date 				Date 
decimal 			BigDecimal
bigdecimal			BigDecimal
map              	Map/HashMap
```



# 5.动态sql

```xml
<!--  集合判断  -->
<update id="deleteStudents">
        update tb_student
        <if test="list != null and list.size > 0">
            set isdeleted = 1 where id in
            <foreach collection="list" open="(" close=")" separator=","
                     item="stuid">
                #{stuid}
            </foreach>
        </if>
    </update>

<!--  数组判断  -->
    <update id="deleteStudentByArray">
        update tb_student
        <if test="array != null and array.length > 0">
            set isdeleted = 1 where id in
            <foreach collection="array" open="(" close=")" separator=","
                     item="stuid">
                #{stuid}
            </foreach>
        </if>
    </update>

<!--简单类型-->
    <select id="findStuByIds" resultType="Student">
        select * from tb_student
        <where>
            <if test="list != null and list.size>0">
                id in
                <foreach collection="list" item="stuid" open="(" close=")" separator=",">
                    #{stuid}
                </foreach>
            </if>
        </where>
    </select>
<!--    对象类型-->
    <select id="findStuByStus" resultType="Student">
        select * from tb_student
        <where>
            <if test="list != null and list.size>0">
                id in
                <foreach collection="list" item="student" open="(" close=")" separator=",">
                    #{student.id}
                </foreach>
            </if>
        </where>
    </select>

 <!--  多条件组合查询加分页  -->
    <select id="findStuPage" parameterType="StudentVo" resultType="Student">
        select * from tb_student
        <where>
            isdeleted =0

            <if test="name != null and name !='' ">
                and name like "%" #{name} "%"
            </if>
            <if test="(minAge != null and minAge != '') and (maxAge != null and maxAge != '')">
                and age between #{minAge} and #{maxAge}
            </if>
            <if test="(minAge != null and minAge != '') and (maxAge == null or maxAge == '')">
                and age &gt;=#{minAge}
            </if>
            <if test="(minAge == null or minAge == '') and (maxAge != null and maxAge != '')">
                and age &lt;=#{maxAge}
            </if>
 <!--  特别注意,这里不能判断 start != '' 以及  pageSize != ''-->
            <if test="(start != null and start >= 0) and (pageSize != null and pageSize > 0)">
                limit #{start},#{pageSize}
            </if>

        </where>
    </select>

 <delete id="deleteBatchRelation">
        delete
        from pms_attr_attrgroup_relation
        where
        <foreach collection="relationList" item="item" separator=" or ">
            (attr_id = #{item.attrId} and attr_group_id = #{item.attrGroupId})
        </foreach>
    </delete>
```

