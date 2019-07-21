package com.zjsu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjsu.bean.MenuInfo;
import com.zjsu.dao.IMenuInfoDAO;
import com.zjsu.service.IMenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = IMenuInfoService.class)
// @Service
@Transactional
@Component
public class MenuInfoServiceImpl implements IMenuInfoService {

    @Autowired
    private IMenuInfoDAO menudao;

    public void setMenudao(IMenuInfoDAO menudao) {
        this.menudao = menudao;
    }

    public List<MenuInfo > list( MenuInfo info) {
        return menudao.list(info);
    }

    public MenuInfo getInfo( MenuInfo info) {
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

    public List<MenuInfo > getMenuList( Integer parentMenuId) {
        return menudao.getMenuList(parentMenuId);
    }
}
