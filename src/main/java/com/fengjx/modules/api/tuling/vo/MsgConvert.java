
package com.fengjx.modules.api.tuling.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.modules.api.tuling.vo.resp.*;

import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 *{
 "  code":302000,
 "  text":"********",
 "  list":[{
 "      article":"",
 "      source":"",
 "      detailurl":"",
 "      icon":""
 *  }]
 *}
 * 消息转换器
 * Created by fengjx.
 * Date：2014/12/13 0013
 */
public class MsgConvert {

    public static BaseRespBean strJson2Bean(String json) {
        ListBean res = new ListBean();
        JSONObject jsonObject = JsonUtil.getJSONFromString(json);
        String code = jsonObject.getString("code");
        if (ResutlCode.TEXT.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.TEXT);
        } else if (ResutlCode.NEWS.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.NEWS);
        } else if (ResutlCode.FLIGHT.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.FLIGHT);
        } else if (ResutlCode.HOTEL.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.HOTEL);
        } else if (ResutlCode.PRICE.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.PRICE);
        } else if (ResutlCode.SOFT.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.SOFT);
        } else if (ResutlCode.TRAIN.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.TRAIN);
        } else if (ResutlCode.VIDEO.getCode().equals(code)) {
            res = createListBean(jsonObject, ResutlCode.VIDEO);
        } else if (ResutlCode.LINK.getCode().equals(code)) {
            LinkBean bean = new LinkBean();
            bean.setResult(ResutlCode.LINK);
            bean.setText(jsonObject.getString("text"));
            bean.setUrl(jsonObject.getString("url"));
            return bean;
        } else if (ResutlCode.ERROR40001.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40001);
        } else if (ResutlCode.ERROR40002.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40002);
        } else if (ResutlCode.ERROR40003.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40003);
        } else if (ResutlCode.ERROR40004.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40004);
        } else if (ResutlCode.ERROR40005.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40005);
        } else if (ResutlCode.ERROR40006.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40006);
        } else if (ResutlCode.ERROR40007.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR40007);
        } else if (ResutlCode.ERROR50000.getCode().equals(code)) {
            return createTextBean(jsonObject, ResutlCode.ERROR50000);
        } else {
            TextBean bean = new TextBean();
            bean.setResult(ResutlCode.ERROR00000);
            bean.setText(ResutlCode.ERROR00000.getText());
            return bean;
        }
        return res;
    }

    public static String toWechatMsg(BaseRespBean bean) {
        WxMpXmlOutNewsMessage msg = WxMpXmlOutMessage.NEWS()
                .fromUser("")
                .toUser("").build();

        WxMpXmlOutNewsMessage.Item item = null;
        if (ResutlCode.TEXT.getCode().equals(bean.getCode())) {
            TextBean text = (TextBean) bean;
            WxMpXmlOutTextMessage m = new WxMpXmlOutTextMessage();
            m.setContent(text.getText());
            m.setCreateTime(0L);
            m.setFromUserName("");
            m.setToUserName("");
            return m.toXml();
        } else if (ResutlCode.NEWS.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 10; i++) {
                    NewsBean news = (NewsBean) list.get(i);
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(news.getIcon());
                    item.setTitle(news.getArticle());
                    item.setUrl(news.getDetailurl());
                    msg.addArticle(item);
                }
            }
        } else if (ResutlCode.FLIGHT.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 10; i++) {
                    String desc = "";
                    FlightBean flight = (FlightBean) list.get(i);
                    desc += "航班：" + flight.getFlight();
                    desc += "\n路线：" + flight.getRoute();
                    desc += "\n起飞时间：" + flight.getStarttime();
                    desc += "\n到达时间：" + flight.getEndtime();
                    desc += "\n状态：" + flight.getState();
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(flight.getIcon());
                    item.setDescription(desc);
                    item.setUrl(flight.getDetailurl());
                    msg.addArticle(item);
                }
            }
        } else if (ResutlCode.HOTEL.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 10; i++) {
                    String desc = "";
                    HotelBean hotel = (HotelBean) list.get(i);
                    desc += "酒店名称：" + hotel.getName();
                    desc += "\n价格：" + hotel.getPrice();
                    desc += "\n满意度：" + hotel.getSatisfaction();
                    desc += "\n数量：" + hotel.getCount();
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(hotel.getIcon());
                    item.setDescription(desc);
                    item.setUrl(hotel.getDetailurl());
                    msg.addArticle(item);

                }
            }
        } else if (ResutlCode.PRICE.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 11; i++) {
                    String desc = "";
                    PriceBean price = (PriceBean) list.get(i);
                    desc += "名称：" + price.getName();
                    desc += "\n价格：" + price.getPrice();
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(price.getIcon());
                    item.setDescription(desc);
                    item.setUrl(price.getDetailurl());
                    msg.addArticle(item);
                }
            }
        } else if (ResutlCode.SOFT.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 11; i++) {
                    String desc = "";
                    SoftBean soft = (SoftBean) list.get(i);
                    desc += "软件名称：" + soft.getName();
                    desc += "\n下载量：" + soft.getCount();
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(soft.getIcon());
                    item.setDescription(desc);
                    item.setUrl(soft.getDetailurl());
                    msg.addArticle(item);
                }
            }
        } else if (ResutlCode.TRAIN.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 11; i++) {
                    String desc = "";
                    TrainBean train = (TrainBean) list.get(i);
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(train.getIcon());
                    item.setDescription(desc);
                    item.setUrl(train.getDetailurl());
                    msg.addArticle(item);
                }
            }
        } else if (ResutlCode.VIDEO.getCode().equals(bean.getCode())) {
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size() && i < 11; i++) {
                    String desc = "";
                    VideoBean video = (VideoBean) list.get(i);
                    desc += "名称：" + video.getName();
                    desc += "\n介绍：" + video.getInfo();
                    item = new WxMpXmlOutNewsMessage.Item();
                    item.setPicUrl(video.getIcon());
                    item.setDescription(desc);
                    item.setUrl(video.getDetailurl());
                    msg.addArticle(item);
                }
            }

        } else if (ResutlCode.LINK.getCode().equals(bean.getCode())) {
            LinkBean link = (LinkBean) bean;
            item = new WxMpXmlOutNewsMessage.Item();
            item.setTitle("点击进入");
            item.setDescription(link.getText());
            item.setUrl(link.getUrl());
            msg.addArticle(item);
        } else {
            TextBean text = (TextBean) bean;
            WxMpXmlOutTextMessage m = new WxMpXmlOutTextMessage();
            m.setContent("暂不支持该功能[" + text.getCode() + "]");
            m.setCreateTime(0L);
            m.setFromUserName("");
            m.setToUserName("");
            return m.toXml();
        }
        if (CollectionUtils.isEmpty(msg.getArticles())) {
            item = new WxMpXmlOutNewsMessage.Item();
            item.setTitle("很遗憾");
            item.setDescription("暂无相关数据。");
            msg.addArticle(item);
        }
        return msg.toXml();
    }

    /**
     * 构建集合返回实体
     * 
     * @param jsonObject
     * @param resutlCode
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T extends ListBaseBean> ListBean createListBean(JSONObject jsonObject,
            ResutlCode resutlCode) {
        ListBean res = new ListBean();
        res.setResult(resutlCode);
        res.setText(jsonObject.getString("text"));
        JSONArray jsonArr = jsonObject.getJSONArray("list");
        T bean = null;
        for (int i = 0; i < jsonArr.size(); i++) {
            bean = (T) JsonUtil.toBean(jsonArr.getJSONObject(i), resutlCode.getClassz());
            res.add(bean);
        }
        return res;
    }

    /**
     * 构建文字返回实体
     * 
     * @param jsonObject
     * @param resutlCode
     * @return
     */
    private static BaseRespBean createTextBean(JSONObject jsonObject, ResutlCode resutlCode) {
        TextBean bean = new TextBean();
        bean.setResult(resutlCode);
        bean.setText(jsonObject.getString("text"));
        return bean;
    }

}
