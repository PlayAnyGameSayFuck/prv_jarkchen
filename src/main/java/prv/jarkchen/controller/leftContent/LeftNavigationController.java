package prv.jarkchen.controller.leftContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import prv.jarkchen.service.leftContent.LeftNavigationService;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.LeftContent.LeftNavigationVo;

@Controller
@RequestMapping(value="/leftNav")
public class LeftNavigationController {

	@Autowired
	private LeftNavigationService leftNavigationService;
	
	@ResponseBody
	@PostMapping(value = "/getAll")
	public RespBean getAllNavName(@RequestBody LeftNavigationVo leftNavigationVo){
		return leftNavigationService.getAllNavName(leftNavigationVo);
	}
}
