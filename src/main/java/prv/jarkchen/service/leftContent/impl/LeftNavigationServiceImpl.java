package prv.jarkchen.service.leftContent.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import prv.jarkchen.mapper.leftContent.LeftNavigationMapper;
import prv.jarkchen.pojo.leftContent.LeftNavigation;
import prv.jarkchen.service.leftContent.LeftNavigationService;
import prv.jarkchen.vo.RespBean;
import prv.jarkchen.vo.RespBeanEnum;
import prv.jarkchen.vo.LeftContent.LeftNavigationVo;

@Service
public class LeftNavigationServiceImpl 
	extends ServiceImpl<LeftNavigationMapper, LeftNavigation> 
	implements LeftNavigationService {

	@Override
	public RespBean getAllNavName(LeftNavigationVo leftNavigationVo) {
		List<LeftNavigation> leftNavigations = this.list();
		if(CollectionUtils.isEmpty(leftNavigations)) {
			return RespBean.success(RespBeanEnum.QUERY_SUCCESS, new ArrayList<>());
		}
		return RespBean.success(RespBeanEnum.QUERY_SUCCESS, leftNavigations);
	}
}
