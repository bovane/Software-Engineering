package cn.edu.zjgsu.dao;

import java.util.List;
import cn.edu.zjgsu.bean.UserInfo;

/**
 * <p>Title:IUserInfoDAO</p>
 * <p>Description:用户管理的数据访问接口</p>
 * <p>Company:ZJGSU</p>
 * @author pf
 * @version 1.0
 */
public interface IUserInfoDAO {
    /**
     * 添加用户信息
     * @param user 用户信息
     * @return 返回影响行数
     */
    public int add(UserInfo user);

    /**
     * 根据条件查询用户信息
     * @param user
     * @return
     */
    public UserInfo getUser(UserInfo user);

    /**
     * 根据条件查询用户角色信息
     * @param info
     * @return
     */
    public List<UserInfo> list(UserInfo info);

    /**
     * 获取总记录数
     * @param info
     * @return
     */
    public long getcount(UserInfo info);

    /**
     * 修改用户信息
     * @param user
     */
    public int update(UserInfo user);

}
