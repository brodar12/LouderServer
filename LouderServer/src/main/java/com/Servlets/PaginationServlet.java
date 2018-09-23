package com.Servlets;

import com.DisplayContent.HardwarePagination;
import com.InitializeResources.InitResources;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 2/21/2018.
 */
public class PaginationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if(req.getParameter("display").equals("downloadstat")) {

            if (req.getParameter("action").equals("next")) {

                HardwarePagination.set_next_page();

            } else if (req.getParameter("action").equals("prev")) {

                HardwarePagination.set_previous_page();

            } else if (!req.getParameter("curent_page").equals("empty")) {
                try {

                    HardwarePagination.set_curent_page(Integer.parseInt(req.getParameter("curent_page")));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            resp.sendRedirect("http://"+ InitResources.getInstance().getHostname()+":"+InitResources.getInstance().getPort()+"/LouderServer/basic_admin_panel.jsp");
        }
        else if(req.getParameter("display").equals("modal")){
            resp.sendRedirect("http://"+InitResources.getInstance().getHostname()+":"+InitResources.getInstance().getPort()+"/LouderServer/basic_admin_panel.jsp?modalaces=true&mac="+ req.getParameter("macaddress"));
        }
        else if(req.getParameter("display").equals("modalpathfile")){
            resp.sendRedirect("http://"+InitResources.getInstance().getHostname()+":"+InitResources.getInstance().getPort()+"/LouderServer/basic_admin_panel.jsp?modalaces1=true&mac=");
        }


    }


}
