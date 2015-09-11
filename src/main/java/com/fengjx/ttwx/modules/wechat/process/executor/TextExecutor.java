
package com.fengjx.ttwx.modules.wechat.process.executor;

import com.fengjx.ttwx.common.plugin.db.Record;
import com.fengjx.ttwx.common.utils.LogUtil;
import com.fengjx.ttwx.modules.api.restful.BaiduMusicServiceApi;
import com.fengjx.ttwx.modules.api.restful.BaiduTranslateServiceApi;
import com.fengjx.ttwx.modules.api.restful.WeatherServiceApi;
import com.fengjx.ttwx.modules.api.restful.YoukuVideoServiceApi;
import com.fengjx.ttwx.modules.api.tuling.client.TulingApiClient;
import com.fengjx.ttwx.modules.api.tuling.vo.req.RequestBean;
import com.fengjx.ttwx.modules.wechat.model.RespMsgAction;
import com.fengjx.ttwx.modules.wechat.process.utils.ExecutorNameUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 文本消息处理器
 *
 * @author fengjx
 * @date 2015-6-24
 */
public class TextExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(TextExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入文本消息处理器fromUserName=" + inMessage.getFromUserName());
        List<Map<String, Object>> keywords = respMsgAction
                .loadKeywordActions(inMessage.getContent(), accountRecord.getStr("sys_user_id"));
        Record actionRecord = matching(inMessage.getContent(), keywords);
        // 没有找到匹配规则
        if (null == actionRecord || actionRecord.isEmpty()) {
            String res = extHandel(inMessage);
            if (StringUtils.isNotBlank(res)) { // 如果有数据则直接返回
                return doAction(res);
            }
        }
        return doAction(actionRecord);
    }

    /**
     * 查找匹配规则
     *
     * @param content
     * @param keywords
     * @return
     */
    private Record matching(String content, List<Map<String, Object>> keywords) {
        if (CollectionUtils.isEmpty(keywords)) {
            return null;
        }
        for (Map<String, Object> action : keywords) {
            String fuzzy = action.get("fuzzy") + "";
            String keyword = action.get("key_word") + "";
            // 完全匹配
            if (RespMsgAction.FUZZY_EXACT.equals(fuzzy) && content.equals(keyword)) {
                return new Record(action);
            }
            // 包含
            if (RespMsgAction.FUZZY_CONTAIN.equals(fuzzy) && content.contains(keyword)) {
                return new Record(action);
            }
            // 关键字开始
            if (RespMsgAction.FUZZY_START.equals(fuzzy) && content.startsWith(keyword)) {
                return new Record(action);
            }
            // 关键字结束
            if (RespMsgAction.FUZZY_END.equals(fuzzy) && content.endsWith(keyword)) {
                return new Record(action);
            }
        }
        return null;
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_TEXT, null);
    }

    public String extHandel(WxMpXmlMessage inMessage) {
        String respMessage = null;
        String fromUserName = inMessage.getFromUserName();
        String toUserName = inMessage.getToUserName();
        String content = inMessage.getContent();

        WxMpXmlOutTextMessage text = WxMpXmlOutMessage.TEXT().fromUser(toUserName)
                .toUser("fromUserName").build();
        // 接收用户发送的文本消息内容
        // 创建图文消息
        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(fromUserName).toUser(toUserName)
                .build();
        if (content.startsWith("歌曲")) {
            // 文本消息内容
            String respContent = null;
            // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉
            String keyWord = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?", "").trim();
            // 如果歌曲名称为空
            if ("".equals(keyWord)) {
                respContent = BaiduMusicServiceApi.getMusicUsage();
            } else {
                String[] kwArr = keyWord.split("@");
                // 歌曲名称
                String musicTitle = kwArr[0];
                // 演唱者默认为空
                String musicAuthor = "";
                if (2 == kwArr.length) {
                    musicAuthor = kwArr[1];
                }
                // 搜索音乐
                WxMpXmlOutMusicMessage music = BaiduMusicServiceApi.searchMusic(musicTitle,
                        musicAuthor);
                // 未搜索到音乐
                if (null == music) {
                    respContent = "对不起，没有找到你想听的歌曲<" + musicTitle + ">。";
                } else {
                    // 音乐消息
                    respMessage = music.toXml();
                }
                // 没有搜索结果
                if (null != respContent) {
                    text.setContent(respContent);
                    respMessage = text.toXml();
                }
            }
        }
        // 翻译
        else if (content.startsWith("翻译")) {
            String keyWord = content.replaceAll("^翻译", "").trim();
            LogUtil.info(LOG, "翻译：" + keyWord);
            if ("".equals(keyWord)) {
                // 返回使用指南
                text.setContent(BaiduTranslateServiceApi.getTranslateUsage());
            } else {
                text.setContent(BaiduTranslateServiceApi.translate(keyWord));
            }
            respMessage = text.toXml();
        }
        // 搜索视频（优酷）
        else if (content.startsWith("视频")) {
            String keyWord = content.replaceAll("^视频", "").trim();
            LogUtil.info(LOG, "搜索视频：" + keyWord);
            String textContent = null;
            if ("".equals(keyWord)) {
                textContent = YoukuVideoServiceApi.getVideoUsage();
            } else {
                WxMpXmlOutNewsMessage news = YoukuVideoServiceApi.searchVideo(keyWord);
                if (null != news && !CollectionUtils.isEmpty(news.getArticles())) {
                    respMessage = news.toXml();
                } else {
                    textContent = "没有搜索结果...";
                }
            }
            // 没有搜索结果
            if (null != textContent) {
                text.setContent(textContent);
                respMessage = text.toXml();
            }
        }
        // 天气预报
        else if (content.startsWith("天气")) {
            String keyWord = content.replaceAll("^天气", "").trim();
            LogUtil.info(LOG, "天气查询：" + keyWord);
            if ("".equals(keyWord)) {
                // 返回使用指南
                text.setContent(WeatherServiceApi.getWeatherUsage());
            } else {
                text.setContent(WeatherServiceApi.queryhWeather(keyWord));
            }
            respMessage = text.toXml();
        } else if (content.startsWith("留言")) {
            text.setContent("<a href=\"http://blog.fengjx.com/?page_id=31\">留言</a>");
            respMessage = text.toXml();
        } else {
            // 没有匹配规则的消息，交给图灵机器人处理
            RequestBean req = new RequestBean();
            req.setInfo(content);
            req.setUserid(fromUserName);
            return TulingApiClient.call2WechatMsg(req);
        }
        return respMessage;
    }
}
