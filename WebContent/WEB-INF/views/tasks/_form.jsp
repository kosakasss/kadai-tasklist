<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<label for="deadline">期限</label><br />
<input type="date" name="deadline" value="<fmt:formatDate value='${task.deadline}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="content">タスク内容</label><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<label for="status_flag">タスクの進捗具合</label><br />
<select name="status_flag">
    <option value="0"<c:if test="${task.status_flag == 0}">selected</c:if>>未完了</option>
    <option value="1"<c:if test="${task.status_flag == 1}">selected</c:if>>完了</option>
</select>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>