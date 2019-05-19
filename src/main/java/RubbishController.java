import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
        this.home_page = "WEB-INF/content/jsp/home.jsp";
        this.error_page = "WEB-INF/content/jsp/error.jsp";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/menu.jsp":
                    req.getRequestDispatcher("WEB-INF/content/jsp/menu.jsp").forward(req, resp);
                    filterChain.doFilter(req, resp);
                    break;
                case "/new":
                    showNewForm(req, resp);
                    filterChain.doFilter(req, resp);
                    break;
                case "/insert":
                    insertRubbish(req, resp);
                    filterChain.doFilter(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher(home_page).forward(req, resp);
        filterChain.doFilter(req, resp);
    }

    private void insertRubbish(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/content/jsp/RubbishForm.jsp");
        dispatcher.forward(req, resp);
    }

}
