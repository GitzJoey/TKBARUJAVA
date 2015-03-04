package com.tkbaru.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tkbaru.model.Truck;
import com.tkbaru.service.TruckService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TruckDAOTest {

	@Autowired
	TruckService truckService;
	
	@Test
	public void getAllTruck() {
		List<Truck> list = truckService.getAllTruck();
		Assert.assertNotNull(list);
	}
}
