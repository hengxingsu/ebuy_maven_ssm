package com.lcvc.ebuy_maven_ssm.service;

import com.lcvc.ebuy_maven_ssm.dao.AdminDao;
import com.lcvc.ebuy_maven_ssm.dao.SqlSessionFactoryUtil;
import com.lcvc.ebuy_maven_ssm.model.Admin;
import com.lcvc.ebuy_maven_ssm.util.SHA;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;


    /**
     * 根据账户名和密码去数据库查询，进行登录判断
     * @param username 账户名
     * @param password 密码
     * @return null表示登录失败
     */
    public Admin login(String username, String password){
        //将密码加密后再进行比对
        password= SHA.getResult(password);
        Admin admin=adminDao.login(username, password);
        return admin;
    }


    /*
	 * 张三丰
	 * 20180330
	 * 将新密码保存到数据库中
	 * @return true表示密码更改成功，false表示密码更改失败
	 */
    public boolean updatePassword(String newPass,Integer id){
        Boolean status=false;//默认编辑失败
        newPass= SHA.getResult(newPass);
        if(adminDao.updatePassword(newPass,id)>0){
            status=true;
        }
        return status;
    }

    /**
     *1、修改后的账户名不能与其他账户名重名
     * @param admin
     * @return true表示 ，false表示
     */
    public boolean updateAdmin(Admin admin){
        boolean status=false;
        if (adminDao.existsAdmin(admin.getUsername(),admin.getId())==0){    //如果不重名
        if (adminDao.updateAdmin(admin)==1){
            status=true;
        }else {
            status=false;
          }
        }
        return  status;
    }

    /**
     * 查找在数据中和指定用户名重名的个数  （用于账户编辑）
     * @param username
     * @param id
     * @return true表示存在账户重名 ，false表示不存在
     */
    public boolean existsAdmin(String username,Integer id){
        if (adminDao.existsAdmin(username,id)==0){
            return  false;
        }else {
            return true;
        }
    }

    /**
     * 判断账户是否存在 （用于创建账户的时候）
     * @param username
     * @return
     */
    public  boolean existsUsername(String username){
    if (adminDao.existsUsername(username)==0){
        return  false;
    }else {
        return true;
    }
}
    /**
     * 返回所有的管理账户集合
     * @return 以List的方式返回
     */
   public List<Admin> getAdminList(){
       return adminDao.getAdminList();
   }

    /**
     * 删除指定账户
     * @param id  被删除的账户id
     *@param adminId  执行删除的管理账户管理
     * @return  true表示修改成功
     */
    public boolean deleteAdmin(Integer id,Integer adminId){
        Boolean status=false;//存储修改结果，默认编辑失败
        if (id!=null&&adminId!=null) {
            if (adminId!=id.intValue()) {
               int n= adminDao.deleteAdmin(id);
               if (n==1) {
                   status = true;
               }
            }
        }
        return status;
    }

    /**
     * 添加指定账户
     * @param admin
     * @return  false表示修改失败 ，true表示修改成功
     */
    public boolean saveAdmin(Admin admin){
        boolean status=false;
        admin.setPassword(SHA.getResult("123456"));   //默认密码
        admin.setCreateTime(new Date());                        //系统当前时间为创建时间
            if (adminDao.saveAdmin(admin)>0){
            status=true;
        }
        return  status;
    }


    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return  null表示没有找到
     */
   public Admin getAdmin(Integer id){
        Admin admin=null;
        if (id!=null){
            admin=adminDao.getAdmin(id);
        }
        return  admin;
    }

}
