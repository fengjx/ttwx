/**
 * 微信js工具类
 * 依赖xml2json.js
 */

/**
 * 将xml字符串转成html
 * @param {} xmlStr
 * @param {} in_time
 * @param {} material_id
 * @param {} type type=edit，则html生成编辑按钮（删除、编辑）,type=select则生成选择预览样式
 * @return {}
 */
function xml2NewsHtml(xmlStr,in_time,material_id,type){
	
	var newsType;
	
	var xml_data = $.xml2json(xmlStr);
				
	var articleCount = xml_data.ArticleCount;
	if(articleCount == "1"){//单图文本
		newsType = 'single';
		html = '<div id="appmsg'+material_id+'">' +
					'<div id="'+material_id+'" class="appmsg">' +
						'<div class="appmsg_content">' +
							'<h4 class="appmsg_title">' +
								'<a target="_blank" href="'+getUrl(xml_data.Articles.item.Url,domain)+'">'+xml_data.Articles.item.Title+'</a>' +
							'</h4>' +
							'<div class="appmsg_info">' +
								'<em class="appmsg_date">'+in_time+'</em>' +
							'</div>' +
							'<div class="appmsg_thumb_wrp">' +
								'<img class="appmsg_thumb" alt="" src="'+xml_data.Articles.item.PicUrl+'">' +
							'</div>' +
							'<p class="appmsg_desc">'+xml_data.Articles.item.Description+'</p>' +
						'</div>';
						
	}else{//多图文本
		newsType = 'multiple';
		var items = xml_data.Articles.item;
		html = '<div id="appmsg'+material_id+'">' +
					'<div id="'+material_id+'" class="appmsg">' +
						'<div class="appmsg_content">' +
							'<div class="appmsg_info">' +
								'<em class="appmsg_date">'+in_time+'</em>' +
							'</div>';
		$.each(items, function(j,item){
			if(j == 0){	//第一张图文
				html += '<div class="cover_appmsg_item">' +
							'<h4 class="appmsg_title"><a target="_blank" href="'+getUrl(item.Url,domain)+'">'+item.Title+'</a></h4>' +
							'<div class="appmsg_thumb_wrp">' +
								'<img class="appmsg_thumb" alt="" src="'+item.PicUrl+'">' +
							'</div>' +
						'</div>';
			}else{
				html += '<div class="appmsg_item">' +
							'<img class="appmsg_thumb" alt="" src="'+item.PicUrl+'">' +
							'<h4 class="appmsg_title"><a target="_blank" href="'+getUrl(item.Url,domain)+'">'+item.Title+'</a></h4>' +
						'</div>';
			}
		});
		html += '</div>';
	}
	if(!type){
		return html;
	}
	if(type ==='edit'){
		html += '<div class="appmsg_opr">' +
									'<ul>' +
										'<li class="appmsg_opr_item grid_item size1of2">' +
											'<a target="_blank" href="'+domain+'/admin/material/'+newsType+'?id='+material_id+'" class="js_edit" data-id="'+material_id+'">' +
												'<i class="icon18_common edit_gray">編輯</i>' +
											'</a>' +
										'</li>'+
										'<li class="appmsg_opr_item grid_item size1of2 no_extra">' +
											'<a href="javascript:void(0);" data-id="'+material_id+'" class="js_del no_extra">' +
												'<i class="icon18_common del_gray">刪除</i>' +
											'</a>' +
										'</li>' +
									'</ul>' +
								'</div>';
		
	}else if(type ==='select'){
		html += '<div class="appmsg_mask"></div>' +
				'<i class="icon_appmsg_selected">已选择</i>';
	}
	html += "</div></div>";
	return html;
}

/**
 * 解析响应消息xml数据
 * @param {} resp_xml
 * @param {} resp_time
 * @return {}
 */
function parseRespxml2html(resp_xml,resp_time){
	var json = $.xml2json(resp_xml);
	var viewHtml = "";		//预览效果HTML
	var msgType = json.MsgType;
	if(msgType == "text"){		//文本消息
		viewHtml = json.Content;
	}else if(msgType == "news"){	//图文消息
		viewHtml = xml2NewsHtml(resp_xml,resp_time);
	}else if(msgType == "image"){	//图片
		viewHtml = '【图片】MediaId：'+json.MediaId;
	}else if(msgType == "voice"){	//语音
		viewHtml = '【語音】MediaId：'+json.MediaId;
	}else if(msgType == "video"){	//视频
		viewHtml = '【視頻】MediaId：'+json.MediaId;
	}else if(msgType == "music"){	//音乐
		viewHtml = '【音樂】MediaId：'+json.MusicURL;
	}
	return viewHtml;
}


/**
 * 解析请求消息xml数据
 * @param {} resp_xml
 * @param {} resp_time
 * @return {}
 */
function parseReqxml2html(req_xml,req_time){
	var json = $.xml2json(req_xml);
	var viewHtml = "";		//预览效果HTML
	var msgType = json.MsgType;
	if(msgType == "text"){		//文本消息
		viewHtml = json.Content;
	}else if(msgType == "location"){	//地理位置
		viewHtml = '【地理位置】'+json.Label+'('+json.Location_Y+','+json.Location_X+')';
	}else if(msgType == "image"){	//图片
		viewHtml = '【圖片】MediaId：'+json.MediaId;
	}else if(msgType == "voice"){	//语音
		viewHtml = '【語音】MediaId：'+json.MediaId;
	}else if(msgType == "video"){	//视频
		viewHtml = '【視頻】MediaId：'+json.MediaId;
	}else if(msgType == "link"){	//链接
		viewHtml = '【鏈接】Url：'+json.Url;
	}else if(msgType == "event"){	//21:15
		var eventType = json.Event;
		if(eventType == 'CLICK'){		//菜单点击
			viewHtml = '【菜單點擊】EventKey：'+json.EventKey;
		}else if(eventType == 'VIEW'){		//菜单跳转
			viewHtml = '【菜單跳轉】EventKey：'+json.EventKey;
		}else if(eventType == 'LOCATION'){	//上报地理位置
			viewHtml = '【上報地理位置】EventKey：'+json.EventKey;
		}else if(eventType == 'SCAN'){	//用户已关注时的事件推送（二维码扫描）
			viewHtml = '【二維碼掃描】EventKey：'+json.EventKey;
		}else if(eventType == 'subscribe'){	//用户关注
			viewHtml = '【用戶訂閱】';
		}else if(eventType == 'unsubscribe'){	//用户关注
			viewHtml = '【取消訂閱】';
		}
	}
	return viewHtml;
}



