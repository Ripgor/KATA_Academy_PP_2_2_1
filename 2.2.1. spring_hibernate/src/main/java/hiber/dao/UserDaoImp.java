package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   //@Autowired
   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   @Override
   public User owner(String model, int series) {
      // в переводе на русский :"Достань из таблицы users тех, у кого модель и серия машины совпадают с переданными параметрами"
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User user where user.car.model = :model and user.car.series = :series");

      // Назначем эти параметры -- они будут подставлены вместо :model and :series соответственно
      query.setParameter("model", model)
           .setParameter("series", series);

      return query.getSingleResult();
   }

}