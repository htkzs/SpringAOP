package com.atguigu;


import com.atguigu.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@ContextConfiguration(locations = "classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /*
     * @Author GhostGalaxy
     * @Description 测试数据库连接
     * @Date 20:23:29 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void datasource() throws SQLException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
        connection.close();

    }
    /*
     * @Author GhostGalaxy
     * @Description 测试JdbcTemplate
     * @Date 20:43:07 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void JdbcTemplate(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

        System.out.println( jdbcTemplate);
    }

    /*
     * @Author GhostGalaxy
     * @Description 插入数据
     * @Date 20:43:43 2022/11/25
     * @Param
     * @return
     **/
    @Test
    public void insertMethod(){
        String sql="insert into employee(emp_id, emp_name, salary) values (?,?,?)";
        jdbcTemplate.update(sql,null,"lisi",15800.25);
    }
    /*
     * @Author GhostGalaxy
     * @Description 修改一条数据
     * @Date 21:06:47 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void updateMethod(){
        String updateSql = "update employee set salary=? where emp_id=?";
        jdbcTemplate.update(updateSql,12800.25,1011);
    }
    @Test
    public void batchInsert(){
        String batchInsertSql="insert into employee(emp_name, salary) values (?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"赵六",9800.5});
        batchArgs.add(new Object[]{"马哈哈",9850.5});
        batchArgs.add(new Object[]{"李雷",9400.5});
        batchArgs.add(new Object[]{"韩梅梅",91800.5});
        //数组的长度就是要插入的记录跳数 sql语句执行的次数
        jdbcTemplate.batchUpdate(batchInsertSql,batchArgs);
    }
    //<T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args) throws DataAccessException;
    /*
     * @Author GhostGalaxy
     * @Description 批量查询 将查询结果封装为一个对象返回
     * jdbcTemplate在方法级别进行了区分查询集合:jdbcTemplate.query()
                                            查询单个对象:jdbcTemplate.queryForobject( )
                                            如果查询没结果就报错;
     * @Date 21:16:32 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void batchQuery(){
        String querySql = "select emp_id as empId,emp_name as empName,salary from employee where emp_id > ?";
        List<Employee> employeeList = jdbcTemplate.query(querySql, new BeanPropertyRowMapper<>(Employee.class), 1012);
        System.out.println(employeeList);
    }
    /*
     * @Author GhostGalaxy
     * @Description 查询单个结果 如果不存在时，将报异常  org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
     * @Date 21:40:49 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void singleQuery(){
        String querySql = "select emp_id as empId,emp_name as empName,salary from employee where emp_id = ?";
        Employee employee =null;
        try{
            employee = jdbcTemplate.queryForObject(querySql, Employee.class,1019);
            System.out.println(employee);
        }catch (EmptyResultDataAccessException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
    /*
     * @Author GhostGalaxy
     * @Description 注意返回一个值也是使用 jdbcTemplate.queryForObject();
     * @Date 21:54:51 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void maxQuery(){
        String sql ="select max(salary) from employee";
        Double maxSalary = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(maxSalary);
    }
    /*
     * @Author GhostGalaxy
     * @Description 具名传参
     * @Date 22:21:53 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void update(){
        String sql="insert into employee(emp_name, salary) values (:empName,:salary)";
        Map<String,Object> map = new HashMap<>();
        map.put("empName","蜡笔小新");
        map.put("salary",9898.98);
        namedParameterJdbcTemplate.update(sql,map);
    }

    /*
     * @Author GhostGalaxy
     * @Description int update(String sql, SqlParameterSource paramSource) throws DataAccessException; 使用Bean属性作为sql参数来源
     * @Date 22:27:24 2022/11/25
     * @Param []
     * @return void
     **/
    @Test
    public void updateByParamSource(){
        String sql="insert into employee(emp_name, salary) values (:empName,:salary)";
        Employee emp = new Employee();
        emp.setEmpName("冰墩墩");
        emp.setSalary(9879.56);

        namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(emp));
    }


}
