
package com.fengjx.ttwx.modules.wechat.controller.admin;

import com.fengjx.ttwx.common.utils.JsonUtil;
import com.fengjx.ttwx.modules.common.controller.MyController;
import com.fengjx.ttwx.modules.wechat.bean.SysUserEntity;
import com.fengjx.ttwx.modules.wechat.model.Material;
 





import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 素材管理
 *
 * @author fengjx.
 * @date：2015/5/28 0028
 */
@Controller
@RequestMapping("/admin/wechat/material")
public class MaterialController extends MyController {

    @Autowired
    private Material material;

    @RequestMapping(value = "")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("wechat/admin/material");
        return mv;
    }

    @RequestMapping("/multiple")
    public ModelAndView multiple(@RequestParam(value="id",required=false) String id,@RequestParam(value="fname",required=false) String fileName) {
        ModelAndView mv = new ModelAndView("/wechat/admin/multiple_news");
        mv.addObject("id", id);
        mv.addObject("fname", fileName);
        return mv;
    }

    @RequestMapping("/single")
    public ModelAndView single(@RequestParam(value="id",required=false) String id,@RequestParam(value="fname",required=false) String fileName) {
        ModelAndView mv = new ModelAndView("/wechat/admin/single_news");
        mv.addObject("id", id);
        mv.addObject("fname", fileName);
        return mv;
    }

    @RequestMapping("/page")
    @ResponseBody
    public String pageList(HttpServletRequest request, String msg_type, int pageNumber, int pageSize) {
        return material.getListPageByType(pageNumber, pageSize, msg_type,
                getLoginSysUserId(request)).toJson();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrUpdate(HttpServletRequest request, String contentsJson) {
        SysUserEntity sysUser = getLoginSysUser(request);
        List<Map<String, Object>> contents = StringUtils.isNotBlank(contentsJson) ? JsonUtil
                .parseJSON2List(contentsJson) : null;
                Map<String, Object> params=       getRequestMap(request);
        String previewStr = (String) params.get("preview");
        
        if(previewStr!=null&&previewStr.equals("true")){
        	try {
				toSendPreviewMessage(sysUser, contents, params);
			} catch (WxErrorException e) {
				 return retFail(e.getError().getErrorMsg());
			}
        }else{
         material.saveOrUpdate(params, contents, sysUser.getId());
        }
        return retSuccess();
    }

	private void toSendPreviewMessage(SysUserEntity sysUser,
			List<Map<String, Object>> contents, Map<String, Object> params) throws WxErrorException {
		
		String msgType = (String) params.get("msg_type");
		String wxUserId=(String)params.get("wxUserId");
        if (null != msgType && msgType.equals("news")) { // 图文消息
            if (null != contents && contents.size() > 0) {
                String xml_data = (String) params.get("xml_data");
                
					material.previewMessage(contents, xml_data, sysUser.getId(),wxUserId);
				 
            }
        }
	}

    @RequestMapping("/load")
    @ResponseBody
    public String load(String id) {
        return material.findById(id).toJson();
    }

    @RequestMapping(value = "/getContent", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String loadMaterialContentByUrl(HttpServletResponse response, String url) {
        return material.loadMaterialContentByUrl(url);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, String> delete(String id) {
        material.deleteById(id);
        return retSuccess();
    }
    
 

}
