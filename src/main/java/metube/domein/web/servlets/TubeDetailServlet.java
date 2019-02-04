package metube.domein.web.servlets;

import metube.domein.models.view.TubeDetailsViewModel;
import metube.domein.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class TubeDetailServlet extends HttpServlet {

    private final TubeService tubeService;

   private final ModelMapper modelMapper;

    @Inject
    public TubeDetailServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = URLDecoder
                .decode(req.getQueryString().split("=")[1],"UTF-8");

        req.setAttribute("tubeDetailsViewModel",this.modelMapper
                .map(this.tubeService.findTubeByName(name), TubeDetailsViewModel.class));

        req.getRequestDispatcher("/jsps/details-tube.jsp")
                .forward(req,resp);
    }
}
