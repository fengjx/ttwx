/**
 * 字典管理
 */

var editModal;
var formValid;
var dataForm;
var $table;

$(function () {

    editModal = $('#editModal').modal({
        show: false
    });

    $table = $('#data-table').jqGrid({
        url: adminPath + '/sys/dict/pageList',
        toolbar: [true, "top"],
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'dict_value',
            label: '字典值',
            sortable: false,
            align: 'left'
        }, {
            name: 'dict_name',
            label: '字典名称',
            align: 'left'
        }, {
            name: 'dict_desc',
            label: '字典描述',
            align: 'left'
        }, {
            name: 'group_code',
            label: '字典组',
            align: 'left'
        }, {
            name: 'group_name',
            label: '组名称',
            align: 'left',
        }, {
            name: 'order_num',
            label: '排序',
            width: 40,
            align: 'left'
        }, {
            name: 'is_valid',
            label: '是否启用',
            width: 70,
            align: 'left'
        }, {
            name: 'in_time',
            label: '接入时间',
            align: 'left',
        }, {
            name: 'op',
            label: '操作',
            align: 'center',
            formatter: function (value, opt, row) {
                var html = '<a class="btn btn-minier btn-success" onclick="append(\'' + row.id + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-plus"></i></a>';
                html += '<a class="btn btn-minier btn-info" onclick="updateData(\'' + row.id + '\')" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
                html += '<a class="btn btn-minier btn-danger" onclick="deleteData(\'' + row.id + '\',\'' + row.dict_name + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-remove"></i></a>';
                return html;
            }
        }],
        serializeGridData: function (postData) {
            postData = $.extend(postData,{
                "group_code": $('#toolbar input[name="qry_group_code"]').val(),
                "dict_desc": $('#toolbar input[name="qry_dict_desc"]').val()
            });
            return postData;
        }
    });

    $table.jqGrid('navGrid', '#tablePager', {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true,
        view: false,
        position: "left",
        cloneToTop: true
    }).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-remove",
            title: "remove",
            caption: "",
            position: "first",
            onClickButton: function () {
                deleteData();
            }
        }
    ).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-edit",
            title: "edit",
            caption: "",
            position: "first",
            onClickButton: function () {
                updateData();
            }
        }
    ).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-plus",
            title: "add",
            caption: "",
            position: "first",
            onClickButton: function () {
                append();
            }
        }
    );

    formValid = $.scojs_valid('#form-dict', {
        rules: {
            dict_value: ['not_empty', {'max_length': 20}],
            dict_name: ['not_empty'],
            group_code: ['not_empty']
        },
        messages: {
            dict_value: {
                not_empty: "请输入字典值",
                max_length: "字典值不能超过20个字符"
            },
            dict_name: {
                not_empty: "请输入字典名称"
            },
            group_code: {
                not_empty: "请输入字典组标识"
            }
        },
        wrapper: '.control-group'
    });

    dataForm = $("#form-dict").submit(function () {
        $(this).ajaxSubmit({
            url: adminPath + "/sys/dict/save",
            dataType: 'json',
            beforeSubmit: function () {
                return formValid.validate();
            },
            success: function (res) {
                if (res && "1" == res.code) {
                    editModal.modal('hide');
                    app.ok(res.msg);
                    $table.trigger("reloadGrid");
                } else {
                    app.error(res.msg);
                }
            }
        });
        return false;
    });
});

function searchDatagrid() {
    $table.trigger("reloadGrid");
}

function clearDatagrid() {
    $('#toolbar input').val('');
    $table.trigger("reloadGrid");
}

/**
 * 添加
 * @param id
 */
function append(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
    }
    if(!id){
        edit();
    }else{
        var row = $table.jqGrid('getRowData', id);
        edit({
            group_code:row.group_code,
            group_name:row.group_name
        });
    }
}

/**
 * 编辑（添加/修改）
 * @param data
 */
function edit(data) {
    dataForm.find("#id").val('');
    dataForm.clearForm();
    if (data) {
        $("#form-dict").autofill(data);
    }
    editModal.modal('show');
}

/**
 * 修改
 * @param id
 * @returns {boolean}
 */
function updateData(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要编辑的数据");
            return false;
        }
    }
    var row = $table.jqGrid('getRowData', id);
    edit(row);
}

/**
 * 删除
 * @param id
 * @param name
 * @returns {boolean}
 */
function deleteData(id, name) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要删除的数据");
            return false;
        }
        name = $table.jqGrid('getRowData', id).dict_name;
    }
    app.confirmModal("你要删除字典【" + name + "】吗？", function () {
        $.ajax({
            url: adminPath + '/sys/dict/delete',
            data: 'id=' + id,
            cache: false,
            dataType: "json",
            success: function (res) {
                if (res && '1' === res.code) {
                    app.ok('刪除成功');
                    $table.trigger("reloadGrid");
                } else {
                    app.error(res.msg ? res.msg : '刪除失败');
                }
            }
        });
    });
}


/**
 * 预览
 * @param {} id
 * @return {Boolean}
 */
function view(index) {
    $table.bootstrapTable('uncheckAll');
    $table.bootstrapTable('check', index);
    var row = $table.bootstrapTable('getSelections')[0];

    var viewHtml = "";		//预览效果HTML
    if (row.action_type == 'material') {//数据源从素材读取
        var json = $.xml2json(row.xml_data);
        var msgType = json.MsgType;
        if (msgType == "text") {		//文本消息
            viewHtml = json.Content;
        } else if (msgType == "news") {	//图文消息
            viewHtml = xml2NewsHtml(row.xml_data, row.in_time, row.material_id);	//(wechat.js)
        }
    } else if (row.action_type == 'api') {
        if (row.app_name) {
            viewHtml = "从接口【" + row.app_name + "】中获得响应数据";
        } else {
            viewHtml = "配置的接口已经失效，请重新配置";
        }
    }
    app.alertModal(viewHtml, {
        title: "用户发送文字消息【" + row.key_word + "】将收到以下内容",
        height: "auto",
        width: 300
    });
}
