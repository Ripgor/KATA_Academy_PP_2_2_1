package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User u1 = new User("U1", "LN1", "u1@mail.ru");
      Car c1 = new Car("Nissan", 15);
      u1.setCar(c1);

      User u2 = new User("U2", "LN2", "u2@mail.ru");
      Car c2 = new Car("Toyota", 5);
      u2.setCar(c2);

      User u3 = new User("U3", "LN3", "u3@mail.ru");
      Car c3 = new Car("Ford", 10);
      u3.setCar(c3);

      userService.add(u1);
      userService.add(u2);
      userService.add(u3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      try {
         User ownerCar = userService.owner("Nissan", 15);
         System.out.println(ownerCar);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }
      context.close();
   }
}