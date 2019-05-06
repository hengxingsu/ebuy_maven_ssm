package dao;

import com.lcvc.ebuy_maven_ssm.dao.AdminDao;
import com.lcvc.ebuy_maven_ssm.dao.SqlSessionFactoryUtil;
import com.lcvc.ebuy_maven_ssm.model.Admin;
import com.lcvc.ebuy_maven_ssm.test.SpringJunitTest;
import com.lcvc.ebuy_maven_ssm.util.SHA;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class AdminDaoTest extends SpringJunitTest {

    @Resource
    private AdminDao adminDao;
    @Test
    public void print(){
        System.out.println("hello");
    }

    @Test
    public void testLogin(){
        Admin admin=adminDao.login("user",  SHA.getResult("123456"));
        System.out.println(admin);
        Admin admin2=adminDao.login("user",  SHA.getResult("12345611"));
        System.out.println(admin2);
    }

    @Test
    public void testUpdatePassword(){
        System.out.println(adminDao.updatePassword("123",2));
    }


    @Test
    public void  testUpdateAdmin(){
       // System.out.println(adminDao.updateAdmin("hahha","haha",23));
      //  System.out.println(adminDao.updateAdmin("hahha","haha",12));

    }
    @Test
    public void  testExistsAdmin(){
        System.out.println(adminDao.existsAdmin("user",1));
        System.out.println(adminDao.existsAdmin("user11",1));
        System.out.println(adminDao.existsAdmin("user1",1));
    }
    @Test
    public  void  testgetAdminList(){
        List<Admin> list= adminDao.getAdminList();
        for (int i=0;i< list.size();i++){
            Admin admin=list.get(i);
            System.out.println(admin.getUsername());
        }
    }
    @Test
    public  void  testDeleteAdmin(){
        System.out.println(adminDao.deleteAdmin(2));
        System.out.println(adminDao.deleteAdmin(0));
    }


    @Test
    public  void  testSaveAdmin(){
        Admin admin=new Admin();
        admin.setUsername("333");
        admin.setPassword("123");
        admin.setName("呆呆");
        admin.setCreateTime(new Date());
        System.out.println(adminDao.saveAdmin(admin));
    }

    @Test
    public  void  testGetAdmin(){
        System.out.println(adminDao.getAdmin(2));
        System.out.println(adminDao.getAdmin(211));
    }

}
