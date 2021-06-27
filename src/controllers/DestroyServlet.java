package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DestroyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //セッションスコープからタスクのIDを取得して該当のIDのタスク一件のみをデータベースから取得
            Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除しました");
            em.close();

            //セッションスコープ上の不要になったデータ削除
            request.getSession().removeAttribute("task_id");

            //indexへ
            response.sendRedirect(request.getContextPath() + "/index");

        }
    }

}
