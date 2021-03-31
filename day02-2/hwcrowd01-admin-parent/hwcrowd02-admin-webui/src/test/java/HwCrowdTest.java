import com.zgd.hw.mapper.AdminMapper;
import com.zgd.hw.service.api.AdminService;
import com.zgd.hw.util.HWCrowdUtil;
import entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huangwei
 * @description
 * @create 2021-03-30-21:14
 */
// 指定 Spring 给 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)

// 加载 Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class HwCrowdTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminMapper adminMapper;

    @Resource
    AdminService adminService;

    Logger logger = LoggerFactory.getLogger(HwCrowdTest.class);

    @Test
    public void testLog(){
        Admin admin = new Admin(5, "hw3", "111", "黄巍3号", "hw3@sina.cn", null);
        int insert = adminMapper.insert(admin);
        logger.debug("影响的行数为：" + insert);
    }
    @Test
    public void testSave(){
        Admin admin = new Admin(4, "hw2", "111", "黄巍2号", "hw2@sina.cn", null);
        adminService.save(admin);
    }

    @Test
    public void testContect() throws SQLException {
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }

    @Test
    public void testFindAll(){
        Admin admin = adminMapper.selectByPrimaryKey(1);
        System.out.println(admin);
    }

    @Test
    public void testMD5(){
        String pswd = "123456";
        String s = HWCrowdUtil.md5(pswd);
        logger.debug(s);
    }
}
