package com.shuaigef.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuaigef.springsecuritydemo.model.entity.Authority;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public interface AuthorityMapper extends BaseMapper<Authority> {

    /**
     * 查询用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表
     */
    List<Authority> findListByUserIdAndType(
            @Param("userId") Long userId);

}




