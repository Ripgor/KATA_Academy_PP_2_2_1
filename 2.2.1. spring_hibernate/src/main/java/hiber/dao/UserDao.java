package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   // Новый метод, который вытаскивает user'a по модели и серии его машины
   User getUserByCar(String model, int series);

   List<User> getUsers();
}
