package dev.fringe.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.model.User;
import dev.fringe.model.UserS;
import dev.fringe.repository.UserSRepository;

@Service
public class UserService {

	@Autowired SessionFactory sessionFactory;
	
	@Autowired UserSRepository userSRepository;
	
//	@Value("#{jobParameters['table']}") String table;
	
	public void insert() {
		sessionFactory.getCurrentSession().save(new User(UUID.randomUUID().toString()));
	}
	
	public void insertS() {
		sessionFactory.getCurrentSession().save(new UserS(UUID.randomUUID().toString()));
	}

	public void select() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		Map<String, List> strs = new HashMap<>();
		for (User user : list) {
			Map<String, Object> map = PropertyUtils.describe(user);
			map.forEach((key, value)-> {
				if(key.equals("name")) {
					List objs = strs.get("name");
					if(objs == null) {
						objs = new ArrayList<>();
						objs.add(value);
						strs.put("name", objs);
					}else {
						objs.add(value);
						strs.put("name", objs);
					}
				}
			});
		}
		List<User> users = new ArrayList();
		for (User user : list) {
			Map<String, Object> map = PropertyUtils.describe(user);
			for( String key : map.keySet() ){ 
				if(key.equals("name")) {
					map.put("name", String.join("|", strs.get("name")));
				}
			}
			User temp = new User();
			try {
				BeanUtils.populate(temp, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			users.add(temp);
		}
		System.out.println(users);
		
//		CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<UserS> cq = cb.createQuery(UserS.class);
//		Root<UserS> root = cq.from(UserS.class);
////		if(StringUtils.isEmpty(event.getId()) == false) {
//
////			Predicate predicate = cb.equal(root.get("id"), event.getId());
//
////			cq.select(root).where(predicate);
//
////		}
//		Query<UserS> query = sessionFactory.getCurrentSession().createQuery(cq);
////		query.setFirstResult(event.getFormRecord());
////		query.setMaxResults(event.getSize());
//		System.out.println(query.list());
	}
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<String> arrs = Arrays.asList("12","23");
		System.out.println(String.join("|", arrs));
		User user = new User();
		user.setId(Long.parseLong("1232"));
		user.setName("12332");
		user.setInserted(new Date());
		Map map = new HashMap();
		map =PropertyUtils.describe(user);
		map.put("name", "sdsd");
		User user2 = new User();
		BeanUtils.populate(user2, map);
		System.out.println(user);
		System.out.println(user2);
	}

	public void selectS() {
//		List<UserS> list = sessionFactory.getCurrentSession().createQuery("from UserS", UserS.class).list();
//		System.out.println(list);
		System.out.println(userSRepository.findAll());
	}

	public void insertS1() {
		userSRepository.save(new UserS(UUID.randomUUID().toString()));
	}
}
