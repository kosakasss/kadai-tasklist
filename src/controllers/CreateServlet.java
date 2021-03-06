package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
import utils.DBUtil;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Task m = new Task();

            Date deadline = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("deadline");
            if(rd_str != null && !rd_str.equals("")) {
                deadline = Date.valueOf(request.getParameter("deadline"));
            }
            m.setDeadline(deadline);

            String content = request.getParameter("content");
            m.setContent(content);

            m.setStatus_flag(Integer.parseInt(request.getParameter("status_flag")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            //バリデーションを実行してエラーがあったらら新規登録（new）に戻る
            List<String> errors = TaskValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                //フォームに初期値を設定、エラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
                rd.forward(request, response);

            } else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました");
                em.close();

                response.sendRedirect(request.getContextPath() + "/index");
            }

        }
     }

}
