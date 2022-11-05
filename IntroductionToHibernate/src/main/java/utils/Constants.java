package utils;

public class Constants {
    public static final String VITOSHKA_15 = "Vitoshka 15";
    public static final String PLEASE_ENTER_EMPLOYEE_LAST_NAME_TO_WHOM_ASSIGNED_NEW_ADDRESS = "Please enter employee last name to whom assigned new address";
    public static final String SELECT_E_FROM_EMPLOYEE_E_WHERE_E_LAST_NAME_EMPLOYEE_LAST_NAME = "SELECT e FROM Employee e where e.lastName = :employeeLastName";
    public static final String EMPLOYEE_LAST_NAME = "employeeLastName";
    public static final String D_EMPLOYEES_ARE_AFFECTED = "%d Employees are affected";

    public static final String SELECT_E_FROM_EMPLOYEE_E_GROUP_BY_E_ADDRESS_ID_ORDER_BY_COUNT_E_ID_DESC = "select e from Employee e group by e.address.id ORDER by count(e.id) desc";
    public static final String S_S_D = "%s %s - %d";

    public static final String SELECT_T_FROM_TOWN_T_WHERE_LENGTH_T_NAME_MAX_NAME_LENGTH = "SELECT t FROM Town t where length(t.name) > :maxNameLength";
    public static final String MAX_NAME_LENGTH = "maxNameLength";
    public static final String UPDATE_TOWN_T_SET_T_NAME_UPPER_T_NAME_WHERE_LENGTH_T_NAME_5 = "UPDATE Town t SET t.name = upper(t.name) where length(t.name) > 5";

    public static final String PLEASE_ENTER_NAME_I_WILL_CHECK_THAT_NAME_IN_DATABASE_AND_GIVE_YOU_ANSWER_IF_THAT_USER_EXIST_OR_NOT = "Please enter name! I will check that name in database and give you answer if that user exist or not!";
    public static final String SELECT_E_FROM_EMPLOYEE_E_WHERE_CONCAT_WS_E_FIRST_NAME_E_LAST_NAME_NAME = "SELECT e FROM Employee e where CONCAT_WS(' ',e.firstName , e.lastName) = :name";
    public static final String NAME = "name";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String HAVE_A_NICE_DAY = "Have a nice day!";

    public static final String SELECT_E_FROM_EMPLOYEE_E_WHERE_E_DEPARTMENT_NAME_RESEARCH_AND_DEVELOPMENT_ORDER_BY_E_SALARY_E_ID = "select e from Employee e where e.department.name = 'Research and Development' order by e.salary, e.id ";
    public static final String S_S_FROM_S_$_2_F = "%s %s from %s - $%.2f";

    public static final String UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_10 = "UPDATE Employee e SET e.salary = e.salary * 10";
    public static final String SELECT_E_FROM_EMPLOYEE_E_GROUP_BY_E_DEPARTMENT_ID_HAVING_MAX_E_SALARY_NOT_BETWEEN_30000_AND_70000 = "Select e from Employee e group by e.department.id HAVING max(e.salary) NOT BETWEEN 30000 and 70000";
    public static final String S_2_F = "%s %.2f";

    public static final String SELECT_E_FIRST_NAME_FROM_EMPLOYEE_E_WHERE_E_SALARY_SALARY = "SELECT e.firstName FROM Employee e WHERE e.salary > :salary";
    public static final String SALARY = "salary";

    public static final String PLEASE_ENTER_PATTERN_FOR_SEARCH_IN_EMPLOYEE_FOR_FIRSTNAME_START_WITH_THIS_PATTERN = "Please enter pattern for search in employee for firstname start with this pattern!";
    public static final String UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_10_WHERE_E_FIRST_NAME_LIKE_CODE = "UPDATE Employee e SET e.salary = e.salary * 10 WHERE e.firstName like :code";
    public static final String CODE = "code";
    public static final String PERCENT = "%";
    public static final String SELECT_E_FROM_EMPLOYEE_E_WHERE_E_FIRST_NAME_LIKE_CODE_ORDER_BY_E_ID = "SELECT e FROM Employee e where e.firstName like :code order by e.id";
    public static final String S_S_S_2_F = "%s %s - %s - (%.2f)";
    public static final String SELECT_P_FROM_PROJECT_P_ORDER_BY_P_START_DATE_DESC = "SELECT p FROM Project p order by p.startDate desc";
    public static final String PROJECT_NAME_S_N_PROJECT_DESCRIPTION_S_N_PROJECT_START_DATE_S_N_PROJECT_END_DATE_S = "Project name: %s%nProject Description: %s%nProject Start Date: %s%nProject End Date: %s";
    public static final String SELECT_E_FROM_EMPLOYEE_E_WHERE_E_ID_ID = "SELECT e FROM Employee e where e.id = :id";
    public static final String S_S_S_N_S = "%s %s - %s%n%s";
    public static final String PLEASE_ENTER_EMPLOYEE_ID = "Please enter employee id";
    public static final String GetEmployeeWithProject_ID = "id";
    public static final String UPDATE_EMPLOYEE_E_SET_E_SALARY_E_SALARY_1_12_WHERE_E_DEPARTMENT_ID_IN_1_2_14_3 = "UPDATE Employee e SET e.salary = e.salary * 1.12 WHERE e.department.id in (1,2,14,3)";
    public static final String SELECT_QUERY = "" +
            "Select e from Employee e " +
            "where e.department.name = 'Engineering' or e.department.name = 'Tool Design' or " +
            "e.department.name = 'Information Services' or  e.department.name = 'Marketing'";
    public static final String S_S_2_F = "%s %s (%.2f)";
    public static final String PLEASE_ENTER_TOWN_NAME = "Please enter town name:";
    public static final String SELECT_T_FROM_TOWN_T_WHERE_T_NAME_TOWN_NAME = "Select t FROM Town t where t.name = :townName";
    public static final String TOWN_NAME = "townName";
    public static final String SELECT_A_FROM_ADDRESS_A_WHERE_A_TOWN_ID_ID = "Select a FROM Address a where a.town.id = :id";
    public static final String RemoveTowns_ID = "id";
    public static final String UPDATE_EMPLOYEE_E_SET_E_ADDRESS_ID_NULL_WHERE_E_ADDRESS_ID_ADDRESS_ID = "UPDATE Employee e set e.address.id = null WHERE e.address.id = :addressId";
    public static final String ADDRESS_ID = "addressId";
    public static final String DELETE_ADDRESS_A_WHERE_A_TOWN_ID_ID = "DELETE Address a WHERE a.town.id = :id";
    public static final String ID1 = "id";
    public static final String D_ADDRESS_IN_S_DELETED = "%d address in %s deleted";
    public static final String TOWN_WITH_NAME_S_NOT_FOUND = "Town with name %s not found";
}
