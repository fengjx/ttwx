<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="myform" tagdir="/WEB-INF/tags/form" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<c:set var="appName" value="${fns:getConst('APP_NAME')}"/>
<c:set var="keywords" value="${fns:getConst('KEYWORDS')}"/>
<c:set var="description" value="${fns:getConst('DESCRIPTION')}"/>
<c:set var="domain" value="${fns:getConst('DOMAIN_PAGE')}"/>
<c:set var="adminPath" value="${fns:getConst('DOMAIN_PAGE')}${fns:getConst('ADMIN_PATH')}"/>
<c:set var="resourceUrl" value="${fns:getConst('RESOURCE_URL')}"/>
<c:set var="staticDomain" value="${fns:getConst('STATIC_DOMAIN')}"/>
<c:set var="adapterPageName" value="${fns:getConst('ADAPTER_PAGE_NAME')}"/>
<c:set var="isDebug" value="${fns:getConst('YES') eq fns:getConst('TEST_FALG')}"/>