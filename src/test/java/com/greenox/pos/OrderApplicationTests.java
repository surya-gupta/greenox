package com.greenox.pos;

import com.greenox.pos.dao.InventoryDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	@Autowired
	InventoryDao inventoryDao;
	@Test
	public void contextLoads() {

	}

}
