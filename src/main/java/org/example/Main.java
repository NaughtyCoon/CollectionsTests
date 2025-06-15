package org.example;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        MyList<Gal> myArrayList = new MyArrayList<>();

        // Заполним наш ArrayList исходными данными с помощью метода .add
        myArrayList.add(new Gal("Jenny", 18));
        myArrayList.add(new Gal("Jane", 21));
        myArrayList.add(new Gal("Jade", 25));
        myArrayList.add(new Gal("Joan", 22));
        myArrayList.add(new Gal("Jacky", 32));
        myArrayList.add(new Gal("Julia", 34));
        myArrayList.add(new Gal("Jessy", 28));
        myArrayList.add(new Gal("Jolie", 27));
        myArrayList.add(new Gal("Jilly", 31));
        myArrayList.add(new Gal("Judith", 25));

        // Проконтролируем текущий размер самого списка и массива, который его содержит
        System.out.println("Размер списка: " + myArrayList.size() + ", ёмкость массива: " + myArrayList.capacity());
        // И вот, что в нём сейчас находится:
        System.out.println(myArrayList + "\n");  // Форматированный вывод всего списка

        // Добавим ещё элемент и посмотрим, что будет со статистикой размеров
        // (ёмкость массива должна увеличиться в полтора раза +1 место):
        myArrayList.add(new Gal("Jana", 26));
        System.out.println("Размер списка: " + myArrayList.size() + ", ёмкость массива: " + myArrayList.capacity());
        System.out.println(myArrayList + "\n");

        System.out.println("Вторая девушка в списке: " + myArrayList.get(1) + "\n");  // Доступ на чтение к элементу по индексу

        // Удалим элемент с индексом 3
        System.out.println("Нас покидает " + myArrayList.remove(3).name() + "!  :(((");
        // И посмотрим на список без покинувшей его Joan:
        System.out.println(myArrayList + "\n");

        // У нас новенькая, и директор попросил её записать под номером 3:
        System.out.println("Но зато у нас пополнение! Встречаем " + myArrayList.insert(new Gal("Jasmine", 19), 3).name() + "!");
        System.out.println("Теперь наш класс вот такой: \n" + myArrayList + "\n");

        // Отсортируем по именам и покажем, как они построились:
        myArrayList.sort((a, b) -> a.name().compareTo(b.name()));
        System.out.println(myArrayList + "\n");

        // А теперь - по возрасту:
        myArrayList.sort(Comparator.comparingInt(Gal::age));
        System.out.println(myArrayList + "\n");

        // Всё! Девушки устали. Очистка списка!
        myArrayList.destroy();
        System.out.println("Всех по домам! " + myArrayList + "\n");

    }

}
