import id.ac.polban.employee.model.*;
import id.ac.polban.employee.service.*;

public class Main {
    public static void main(String[] args) {
        Department it = new Department("IT");
        EmployeeType fulltime = new EmployeeType("Fulltime");

        Employee emp1 = new Employee(1, "Budi", it, fulltime, 5000000);
        Employee emp2 = new Employee(2, "Siti", it, fulltime, 6000000);

        EmployeeService service = new EmployeeService();

        service.addEmployee(emp1);
        service.addEmployee(emp2);

        service.raiseSalary(1, 10);

        System.out.println("Total Employee: " + Employee.getTotalEmployee());
        System.out.println("Salary Budi setelah naik: " + emp1.getSalary());
    }
}
