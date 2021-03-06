package gl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AlertServlet", urlPatterns = "/AlertServlet")
public class AlertServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String stringDate = dateFormat.format(currentDate);

        String authorMail = request.getParameter("studentMail");
        String reason = request.getParameter("reason");
        String comment = request.getParameter("alertContent");
        String idUniversity = request.getParameter("id_university");

        try {
            DbDao.insertAlert(stringDate, authorMail, reason, comment, idUniversity);
            response.sendRedirect("/");
        } catch (Exception exObj) {
            exObj.printStackTrace();
        } finally {
            DbDao.disconnectDb();
        }
	}

}
