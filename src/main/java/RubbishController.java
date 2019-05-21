import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

@WebFilter("/*")
public class RubbishController implements Filter {

    private static final long serialVersionUID = 1;
    private static DaoRubbish daoRubbishInstance = DaoRubbish.getInstance();
    private static final Logger LOGGER = Logger.getLogger(RubbishController.class.getName());
    private String home_page = null;
    private String error_page = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.home_page = "WEB-INF/content/jsp/menu.jsp";
        this.error_page = "WEB-INF/content/jsp/error.jsp";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/update":
                    updateRubbsih(req, resp);
                    filterChain.doFilter(req, resp);
                    return;
                case "/edit":
                    showEditForm(req, resp);
                    filterChain.doFilter(req, resp);
                    return;
                case "/new":
                    showNewForm(req, resp);
                    filterChain.doFilter(req, resp);
                    return;
                case "/insert":
                    insertRubbish(req, resp);
                    filterChain.doFilter(req, resp);
                    return;
                case "/delete":
                    deleteRubbish(req, resp);
                    filterChain.doFilter(req, resp);
                    return;
                default:
                    listRubbish(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher(home_page).forward(req, resp);
        filterChain.doFilter(req, resp);
        return;
    }

    private void updateRubbsih(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String location = req.getParameter("location");

        Rubbish rubbish = new Rubbish(id, name, description, quantity, location);
        daoRubbishInstance.update(rubbish);
        resp.sendRedirect("/list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Rubbish> rubbish = daoRubbishInstance.find(id);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/content/jsp/RubbishForm.jsp");
        rubbish.ifPresent(s -> req.setAttribute("rubbish", s));
        rd.forward(req, resp);
    }

    private void deleteRubbish(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Rubbish rubbish = new Rubbish(id);
        daoRubbishInstance.delete(rubbish);
        resp.sendRedirect("/list");
    }

    private void insertRubbish(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String location = req.getParameter("location");

        Rubbish rubbish = new Rubbish(name, description, quantity, location);
        daoRubbishInstance.save(rubbish);
        resp.sendRedirect("/list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/content/jsp/RubbishForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void listRubbish(HttpServletRequest req, HttpServletResponse resp) {

    }
}
