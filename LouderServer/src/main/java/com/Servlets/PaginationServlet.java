package com.Servlets;

import com.DisplayContent.HardwarePagination;
import com.DisplayContent.WalletstatisticsPagination;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihai on 2/21/2018.
 */
public class PaginationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if(req.getParameter("display").equals("wallet")) {

                if (req.getParameter("action").equals("next")) {
                    WalletstatisticsPagination.set_next_page();
                } else if (req.getParameter("action").equals("prev")) {
                    WalletstatisticsPagination.set_previous_page();
                } else if (!req.getParameter("curent_page").equals("empty")) {
                    try {
                        WalletstatisticsPagination.set_curent_page(Integer.parseInt(req.getParameter("curent_page")));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            resp.sendRedirect("http://localhost:8080/LouderServer/walletstatistics.jsp");
        }
        else if(req.getParameter("display").equals("modal")){
            resp.sendRedirect("http://localhost:8080/LouderServer/walletstatistics.jsp?modalaces=true&mac="+ req.getParameter("macaddress"));
        }
        else if(req.getParameter("display").equals("walletmodal")){
            resp.sendRedirect("http://localhost:8080/LouderServer/walletstatistics.jsp?modalaces_wallet=true&mac="+ req.getParameter("macaddress")+"&wallettype="+req.getParameter("walletyp"));
        }
        else if(req.getParameter("display").equals("hardware")){

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
            resp.sendRedirect("http://localhost:8080/LouderServer/hardwareinfo.jsp");

        }


    }


}
