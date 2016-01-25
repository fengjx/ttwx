<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<div id="side-menu" class="sidebar responsive">
  <ul class="nav nav-sidebar nav-list">
    <c:forEach var="m1" items="${menus}">
      <c:if test="${'1' eq m1.is_show}">
        <c:set var="isLeef" value="${m1.isLeef}"></c:set>
        <li>
          <a href="<c:choose><c:when test="${not empty m1.url}">${domain}${m1.url}</c:when><c:otherwise>javascript:void (0);</c:otherwise></c:choose>">
            <i class="${m1.icon}"></i>
            <span class="menu-text"> ${m1.name} </span>
            <c:if test="${isShowNext}">
              <b class="arrow icon-angle-down"></b>
            </c:if>
          </a>
          <b class="arrow"></b>
          <c:if test="${!isLeef}">
            <ul class="submenu">
              <c:forEach var="m2" items="${menus}">
                <c:if test="${m2.parent_id eq m1.id && '1' eq m2.is_show}">
                  <li>
                    <a href="${domain}${m2.url}">
                      <i class="menu-icon fa fa-caret-right"></i>
                        ${m2.name}
                    </a>
                    <b class="arrow"></b>
                  </li>
                </c:if>
              </c:forEach>
            </ul>
          </c:if>
        </li>
      </c:if>
    </c:forEach>
  </ul>
  <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
    <i class="ace-icon icon-arrow-left"></i>
  </div>
</div>