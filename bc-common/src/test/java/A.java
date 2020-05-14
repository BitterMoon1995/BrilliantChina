import java.text.Collator;
import java.util.*;
//十年Java，血与泪，周神的中文排序之路
public class A {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1,"中"));
        list.add(new Person(1,"出"));
        list.add(new Person(1,"被"));
        list.add(new Person(1,"啊"));
        System.out.printf(String.valueOf(list));

        Comparator<Object> comparator = Collator.getInstance(Locale.CHINA);
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                String s1 = p1.name;
                String s2 = p2.name;
                return comparator.compare(s1, s2);
            }
        });

        System.out.printf(String.valueOf(list));
    }
}
