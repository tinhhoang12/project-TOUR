/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CityDAO;
import DAO.HotelDAO;
import DAO.TourDAO;
import entities.Tour;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author 84971
 */
@WebServlet(name = "LoadMore", urlPatterns = {"/loadMore"})
public class LoadMore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int amount = Integer.parseInt(request.getParameter("exist"));       
         TourDAO dao = new TourDAO();
          HotelDAO hDAO = new HotelDAO();
          CityDAO cDAO = new CityDAO();
            List<Tour> tour = dao.getNext6Tour(amount);
        PrintWriter out = response.getWriter();
        for (Tour t : tour) {
            out.print(" <div class=\"product col-lg-4 col-md-6 my-3\">\n"
                    + "                        <a href=\"TourDetail?id="+t.getTour_ID()+"}\">\n"
                    + "                            <div class=\"popular-card\">\n"
                    + "                                <img src=\""+t.getImg()+"\" alt=\"Image description\">\n"
                    + "\n"
                    + "                                <div class=\"card-content\">\n"
                    + "\n"
                    + "                                    <div class=\"card-rating\">\n"
                    + "                                        <ion-icon name=\"star\"></ion-icon>\n"
                    + "                                        <ion-icon name=\"star\"></ion-icon>\n"
                    + "                                        <ion-icon name=\"star\"></ion-icon>\n"
                    + "                                        <ion-icon name=\"star\"></ion-icon>\n"
                    + "                                        <ion-icon name=\"star\"></ion-icon>\n"
                    + "                                    </div>\n"
                    + "                                    <p class=\"card-subtitle\">\n"
                    + "                                        <a href=\"TourDetail?id="+t.getTour_ID()+"\">"+cDAO.getCityByID(hDAO.getHotelByID(t.getHotel_ID()).getCityID()).getCity_Name()+"</a>\n"
                    + "                                    </p>\n"
                    + "                                    <h3 class=\"h3 card-title\">         \n"
                    + "                                        <a href=\"TourDetail?id="+t.getTour_ID()+"\">Hotel:"+hDAO.getHotelByID(t.getHotel_ID()).getHotel_Name()+" </a>\n"
                    + "                                    </h3>\n"
                    + "                                    <h4 class=\"h3 card-title\">\n"
                    + "                                        <a href=\"TourDetail?id="+t.getTour_ID()+"\" style=\"color: red\">"+t.getTotalPrice()+"VNĐ</a>\n"
                    + "                                    </h4>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </a>\n"
                    + "                    </div>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
