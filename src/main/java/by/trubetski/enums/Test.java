package by.trubetski.enums;

import by.trubetski.models.Animal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.Optional;

public class Test {


    Optional<?> object1 = Optional.empty();

   public static void main(String[] args) {
      Animal animal = new Animal();
      Class<? extends Animal> field = animal.getClass();
      field.getDeclaredFields();
      Method[] field1 = field.getDeclaredMethods();
      field.getDeclaringClass().getName();
      Optional<?> object = Optional.empty();
      Optional<String> string = Optional.of("sdf");
      Optional<String> string2 = Optional.ofNullable(null);
      string2.isPresent();
      Optional.ofNullable(null);
      string2.ifPresentOrElse(System.out::println, System.out::println);
      string2.get();
      string2.filter(s -> s.equals("sdf")).ifPresent(System.out::println);
   }

   public void setObject1(Animal animal) {
      for (Field field : animal.getClass().getDeclaredFields()) {
         field.setAccessible(true);
      }
   }
}
