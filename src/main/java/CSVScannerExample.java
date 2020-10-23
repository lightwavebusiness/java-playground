import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CSVScannerExample {
        static List<Employee> empList;
    
	public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            empList = getEmployees();
            try {
                boolean run = true;
                while (run) {
                    System.out.println(displayMenu(1));
                    char entry = scanner.nextLine().toLowerCase().charAt(0);
                    switch (entry) {
                        case 'l' : { displayEmployees(empList); }
                            break;
                        case 'd' : {
                                System.out.println(displayMenu(2));
                                int id = Integer.parseInt(scanner.nextLine());
                                displayEmployee(empList.stream().filter((Employee emp) -> emp.getId() == id).findFirst().orElse(null));
                            }
                            break;
                        case 'q' : { run = false; }
                    }
                }
            } catch(IllegalStateException | NoSuchElementException e) {
                // System.in has been closed
                System.out.println("System.in was closed; exiting");
            }
	}
        
        public static String displayMenu(Integer menuNumber) {
            String menu = "Please select: \n";
            switch (menuNumber) {
                case 1: {
                        menu += "l : List Employees \n";
                        menu += "d : Display Employee \n";
                        menu += "q : Exit Program \n";
                    }
                    break;
                case 2: {
                        menu += "Please enter the ID of the employee \n";
                    }
                    break;
                
            }
            return menu;
        }
        
        public static void displayEmployees(List<Employee> list) {
            System.out.println(list);
        }
        
        public static void displayEmployee(Employee employee) {
            System.out.println(employee);
        }
        
        public static List<Employee> getEmployees() throws IOException {
            List<Employee> _empList;
            try (Scanner scanner = new Scanner(new File("employees.csv"))) {
                Scanner dataScanner = null;
                int index = 0;
                _empList = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    dataScanner = new Scanner(scanner.nextLine());
                    dataScanner.useDelimiter(",");
                    Employee emp = new Employee();
                    
                    while (dataScanner.hasNext()) {
                        String data = dataScanner.next();
                        switch (index) {
                            case 0 -> emp.setId(Integer.parseInt(data));
                            case 1 -> emp.setName(data);
                            case 2 -> emp.setRole(data);
                            case 3 -> emp.setSalary(data);
                            default -> System.out.println("invalid data::" + data);
                        }
                        index++;
                    }
                    index = 0;
                    _empList.add(emp);
                }
            }
            
            return _empList;
        }
        
        public static List<Club> getClubs() throws IOException {
            //Read in clubs.csv
            
            return null;
        }
}