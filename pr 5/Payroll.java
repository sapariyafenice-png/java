abstract class Employee {
    String name;
    int id;
    Employee(String name,int id)
    {
this.name=name;
this.id=id;
    }
   abstract double monthlySalary();
    
}
class FullTime extends Employee
{
double salary;
FullTime(String name,int id,double salary)
{
    super(name,id);
    this.salary=salary;
}
@Override
double monthlySalary()
{
    return salary;
}
}
class PartTime extends Employee
{
  double hours;
    double rate;

    PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee
{
  double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    double monthlySalary() {
        return stipend;
    }
}

public class Payroll {
    public static void main(String[] args) {

        Employee[] employees = {
            new FullTime("Feni", 101, 40000),
            new PartTime("Rahul", 102, 80, 200),
            new Intern("Priya", 103, 10000),
            new FullTime("Amit", 104, 35000)
        };

        double total = 0;

        for (Employee e : employees) {

            double salary = e.monthlySalary();

            System.out.println(
                "ID: " + e.id +
                ", Name: " + e.name +
                ", Salary: " + salary
            );

            total = total + salary;

            if (e instanceof Intern) {
                System.out.println("Note: This employee is an Intern.");
            }
        }

        System.out.println("--------------------");
        System.out.println("Total Payroll = " + total);
    }
}
