package pe.edu.utp.hrwebprofile.controllers;

import pe.edu.utp.hrwebprofile.models.HrService;
import pe.edu.utp.hrwebprofile.models.Region;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "RegionsController", urlPatterns = "/regions")
public class RegionsController extends javax.servlet.http.HttpServlet {
    HrService service;
    String url;

    public RegionsController() {
        super();
        service = new HrService();
        url = "";
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response)
            throws
            javax.servlet.ServletException,
            IOException {

        processRequest("POST", request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws
            javax.servlet.ServletException,
            IOException {

        processRequest("GET", request,response);
    }

    private void processRequest(String action,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws
            javax.servlet.ServletException,
            IOException {

        if (action.equals("GET")){
            List<Region> regions = service.findAllRegions();
            request.setAttribute("regions",regions);
            url = "listRegions.jsp";
        }

        request.getRequestDispatcher(url).forward(request,response);
    }
}
