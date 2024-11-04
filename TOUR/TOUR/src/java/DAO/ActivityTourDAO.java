/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Activiti;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MienTrungComputer
 */
public class ActivityTourDAO extends MyDAO {

    public List<Activiti> loadAllActivityTourByTourID(String tourID) {
        List<Activiti> list = new ArrayList<>();
        String xSql = "SELECT [Tour_ID], c.[Activity_ID], c.[ActivityName], [landscape_Address], [time], t.[Is Active]\n"
                + "                         FROM [dbo].[TourActivity] t join [dbo].[Activity] c  on c.[Activity_ID] = t.[Activity_ID] \n"
                + "              			  Where [Tour_ID] = ?  ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, tourID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Activiti t = new Activiti();
                list.add(new Activiti(rs.getInt("Activity_ID"),
                        rs.getInt("Tour_ID"),
                        rs.getString("landscape_Address"),
                        rs.getString("ActivityName"),
                        rs.getString("time"),
                        rs.getBoolean("Is Active")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Activiti> loadAllActivity() {
        List<Activiti> list = new ArrayList<>();
        String xSql = "SELECT c.[Activity_ID], c.[ActivityName], [landscape_Address], [time]\n"
                + "                             FROM [dbo].[Activity] c\n"
                + "			        ORDER BY c.[Activity_ID] ASC";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Activiti t = new Activiti();
                list.add(new Activiti(rs.getInt("Activity_ID"),
                        rs.getString("landscape_Address"),
                        rs.getString("ActivityName"),
                        rs.getString("time")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void getTourActivity(int idTour, String activityID){
        String xSql = "INSERT INTO [dbo].[TourActivity]\n"
                + "           ([Tour_ID]\n"
                + "           ,[Activity_ID]\n"
                + "          )\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, idTour);
            ps.setString(2, activityID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Activiti loadTimeActivityTourByID(int tourID, String activityID) {
        Activiti list = new Activiti();
        String xSql = "SELECT [Tour_ID]\n"
                + "      ,[Activity_ID]\n"
                + "      ,[Time]\n"
                + "      ,[Is Active]\n"
                + "                         FROM [dbo].[TourActivity]\n"
                + "              			  Where [Tour_ID] = ? And [Activity_ID] = ?   ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, tourID);
            ps.setString(2, activityID);
            rs = ps.executeQuery();

            while (rs.next()) {
               
                list = (new Activiti(rs.getInt("Activity_ID"),
                        rs.getInt("Tour_ID"),
                        rs.getString("time")
                ));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void deleteTourActivity(int tourID) {
        String sql = "DELETE FROM [dbo].[TourActivity]\n"
                + "WHERE [Tour_ID] = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tourID);
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getID_Activity() {
        int id = 0;
        String xSql = "SELECT TOP 1 * FROM　Activity a ORDER BY a.Activity_ID DESC";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Activity_ID");
            }
        } catch (Exception e) {
        }
        return id;
    }
    
    public void creatTourActivity(int idTour, String activityID){
        String xSql = "INSERT INTO [dbo].[TourActivity]\n"
                + "           ([Tour_ID]\n"
                + "           ,[Activity_ID]\n"
                + "          )\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, idTour);
            ps.setString(2, activityID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertTourActivity(int id_tour, int id_activity) {
        //Activiti act = new Activiti();
        String xSql = "INSERT INTO [dbo].[TourActivity]\n"
                + "           ([Tour_ID]\n"
                + "           ,[Activity_ID]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id_tour);
            ps.setInt(2, id_activity);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateActivity(int tourId, int activityId, boolean isActive) {
        String sql = "update TourActivity set [Is Active] = ? where Tour_ID = ? and Activity_ID = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, isActive);
            ps.setInt(2, tourId);
            ps.setInt(3, activityId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ActivityTourDAO dao = new ActivityTourDAO();
//       Activiti list = dao.insertTourActivity(new Activiti(1, 0, "Bờ Biển", "Hóng Gió", "17:00:00"), dao.getID_Activity());
        System.out.println(dao.loadAllActivityTourByTourID("1"));
//        int id = dao.getID_Activity();

    }
}
