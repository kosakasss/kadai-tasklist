package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //セッションスコープからタスクのIDを取得して該当のIDのタスク一件のみをデータベースから取得
            Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            //フォームの内容を各フィールド上書き
            String content = request.getParameter("content");
            m.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdated_at(currentTime); //作成日時は変える必要ないため

            //データベース更新
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            //セッションスコープ上の不要になったデータ削除
            request.getSession().removeAttribute("task_id");

            //indexへ
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
