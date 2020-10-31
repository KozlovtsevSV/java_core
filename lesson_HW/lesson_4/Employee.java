package lesson_4;

public class Employee {

    // будем хранить табельный номер последнего сотрудника для реализации присвояения уникального номера сотруднику **
    static int cNumber;

    // номер сотрудника private для того чтобы класс самостоятельно назначал номера и из вне не возможно поменять номер
    private int numberEmployee;
    String fullName;
    String position;
    String phone;
    // оклад сотрудника делаем private для того чтобы не возможно было прочитать и записать из вне, а только методами  getSalary() и  setSalary() соответственно
    // такое может пригодиться например для контроля доступа к этой информации в методах можно написать кто получал или изменял определенную информацию
    private float salary;
    int age;

    Employee(String fullName, String position, String phone, float salary, int age){
        cNumber = cNumber + 1;
        this.numberEmployee = cNumber;
        this.fullName = fullName;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getNumber(){
        return numberEmployee;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPosition(){
        return position;
    }

    public String getPhone(){
        return phone;
    }
    public float getSalary(){
        return salary;
    }

    public int getAge(){
        return age;
    }

    public void setSalary(float salary){
        this.salary = salary;
    }

    // попробуем статический метод класса
    static void printEmployee(Employee employee){
        System.out.println("Табельный номер: " + employee.getNumber());
        System.out.println("Ф.И.О: " + employee.getFullName());
        System.out.println("Должность: " + employee.getPosition());
        System.out.println("телефон: " + employee.getPhone());
        System.out.println("Оклад: " + employee.getSalary());
        System.out.println("Возвраст: " + employee.getAge());
    }

}
