package com.wuwii.service.impl;

import com.wuwii.entity.Employee;
import com.wuwii.entity.EmployeeDetail;
import com.wuwii.form.EmployeeSearch;
import com.wuwii.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * EmployeeServiceImpl Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>03/08/2018</pre>
 */
// 获取启动类，加载配置
@SpringBootTest
// 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService employeeService;

    @Before
    public void before() throws Exception {
        System.out.println("before");
    }

    @After
    public void after() throws Exception {
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }

    /**
     * Method: pageBySearch(EmployeeSearch search, Pageable pageable)
     */
    @Test
    public void testPageBySearch() throws Exception {
        EmployeeSearch employeeSearch = new EmployeeSearch(null, null, "程序猿");
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<Employee> employees = employeeService.pageBySearch(employeeSearch, new PageRequest(0, 5, sort));
        Assert.assertThat("18772383543", Matchers.equalTo(employees.getContent().get(0).getDetail().getPhone()));
    }

    @Test
    public void testSave() {
        Employee employee = new Employee();
        EmployeeDetail detail = new EmployeeDetail();
        detail.setAge(22);
        detail.setName("jams");
        detail.setPhone("42345");
        employee.setDetail(detail);
        employeeService.save(employee);
    }

    @Test
    public void testListByAge() {
        List<Employee> list = employeeService.listByAge(24);
        Assert.assertThat(1L, Matchers.equalTo(list.get(0).getId()));
    }

    @Test
    public void testListByAge1() {
        List<Employee> list = employeeService.listByAge1(24);
        Assert.assertThat(1L, Matchers.equalTo(list.get(0).getId()));
    }

    @Test
    public void groupByName() {
        List list = employeeService.groupByName("o");
        System.out.println(list);
    }

    @Test
    public void testFindEmployee() {
        List list = employeeService.findEmployee();
        System.out.println(list);
    }


} 
