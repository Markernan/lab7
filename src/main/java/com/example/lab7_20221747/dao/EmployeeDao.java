
public class EmployeeDao {
    private String url = "jdbc:mysql://localhost:3306/employees";
    private String user = "root";
    private String pass = "root";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY emp_no DESC";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeById(int empNo) {
        String sql = "SELECT * FROM employees WHERE emp_no = ?";
        Employee emp = null;

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                emp = new Employee();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
            }
        } catch (SQLException e) {
        }
        return emp;
    }

    public int createEmployee(Employee emp) {
        String sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emp.getEmpNo());
            pstmt.setDate(2, new java.sql.Date(emp.getBirthDate().getTime()));
            pstmt.setString(3, emp.getFirstName());
            pstmt.setString(4, emp.getLastName());
            pstmt.setString(5, emp.getGender());
            pstmt.setDate(6, new java.sql.Date(emp.getHireDate().getTime()));

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateEmployee(Employee emp) {

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, new java.sql.Date(emp.getBirthDate().getTime()));
            pstmt.setString(2, emp.getFirstName());
            pstmt.setString(3, emp.getLastName());
            pstmt.setString(4, emp.getGender());
            pstmt.setDate(5, new java.sql.Date(emp.getHireDate().getTime()));
            pstmt.setInt(6, emp.getEmpNo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteEmployee(int empNo) {
        String sql = "DELETE FROM employees WHERE emp_no=?";
        int result = 0;

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getNextEmployeeId() {
    private objeto = new objeto();
    }
}