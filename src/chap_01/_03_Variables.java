package chap_01;

public class _03_Variables {
    public static void main(String[] args) {
        String name;
        name = "민수";
        int age;
        age = 25;
        boolean pass = false;
        if (age >= 20) {
            pass = true;
        }
        System.out.println(name + "님은 성인입니까? " + pass);
    }
}
