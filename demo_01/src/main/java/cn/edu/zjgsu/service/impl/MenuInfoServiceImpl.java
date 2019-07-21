package cn.edu.zjgsu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.zjgsu.bean.MenuInfo;
import cn.edu.zjgsu.dao.IMenuInfoDAO;
import cn.edu.zjgsu.service.IMenuInfoService;

@Service
@Transactional
public class MenuInfoServiceImpl implements IMenuInfoService {

    @Autowired
    private IMenuInfoDAO menudao;

    public List<MenuInfo> list(MenuInfo info) {
        return menudao.list(info);
    }

    public MenuInfo getInfo(MenuInfo info) {
        return menudao.getInfo(info);
    }

    public void add(MenuInfo info) {
        menudao.add(info);
    }

    public void update(MenuInfo info) {
        menudao.update(info);
    }


    public void delete(MenuInfo info) {
        menudao.delete(info);

    }


    public List<MenuInfo> getMenuList(Integer parentMenuId) {
        return menudao.getMenuList(parentMenuId);
    }
}
