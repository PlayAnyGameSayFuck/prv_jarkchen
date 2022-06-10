package prv.jarkchen;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prv.jarkchen.controller.system.SysLogController;

@SpringBootTest
@MapperScan("prv.jarkchen.mapper.*")
class ApplicationTests {
	
	@Autowired
	private SysLogController logController;
	
	/* 测试连接数据库 */
	@Test
	void contextLoads() {
		System.out.println("测试数据");
		System.out.println(logController.queryList());
	}
}
