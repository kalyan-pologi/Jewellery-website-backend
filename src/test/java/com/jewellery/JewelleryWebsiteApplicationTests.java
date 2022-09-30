package com.jewellery;

import com.jewellery.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JewelleryWebsiteApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}
//
//	@Test
//	public void repoTest(){
//       String className = this.productRepository.getClass().getName();
//	   String packageName = this.productRepository.getClass().getPackageName();
//		System.out.println(className);
//        System.out.println(packageName);
//	}

}
