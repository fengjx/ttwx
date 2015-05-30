<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<select class="form-control" id="${param.id}" name="${param.name}" style="width: 159px;">
    <c:if test="${param.showAll eq '1'}">
        <option value="" selected="selected"></option>
    </c:if>
    <c:choose>
        <c:when test="${param.app_type eq 'web' }">
            <c:forEach var="a" items="${apps}" varStatus="status">
                <option value="${a.app_url}">${a.name}</option>
            </c:forEach>
        </c:when>
        <c:when test="${param.app_type eq 'restful' }">
            <c:forEach var="a" items="${apps}" varStatus="status">
                <option value="${a.restful_url}">${a.name}</option>
            </c:forEach>
        </c:when>
        <c:when test="${param.app_type eq 'api' }">
            <c:forEach var="a" items="${apps}" varStatus="status">
                <option value="${a.bean_name}">${a.name}</option>
            </c:forEach>
        </c:when>
    </c:choose>
</select>--%>

<c:choose>
    <c:when test="${param.app_type eq 'web' }">
        <div class="form-inline">
            <input id="${param.id}" name="${param.name}" class="form-control" type="text" value="http://" style="width:400px;">
            <a href="#urlModal" data-toggle="modal" class="btn btn-default" role="button">选择链接</a>
        </div>
        <div class="modal" id="urlModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">选择链接地址</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered table-hover text-left">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>链接</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <c:forEach var="a" items="${apps}" varStatus="status">
                                <tr>
                                    <td>${a.name}</td>
                                    <td><a target="_blank" href="${a.app_url}">${a.app_url}</a></td>
                                    <td><a class="btn btn-outline btn-success" onclick="selectUrl('${a.app_url}');" href="javascript:void(0);">选择</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function selectUrl(url) {
                $("#busiapp_url").val(url);
                $('#urlModal').modal('hide')
            }
        </script>
    </c:when>
    <c:when test="${param.app_type eq 'restful' }">
        <div class="form-inline">
            <input id="${param.id}" name="${param.name}" class="form-control" type="text" value="http://" style="width:400px;">
            <a href="#urlModal" data-toggle="modal" class="btn btn-default" role="button">选择链接</a>
        </div>
        <div class="modal" id="urlModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">选择链接地址</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered table-hover text-left">
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>restful api</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <c:forEach var="a" items="${apps}" varStatus="status">
                                <tr>
                                    <td>${a.name}</td>
                                    <td>${a.restful_url}</td>
                                    <td><a class="btn btn-outline btn-success" onclick="selectApi('${a.restful_url}');" href="javascript:void(0);">选择</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function selectApi(restful) {
                $("#busiapp_url").val(restful);
                $('#urlModal').modal('hide')
            }
        </script>
        <div class="form-control input-append dropdown combobox" data-initialize="combobox" id="${params.id}"
             name="${param.name}">
            <input type="text" class="form-control">

            <div class="input-group-btn">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span
                        class="caret"></span></button>
                <ul class="dropdown-menu dropdown-menu-right">
                    <c:forEach var="a" items="${apps}" varStatus="status">
                        <li data-value="${a.restful_url}"><a href="#">${a.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:when>
    <c:when test="${param.app_type eq 'api' }">
        <select class="form-control" id="${param.id}" name="${param.name}" style="width: 159px;">
            <c:forEach var="a" items="${apps}" varStatus="status">
                <option value="${a.bean_name}">${a.name}</option>
            </c:forEach>
        </select>
    </c:when>
</c:choose>







