<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>タスク管理</h2>
        <table id="task_list">
            <tbody>
                <tr>
                    <th>番号</th>
                    <th>タスク内容</th>
                    <th>期限</th>
                    <th>状況</th>
                    <th>詳細画面へ</th>
                </tr>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td><c:out value="${task.id}" /></td>
                        <td><c:out value="${task.content}" /></td>
                        <td><fmt:formatDate value='${task.deadline}' pattern='yyyy-MM-dd' /></td>
                        <td>
                            <c:choose>
                                <c:when test="${task.status_flag == 0}">
                                    未完了
                                </c:when>
                                <c:otherwise>
                                    完了
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/show?id=${task.id}">詳細画面</a></td>
                    </tr>
               </c:forEach>
            </tbody>
        </table>


        <div id="pagination">
                (全${tasks_count} 件)<br />
                <c:forEach var="i" begin="1" end="${((tasks_count - 1) / 10) + 1}" step="1">
                    <c:choose>
                        <c:when test="${i == page}">
                            <c:out value="${i}" />&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}" /></a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>

            <p><a href="${pageContext.request.contextPath}/new">新規タスク登録</a></p>

    </c:param>
</c:import>