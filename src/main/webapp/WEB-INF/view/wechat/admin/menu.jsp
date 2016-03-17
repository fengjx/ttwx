<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="admin"/>
    <title>菜单管理</title>
    <link href="${resourceUrl}/css/menu.css?v=2014111501" rel="stylesheet" type="text/css"/>
    <link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li>微信管理</li>
            <li class="active">菜单管理</li>
        </ol>
    </div>
    <div class="page-content">
        <div class="row" >
            <h4>可创建最多3个一级菜单，每个一级菜单下可创建最多5个二级菜单。编辑中的菜的那不会马上被用户看到，请放心调试。</h4>
        </div>
        <div class="row" style="border:1px solid #ddd;min-height: 600px;">
            <div class="col-md-3 nopadding" style="border-right:1px solid #ddd;min-height: 600px;">
                <div style="text-align: left;">
                    <button type="button" onclick="append(1)" class="btn btn-default btn-xs sorted" title="添加">
                        <i class="glyphicon glyphicon-plus"></i>
                    </button>
                    <button type="button" onclick="updatedMenu()" class="btn btn-default btn-xs sorted" title="修改">
                        <i class="glyphicon glyphicon-edit"></i>
                    </button>
                    <button type="button" onclick="removeMenu()" class="btn btn-default btn-xs sorted" title="删除">
                        <i class="glyphicon glyphicon-trash"></i>
                    </button>
                    <button type="button" onclick="release()" class="btn btn-default btn-xs sorted" title="发布">
                        <i class="glyphicon glyphicon-phone"></i>
                    </button>
                    <button type="button" onclick="sort();" class="btn btn-default btn-xs sorted" title="排序">
                        <i class="glyphicon glyphicon-align-justify"></i>
                    </button>
                    <button type="button" onclick="saveSort();" class="btn btn-success btn-xs hide sort" title="完成排序">
                        完成
                    </button>
                    <button type="button" onclick="cancelSort();" class="btn btn-default btn-xs hide sort" title="完成排序">
                        取消
                    </button>
                </div>
                <div class="dd" id="nestable">
                    <ol id="menu_tree" class="dd-list">
                    </ol>
                </div>

            </div>
            <div class="col-md-9">
                <div id="action_setting">
                    <div class="action_content default jsMain" id="action_none" style="display: block;">
                        <p class="action_tips">你可以先添加一个菜单，然后开始为其设置响应动作</p>
                    </div>
                    <div class="action_content init jsMain" style="display: none;" id="action_index">
                        <p class="action_tips">请选择订阅者点击菜单后，公众号做出的相应动作</p>
                        <a href="javascript:void(0);" id="sendMsg" onclick="showActionContent('action_edit');">
                            <i class="icon_menu_action send"></i>
                            <strong>发送信息</strong></a>
                        <a href="javascript:void(0);" id="goPage" onclick="showActionContent('action_url');">
                            <i class="icon_menu_action url"></i>
                            <strong>跳转到页面</strong>
                        </a>
                    </div>

                    <div class="action_content send jsMain" id="action_edit"  style="display: none;">
                        <!-- 菜单点击消息动作 -->
                        <jsp:include page="/WEB-INF/view/wechat/admin/inc_action.jsp">
                            <jsp:param name="req_type" value="event"/>
                            <jsp:param name="event_type" value="CLICK"/>
                        </jsp:include>
                    </div>

                    <div class="action_content url jsMain" id="action_url" style="display: none;">
                        <%--<p class="action_tips">订阅者点击该子菜单会跳到以下链接</p>--%>
                        <p class="action_tips">订阅者点击该子菜单会跳转到以下链接</p>
                        <div class="frm_control_group">
                            <table>
                                <tr>
                                    <td>
                                        链接地址：
                                        <sys:extapp id="busiapp_url" name="busiapp_url" appType="web" allowInput="true" />
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tool_bar">
                            <button class="btn btn-default btn-sm" onclick="showActionContent('action_index')">返回</button>
                            <button class="btn btn-primary btn-sm" onclick="submitMsgActionForm('url');">保存</button>
                        </div>
                    </div>


                    <div class="action_content sended jsMain" id="view" style="display: none;">
                        <div style="float: left; width: 100%">
                            <div class="action_tips" style="float: left;">
                                订阅者点击该子菜单会受到以下信息
                            </div>
                            <div style="float:right;">
                                <button class="btn btn-info btn-sm" onclick="updateMsgView();" type="button">修&nbsp;&nbsp;改</button>
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="msg_wrp" id="viewDiv">
                            <!-- js加载预览效果 -->
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>


<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/menu.js?v=2015061701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
