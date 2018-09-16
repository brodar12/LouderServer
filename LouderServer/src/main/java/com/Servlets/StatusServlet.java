package com.Servlets;

import com.Databases.DownloadStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihai on 9/12/2018.
 */
public class StatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Logger logg = Logger.getLogger(this.getClass().getName());

             if (req.getParameter("macaddres") != "" && req.getParameter("ipaddres") != ""){
                 logg.log(Level.INFO, "Log get valid mac and ip:" + req.getParameter("ipaddres")+" mac:"+req.getParameter("macaddres"));

                 resp.setContentType("text/html");

                 PrintWriter out = resp.getWriter();
                /* String title = "Auto Refresh Header Setting";
                 String docType =
                         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";*/

                 out.println(
                         "Client_bot.exe\n" +
                                 "45544554\n"
                        /* "<filename>Client_bot.exe</filename>\n" +
                         "<filesize>45544554</filesize>\n"*/
                 );
             }

           // resp.sendRedirect("https://www.google.com");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DownloadStatus downloadStatus= new DownloadStatus();

        try {

            Logger logg = Logger.getLogger(this.getClass().getName());

            if (req.getParameter("status").equals("sendfiles")) {
                logg.log(Level.INFO, "Log first validation checkbox status:" + req.getParameter("status"));

            }
            else if(req.getParameter("status").equals("sendforallfile")){
                logg.log(Level.INFO, "Log first validation checkbox status1:" + req.getParameter("status"));

            }
            else if (req.getParameter("macaddress") != "null") {
                logg.log(Level.INFO, "Execute update of download status:" + req.getParameter("macaddress"));
                downloadStatus.update_download_action(req.getParameter("macaddress"),true);
            }


            resp.sendRedirect("http://localhost:8080/LouderServer/walletstatistics.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
