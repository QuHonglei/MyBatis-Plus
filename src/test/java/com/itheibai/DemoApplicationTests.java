package com.itheibai;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheibai.mapper.UserMapper;
import com.itheibai.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

//    @Autowired
//    UserPojoServiceImpl service;
//
//    @Autowired
//    UserPojoDao userPojoDao;
//
//    @Test
//    public void hello() {
//        List<UserPojo> userPojos = userPojoDao.selectList(null);
//        userPojos.forEach(System.out::println);
////        List<UserPojo> userPojos1 = userPojoDao.selectAll();
////        userPojos1.forEach(System.out::println);
//
//    }

    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testWrapper6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序
        wrapper.orderByAsc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    // 模糊查询
    @Test
    public void testWrapper5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id", "select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    // 模糊查询
    @Test
    public void testWrapper4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右 t%
        wrapper.notLike("name", "a")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void testWrapper3() {
        // 查询年龄在 20 ~ 30 岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30); // 区间
        Long count = userMapper.selectCount(wrapper); // 查询结果数
        System.out.println(count);
    }

    @Test
    public void testWrapper2() {
        // 查询名字itheibai
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "itheibai");
        User user = userMapper.selectOne(wrapper); // 查询一个数据，出现多个结果使用List 或者 Map
        System.out.println(user);
    }

    @Test
    public void testWrapper() {
        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println); // 和我们刚才学习的map对比一下
    }

    // 通过map删除
    @Test
    public void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "itheibai");
        map.put("age", 2);

        userMapper.deleteByMap(map);
    }

    // 测试id批量删除
    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(1473902116184969222L, 1473902116184969221L));
    }

    // 测试删除
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1L);
    }

    // 测试分页查询
    @Test
    public void testPage() {
        // 参数一：当前页
        // 参数二：页面大小
        // 使用了分页插件之后，所有的分页操作也变得简单的！
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println("页总条数：" + page.getTotal());
    }

    // 按条件查询之一 使用map操作
    @Test
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        // 自定义条件查询
        map.put("name", "itheibai");
        map.put("age", 19);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 测试查询
    @Test
    public void testSelect() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 测试查询
    @Test
    void contextLoads() {
        // 参数是一个 Wrapper ，条件构造器，这里我们先不用 null
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 测试乐观锁失败！多线程的情况下
    @Test
    public void testOptimisticLocker2() {
        // 线程 1
        User user = userMapper.selectById(1L);
        user.setName("itheibai01");
        user.setEmail("123456789@qq.com");

        // 模拟另外一个线程执行了插队操作
        User user1 = userMapper.selectById(1L);
        user1.setName("itheibai02");
        user1.setEmail("123456789@qq.com");
        userMapper.updateById(user1);

        // 自旋锁来多次尝试提交！
        userMapper.updateById(user);  // 如果没有乐观锁，就会覆盖插队线程的值！
    }

    // 测试乐观锁成功！
    @Test
    public void testOptimisticLocker() {
        // 1.查询用户信息
        User user = userMapper.selectById(1L);
        // 2.修改用户信息
        user.setName("itheibai");
        user.setEmail("123456789@qq.com");
        // 3.执行更新操作
        userMapper.updateById(user);
    }

    //测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        // 通过条件自动拼接动态sql
        user.setId(5L);
        user.setName("itheibai");
        user.setAge(19);


        // 注意：updateById 参数是一个 对象！
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    //测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("itheibai");
        user.setAge(2);
        user.setEmail("123456789@qq.com");

        int result = userMapper.insert(user); // 帮我们自动生成id
        System.out.println(result); // 受影响的行数
        System.out.println(user); // 发现，id会自动回填
    }

    @Test
    void contextLoads1() {
        //邮件设置1： 一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("通知-明天放假");
        mailMessage.setText("今晚聚餐！");

        mailMessage.setTo("1692671043@qq.com");
        mailMessage.setFrom("1692671043@qq.com");
        mailSender.send(mailMessage);
    }

    @Test
    void contextLoads2() throws MessagingException {
        //邮件设置1： 一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("通知-明天放假");
        helper.setText("<b style='color:red'>今晚聚餐</b>", true);

        //发送附件
        helper.addAttachment("1.jpg", new File("C:\\Users\\94886\\Downloads\\images\\1.jpg"));
        helper.addAttachment("2.jpg", new File("C:\\Users\\94886\\Downloads\\images\\2.jpg"));

        helper.setTo("1692671043@qq.com");
        helper.setFrom("1692671043@qq.com");

        mailSender.send(mimeMessage);
    }

}
