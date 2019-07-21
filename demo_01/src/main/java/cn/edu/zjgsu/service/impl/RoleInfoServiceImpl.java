package cn.edu.zjgsu.service.impl;

import java.util.List;
import javax.management.relation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.zjgsu.bean.RoleInfo;
import cn.edu.zjgsu.dao.IRoleInfoDAO;
import cn.edu.zjgsu.service.IRoleInfoService;
import utils.Comm;
/**
 *
 *
 * <p>Title:RoleInfoServiceImpl </p>
 * <p>Description: 角色管理业务逻辑的实现类</p>
 * <p>Company:ZJGSU</p>
 * @author pf
 * @version 1.0
 */
@Service
@Transactional
public class RoleInfoServiceImpl implements IRoleInfoService{
    @Autowired
    private IRoleInfoDAO roledao;
    public void add(RoleInfo info) {
        roledao.add(info);
    }
    public List<Role> list(RoleInfo info){
        condition(info);
        return roledao.list(info);
    }
    public long getcount(RoleInfo info) {
        condition(info);
        return roledao.getcount(info);
    }
    /**
     * 查询条件
     * @param info
     */
    public void condition(RoleInfo info) {
        if(info!=null) {
            info.setRoleMark(Comm.MARK_YES);
            if(info.getRoleName()!=null
                    && !info.getRoleName().equals("")) {
                info.setRoleName("%"+info.getRoleName()+"%");
            }
        }
    }
    public RoleInfo getInfo(RoleInfo info) {
        return roledao.getInfo(info);
    }
    public void update(RoleInfo info) {
        roledao.update(info);
    }
    public void delete(Integer[] roleId) {
// 可以删除多个角色
        for(Integer id:roleId) {
            RoleInfo info=new RoleInfo();
            info.setRoleId(id);
// 修改为无效表示
            info.setRoleMark(Comm.MARK_NO);
// 其实删除做的只是修改，没有真正删除，否则要做级联删除
            roledao.update(info);
        }
    }
    /*public List<RoleInfo> list(RoleInfo info){
        condition(info);
        return roledao.list(info);
    }*/

}