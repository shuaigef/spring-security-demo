package com.shuaigef.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaigef.springsecuritydemo.mapper.AuthorityMapper;
import com.shuaigef.springsecuritydemo.model.entity.Authority;
import com.shuaigef.springsecuritydemo.model.entity.SysAuthorityMeta;
import com.shuaigef.springsecuritydemo.service.AuthorityService;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * 权限服务实现
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority>
        implements AuthorityService {

    private List<Authority> listTreeProcess(List<Authority> authorityList) {
        List<Authority> tree = authorityList.stream()
                //过滤出parentId为0的节点为根节点 (如果有多个根节点也可以)
                .filter(item -> item.getParentId() == 0)
                .map(item -> {
                    item.setChildren(findChildNode(item, authorityList));
                    return item;
                }).collect(Collectors.toList());
        return tree;
    }

    private List<Authority> findChildNode(Authority parentNode, List<Authority> nodes) {
        List<Authority> childList = nodes.stream()
                //过滤出parentNode的子节点
                .filter(item -> Objects.equals(parentNode.getId(), item.getParentId()))
                //重新映射成一个新的node节点
                .map(item -> {
                    //这里设置当前节点的子节点列表, 子节点列表的获取是通过递归的方式获取的
                    item.setChildren(findChildNode(item, nodes));
                    return item;
                }).collect(Collectors.toList());
        return childList;
    }

    @Override
    public List<Authority> findMenuTree(Long userId) {
        // 用户被赋权的所有菜单
        List<Authority> authorityList =
                baseMapper.findListByUserIdAndType(userId);
        for (int i = authorityList.size() - 1; i >= 0; i--) {
            Authority authority = authorityList.get(i);
            SysAuthorityMeta meta = new SysAuthorityMeta();
            meta.setHideMenu(authority.getHidden() != 0);
            meta.setIcon(authority.getMenuIcon());
            meta.setComponentName(authority.getComponentName());
            meta.setTitle(authority.getMenuName());
            authority.setMeta(meta);
        }

        //排序
        List<Authority> collect = authorityList.stream()
                .sorted(Comparator.comparing(Authority::getOrderNo))
                .collect(Collectors.toList());
        // list -> tree
        return listTreeProcess(collect);
    }

}




