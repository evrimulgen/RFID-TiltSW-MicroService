package io.pivotal.sensor.service;

import io.pivotal.sensor.dao.UserRepository;
import io.pivotal.sensor.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> findAllUsers() {
		// return getDummyData();
		return userRepository.findAll();
	}

//	private Iterable<User> getDummyData() {
//		List<User> rs = new ArrayList<User>();
//		User u1 = new User();
//		u1.setFirstname("Eugene");
//		u1.setLastname("Bell");
//		u1.setId(1l);
//		RFID r12 = new RFID();
//		r12.setActive(false);
//		r12.setRfid("2101010");
//		r12.setCreatedTime(new Date(1421160768284l));
//		r12.setId(2l);
//		u1.setRfid(r12);
//		rs.add(u1);
//
//		User u2 = new User();
//		u2.setFirstname("Stephen");
//		u2.setLastname("Carter");
//		u2.setId(2l);
//		RFID r13 = new RFID();
//		r13.setActive(false);
//		r13.setRfid("33301010");
//		r13.setCreatedTime(new Date(1421160768284l));
//		r13.setId(3l);
//		u2.setRfid(r13);
//		rs.add(u2);
//
//		User u3 = new User();
//		u3.setFirstname("Trevor");
//		u3.setLastname("Kenna");
//		u3.setId(3l);
//		RFID r14 = new RFID();
//		r14.setActive(false);
//		r14.setRfid("888483101010");
//		r14.setCreatedTime(new Date(1421160768284l));
//		r14.setId(4l);
//		u3.setRfid(r14);
//		rs.add(u3);
//		return rs;
//	}

}
