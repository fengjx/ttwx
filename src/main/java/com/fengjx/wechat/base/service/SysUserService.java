
package com.fengjx.wechat.base.service;

import com.fengjx.wechat.base.model.SysUser;

public abstract interface SysUserService {

    public abstract SysUser signin(String paramString1, String paramString2);

    public abstract void register(SysUser paramSysUser) throws Exception;

    public abstract boolean validUsername(String paramString);

    public abstract boolean validEmail(String paramString);

    public abstract boolean activate(String paramString);
}
