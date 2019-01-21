package com.hnjing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hnjing.full.service.FullSiteMonitorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UdiApplicationTests {
	@Autowired
	private FullSiteMonitorService fullSiteMonitorService;

	@Test
	public void contextLoads() throws Exception {
		System.out.println("test");
//		fullSiteMonitorService.deleteDataBeforeDays(1);
	}
	

}
