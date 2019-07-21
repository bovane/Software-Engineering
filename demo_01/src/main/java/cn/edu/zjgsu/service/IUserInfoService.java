package cn.edu.zjgsu.service;

import java.util.List;
import cn.edu.zjgsu.bean.UserInfo;

/**
 * <p>Title:IUserInfoService</p>
 * <p>Description:用户管理的业务逻辑接口</p>
 * <p>Company:ZJGSU</p>
 * @author pf
 * @version 1.0
 */

public interface IUserInfoService {
    /**
     * 添加用户信息
     * @param user
     */
    public void add(UserInfo user);

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
     * @return
     */
    public void update(UserInfo user);

    /**
     * 根据编号删除用户信息
     * @param userId
     */
    public void delete(Integer[] userId);

}
