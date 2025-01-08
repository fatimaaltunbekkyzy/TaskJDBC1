package org.example.dao.Impl;

import org.example.JDConnection;
import org.example.dao.JobDao;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    @Override
    public void createJobTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Jobs (" +
                "id SERIAL PRIMARY KEY, " +
                "position VARCHAR, " +
                "profession VARCHAR, " +
                "description VARCHAR, " +
                "experience VARCHAR" +
                ")";
        try (Statement statement = JDConnection.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    @Override
    public void addJob(Job job) {
String sql= "insert into Jobs values(?,?,?,?)";
    try (Connection connection = JDConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, job.getPosition());
        statement.setString(2, job.getProfession());
        statement.setString(3, job.getDescription());
        statement.setInt   (4, job.getExperience());
        statement.executeUpdate();

    } catch (SQLException e) {
        System.out.println("Ошибка при добавлении работы: " + e.getMessage());
}
    }

    @Override
    public Job getJobById(Long jobId) {
Job job = null;
    String sql = "SELECT * FROM jobs WHERE id = ?";
    try (Connection connection = JDConnection.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setLong(1, jobId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
//        String sql = "SELECT * FROM jobs ORDER BY experience " + ascOrDesc;
//        List<Job> job = new ArrayList<>();
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
//             ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//               job.setId (resultSet.getLong("id"));
//                String position = resultSet.getString("position");
//                String profession = resultSet.getString("profession");
//                String description = resultSet.getString("description");
//                String experience = resultSet.getString("experience");
//
//                Job job = new Job(position, profession, description, experience);
//                job.setId(id);
//                jobs.add(job);
//            }
//
//        } catch (SQLException e) {
//            System.out.println( e.getMessage());
//        }
//
        return null;
    }

   @Override
    public Job getJobByEmployeeId(Long employeeId) {
       String sql = "SELECT j.* FROM jobs j " +
               "JOIN employees e ON e.job_id = j.id " +
               "WHERE e.id = ?";

       Job job = null;

       try (Connection connection = JDConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
           statement.setLong(1, employeeId);

           try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                   String position = resultSet.getString("position");
                   String profession = resultSet.getString("profession");
                   String description = resultSet.getString("description");
                   String experience = resultSet.getString("experience");

                   job = new Job(position, profession, description, 1);
               }
           }

       } catch (SQLException e) {
           System.out.println("Error while fetching job by employee ID: " + e.getMessage());
       }

       return job;
   }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "ALTER TABLE jobs DROP COLUMN description";

        try (Connection connection = JDConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            System.out.println("description' successFull.");

        } catch (SQLException e) {
            System.out.println("Ошибка при удалении колонки 'description': " + e.getMessage());
        }
}}
