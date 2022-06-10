package prv.jarkchen.service.leftContent;

import com.baomidou.mybatisplus.extension.service.IService;
import prv.jarkchen.pojo.leftContent.LeftNavigation;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.LeftContent.LeftNavigationVo;

public interface LeftNavigationService extends IService<LeftNavigation> {
	public RespBean getAllNavName(LeftNavigationVo leftNavigationVo);
}
