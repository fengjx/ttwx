
package com.fengjx.wechat.base.dao;

import com.fengjx.wechat.base.model.SysUser;

public abstract interface SysUserMapper {

    public abstract int deleteByPrimaryKey(String paramString);

    public abstract int insert(SysUser paramSysUser);

    public abstract int insertSelective(SysUser paramSysUser);

    public abstract SysUser selectByPrimaryKey(String paramString);

    public abstract SysUser selectByCondition(SysUser paramSysUser);

    public abstract int updateByPrimaryKeySelective(SysUser paramSysUser);

    public abstract int updateByPrimaryKey(SysUser paramSysUser);

    public abstract int getCount(SysUser paramSysUser);
}
