
package com.fengjx.modules.api.tuling.vo.resp;

import com.fengjx.commons.bean.ToStringBase;

/**
 * 实体基类
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public abstract class BaseRespBean extends ToStringBase {

    private ResutlCode result;

    private String text;

    public String getCode() {
        return result.getCode();
    }

    public ResutlCode getResult() {
        return result;
    }

    public void setResult(ResutlCode result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
