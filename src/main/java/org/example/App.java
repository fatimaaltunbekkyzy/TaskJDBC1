package org.example;

import org.example.models.Employee;
import org.example.models.Job;
import org.example.servise.EmployeeService;
import org.example.servise.JobService;
import org.example.servise.impl.EmployeeServiceImpl;
import org.example.servise.impl.JobServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.createEmployee();

        employeeService.addEmployee(new Employee("Sakina","Altunbek",18,"s@email.com",1));
        employeeService.addEmployee(new Employee("Sofia","Nurlan",20,"s@email.com",2));
        employeeService.addEmployee(new Employee("Albina","Asanov",22,"a@email.com",3));
        employeeService.addEmployee(new Employee("Anara","Alisher",19,"a@email.com",4));
        employeeService.addEmployee(new Employee("Marat","Altunbek",23,"s@email.com",5));
        employeeService.addEmployee(new Employee("Usyf","Altunbek",25,"s@email.com",6));


        employeeService.cleanTable();
        employeeService.dropTable();
        employeeService.updateEmployee(1L,new Employee("Adilet","Egemberdiev",19,"e@email.com",2));
        employeeService.getAllEmployees();
        employeeService.getEmployeeById(2L);
        employeeService.getEmployeeByPosition("Developer");

        JobService jobService = new JobServiceImpl();
        jobService.createJobTable();
        jobService.addJob(new Job("Software Developer","Developer", "Develops software", 3));
        jobService.getJobById(1L);
        jobService.getJobByEmployeeId(1L);
        jobService.sortByExperience("firstName");
        jobService.deleteDescriptionColumn();
    }
}
