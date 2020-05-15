package dao;

import dao.DBUtil;
import dao.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    //把一个 project 保存到数据库中
    public void save(Project project) {
        //1.获取数据库连接
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        //2.构造 prepareStatement 拼接 SQL语句
        String sql = "insert into project_table values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, project.getName());
            ps.setString(2, project.getUrl());
            ps.setString(3, project.getDescription());
            ps.setInt(4, project.getStarCnt());
            ps.setInt(5, project.getForkCnt());
            ps.setInt(6, project.getIssueCnt());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            ps.setString(7, simpleDateFormat.format(System.currentTimeMillis()));

            //3.执行 SQL语句，完成数据库插入操作
            int ret = ps.executeUpdate();
            if (ret != 1) {
                System.out.println("数据库执行出错");
            }
            System.out.println("数据库执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, ps, null);
        }
    }

    public List<Project> selectProjectByDate(String date) {
        List<Project> projects = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select name, url, description, starCnt, forkCnt, issuesCnt from project_table where date=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setName(rs.getString("name"));
                project.setUrl(rs.getString("url"));
                project.setDescription(rs.getString("description"));
                project.setStarCnt(rs.getInt("starCnt"));
                project.setForkCnt(rs.getInt("forkCnt"));
                project.setIssueCnt(rs.getInt("issuesCnt"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(con, ps, rs);
        }
        return projects;
    }

    public static void main(String[] args) {
        ProjectDAO projectDao = new ProjectDAO();
        List<Project> projects = projectDao.selectProjectByDate("20200515");
        System.out.println(projects);
    }
}
