package com.fjx.wechat.extension.api.tuling.vo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fjx.common.utils.CollectionUtil;
import com.fjx.common.utils.JSONUtil;
import com.fjx.wechat.extension.api.tuling.vo.resp.*;
import com.fjx.wechat.mysdk.beans.resp.Article;
import com.fjx.wechat.mysdk.beans.resp.RespNewsMessage;
import com.fjx.wechat.mysdk.tools.MessageUtil;

import java.util.ArrayList;
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

    public static BaseRespBean strJson2Bean(String json){
        ListBean res = new ListBean();
        JSONObject jsonObject = JSONUtil.getJSONFromString(json);
        String code = jsonObject.getString("code");
        if(ResutlCode.TEXT.getCode().equals(code)){
            return createTextBean(jsonObject,ResutlCode.TEXT);
        }else if(ResutlCode.NEWS.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.NEWS);
        }else if(ResutlCode.FLIGHT.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.FLIGHT);
        }else if(ResutlCode.HOTEL.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.HOTEL);
        }else if(ResutlCode.PRICE.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.PRICE);
        }else if(ResutlCode.SOFT.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.SOFT);
        }else if(ResutlCode.TRAIN.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.TRAIN);
        }else if(ResutlCode.VIDEO.getCode().equals(code)){
            res = createListBean(jsonObject,ResutlCode.VIDEO);
        }else if(ResutlCode.LINK.getCode().equals(code)){
            LinkBean bean = new LinkBean();
            bean.setResult(ResutlCode.LINK);
            bean.setText(jsonObject.getString("text"));
            bean.setUrl(jsonObject.getString("url"));
            return bean;
        }else if(ResutlCode.ERROR40001.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40001);
        }else if(ResutlCode.ERROR40002.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40002);
        }else if(ResutlCode.ERROR40003.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40003);
        }else if(ResutlCode.ERROR40004.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40004);
        }else if(ResutlCode.ERROR40005.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40005);
        }else if(ResutlCode.ERROR40006.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40006);
        }else if(ResutlCode.ERROR40007.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR40007);
        }else if(ResutlCode.ERROR50000.getCode().equals(code)){
            return createTextBean(jsonObject, ResutlCode.ERROR50000);
        }else{
            TextBean bean = new TextBean();
            bean.setResult(ResutlCode.ERROR00000);
            bean.setText(ResutlCode.ERROR00000.getText());
            return bean;
        }
        return res;
    }


    public static String toWechatMsg(BaseRespBean bean){
        RespNewsMessage msg = new RespNewsMessage();
        msg.setArticleCount(1);
        List<Article> alist = new ArrayList<Article>();
        Article article = new Article();
        if(ResutlCode.TEXT.getCode().equals(bean.getCode())){
            TextBean text = (TextBean) bean;
            return MessageUtil.textMessageToXml(text.getText());
        }else if(ResutlCode.NEWS.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    NewsBean news = (NewsBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    article.setDescription(news.getArticle());
                    article.setPicUrl(news.getIcon());
                    article.setUrl(news.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }
        }else if(ResutlCode.FLIGHT.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    FlightBean flight = (FlightBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "航班："+flight.getFlight();
                    desc += "\n路线："+flight.getRoute();
                    desc += "\n起飞时间："+flight.getStarttime();
                    desc += "\n到达时间："+flight.getEndtime();
                    desc += "\n状态："+flight.getState();
                    article.setDescription(desc);
                    article.setPicUrl(flight.getIcon());
                    article.setUrl(flight.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.HOTEL.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    HotelBean hotel = (HotelBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "酒店名称："+hotel.getName();
                    desc += "\n价格："+hotel.getPrice();
                    desc += "\n满意度："+hotel.getSatisfaction();
                    desc += "\n数量："+hotel.getCount();
                    article.setDescription(desc);
                    article.setPicUrl(hotel.getIcon());
                    article.setUrl(hotel.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.PRICE.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    PriceBean price = (PriceBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "名称："+price.getName();
                    desc += "\n价格："+price.getPrice();
                    article.setDescription(desc);
                    article.setPicUrl(price.getIcon());
                    article.setUrl(price.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.SOFT.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    SoftBean soft = (SoftBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "软件名称："+soft.getName();
                    desc += "\n下载量："+soft.getCount();
                    article.setDescription(desc);
                    article.setPicUrl(soft.getIcon());
                    article.setUrl(soft.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.TRAIN.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    TrainBean train = (TrainBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "车次："+train.getTrainnum();
                    desc += "\n起始站："+train.getStart();
                    desc += "\n到达站："+train.getTerminal();
                    desc += "\n开车时间："+train.getStarttime();
                    desc += "\n到达时间："+train.getEndtime();
                    article.setDescription(desc);
                    article.setPicUrl(train.getIcon());
                    article.setUrl(train.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.VIDEO.getCode().equals(bean.getCode())){
            ListBean listBean = (ListBean) bean;
            List<ListBaseBean> list = listBean.getList();
            if(CollectionUtil.isNotEmpty(list)){
                for(int i=0; i<list.size() && i<11; i++){
                    String desc = "";
                    VideoBean video = (VideoBean) list.get(i);
                    article = new Article();
                    article.setTitle("");
                    desc += "名称："+video.getName();
                    desc += "\n介绍："+video.getInfo();
                    article.setDescription(desc);
                    article.setPicUrl(video.getIcon());
                    article.setUrl(video.getDetailurl());
                    alist.add(article);
                }
                msg.setArticleCount(list.size()>10?10:list.size());
            }

        }else if(ResutlCode.LINK.getCode().equals(bean.getCode())){
            LinkBean link = (LinkBean) bean;
            article = new Article();
            article.setTitle("点击进入");
            article.setDescription(link.getText());
            article.setUrl(link.getUrl());
            msg.setArticleCount(1);
            alist.add(article);
        }else{
            TextBean text = (TextBean) bean;
            article = new Article();
            article.setTitle("暂不支持该功能["+text.getCode()+"]");
            article.setDescription(text.getText());
            msg.setArticleCount(1);
            alist.add(article);
        }
        if(alist.size()<1){
            article.setTitle("很遗憾");
            article.setDescription("暂无相关数据。");
            alist.add(article);
        }
        msg.setArticles(alist);
        return MessageUtil.newsMessageToXml(msg);
    }


    /**
     * 构建集合返回实体
     * @param jsonObject
     * @param resutlCode
     * @param <T>
     * @return
     */
    private static <T extends ListBaseBean > ListBean createListBean(JSONObject jsonObject,ResutlCode resutlCode){
        ListBean res = new ListBean();
        res.setResult(resutlCode);
        res.setText(jsonObject.getString("text"));
        JSONArray jsonArr = jsonObject.getJSONArray("list");
        T bean = null;
        try {
            bean = (T) resutlCode.getClassz().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i < jsonArr.size(); i++){
            bean = (T) JSONUtil.toBean(jsonArr.getJSONObject(i),bean.getClass());
            res.add(bean);
        }
        return res;
    }


    /**
     * 构建文字返回实体
     * @param jsonObject
     * @param resutlCode
     * @return
     */
    private static BaseRespBean createTextBean(JSONObject jsonObject,ResutlCode resutlCode){
        TextBean bean = new TextBean();
        bean.setResult(resutlCode);
        bean.setText(jsonObject.getString("text"));
        return bean;
    }






}
