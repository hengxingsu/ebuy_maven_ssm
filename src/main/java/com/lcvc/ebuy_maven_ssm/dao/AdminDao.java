package com.lcvc.ebuy_maven_ssm.dao;

import com.lcvc.ebuy_maven_ssm.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    /**
     * 根据账户名和密码去数据库查询
     * @param username 账户名
     * @param password 密码
     * @return null表示没有找到记录
     */
    Admin login(@Param(value = "username") String username, @Param(value = "password") String password);

    /*
	 * 张三丰
	 * 20180330
	 * 将新密码保存到数据库中
	 * @return 更改了多少条记录
	 */
    int updatePassword(@Param(value = "password")String newPass,@Param(value = "id")Integer id);

    /**
     *
     * @param
     * @param admin
     * @param
     * @return 更改了多少条记录
     */
    int updateAdmin(Admin admin);
    /**
     * 查找在数据中和指定用户名重名的个数
     * @param username
     * @param id
     * @return
     */
    int existsAdmin(@Param(value = "username")String username,@Param(value = "id")Integer id);

    int existsUsername(@Param(value = "username")String username);

    /**
     * 返回所有的管理账户集合
     * @return 以List的方式返回
     */
    List<Admin>getAdminList();

    /**
     * 删除指定账户
     * @param id 关键字
     * @return  删除了多少条记录
     */

  int   deleteAdmin(Integer id);

    /**
     * 保存指定账户进数据库
     * @param admin
     * @return   添加了多少条记录
     */
   int saveAdmin(Admin admin);

    /**
     * 根据标识符获取对应的管理账户对象
     * @param id
     * @return  null表示没有找到
     */
   Admin getAdmin(int id);
}
