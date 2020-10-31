package lesson_4;

public class Main {

    public static void increaseSalaryEmployees(Employee[] arrayEmployees, int age, float donate){

        for(Employee arrayEmployee : arrayEmployees) {
            if (arrayEmployee.getAge() > age) {
                float salary = arrayEmployee.getSalary();
                arrayEmployee.setSalary(salary += donate);
            }
        }
    }

    public static void printAllEmployee(Employee[] arrayEmployees){

        System.out.println("Все сотрудники:");
        for(Employee arrayEmployee : arrayEmployees) {
            Employee.printEmployee(arrayEmployee);
            System.out.println("---------------------------------------------");
        }
    }

    public static void printEmployeeOlderAge(Employee[] arrayEmployees, int age){

        System.out.println("Сотрудники старше " + age + "лет:");
        for(Employee arrayEmployee : arrayEmployees) {
            if(arrayEmployee.getAge() > age ){
                Employee.printEmployee(arrayEmployee);
                System.out.println("---------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {

        Employee[] arrayEmployees = new Employee[5];
        arrayEmployees[0] = new Employee("Иванов Иван Иванович","директор","8(903)999-99-01", 100000, 50);
        arrayEmployees[1] = new Employee("Петров Петр Петрович","начальник отдела снабжения","8(903)999-99-02", 50000, 45);
        arrayEmployees[2] = new Employee("Сидоров Сидр Сидорович","сотрудник отдела снабжения","8(903)999-99-03", 30000, 25);
        arrayEmployees[3] = new Employee("Васильев Василий Васильевич","начальник производства","8(903)999-99-04", 50000, 41);
        arrayEmployees[4] = new Employee("Миронов Мирон Миронович","сотрудник произаодства","8(903)999-99-05", 25000, 20);

        // Выводим сотрудников старше 40 лет
        printEmployeeOlderAge(arrayEmployees, 40);

        // Увеличиваем ЗП сотркдникам старше 40 на 5000
        increaseSalaryEmployees(arrayEmployees, 40, 5000);

        // выводим всех сотрудников
        printAllEmployee(arrayEmployees);

    }
}
//    Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
//        Конструктор класса должен заполнять эти поля при создании объекта;
//        Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//        Вывести при помощи методов из пункта 3 ФИО и должность.
//        Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
//        * Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000;
//        ** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.