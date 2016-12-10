package businessLogic.service;

import java.util.List;

import common.ResultMessage;
import vo.ActivityPromotionVo;
import vo.LevelVo;

/**
 * @version 2016-12-10
 * @author 金灵益
 * @description 网站营销人员进行网站活动促销策略制定，等级促销策略制定，特定商圈促销策略制定
 *              
 */
public interface MarketPromotionBlService {
	
	/**
	 * 增加一条活动策略
	 * @param vo
	 * @return
	 */
	public ResultMessage addActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * 更新一条活动策略
	 * @param vo
	 * @return
	 */
	public ResultMessage upActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * 删除一条活动策略
	 * @param vo
	 * @return
	 */
	public ResultMessage delActivityStrategy(ActivityPromotionVo vo);
	
	/**
	 * 得到当前的有效活动策略
	 * @return
	 */
	public List<ActivityPromotionVo> getCurrentActStrategy();
	
	/**
	 * 得到客户等级对应的促销策略
	 * @param level
	 * @return
	 */
	public LevelVo getLevelStrategy(int level);
	
	/**
	 * 通过客户信用值得到客户的等级
	 * @param credit
	 * @return
	 */
	public int getMemberLevel(int credit);
	
	/**
	 * 更新等级促销策略
	 * 本方法会检查输入的有效性：等级、信用值必须递增，List存的等级必须递增
	 * @param list
	 * @return
	 */
	public ResultMessage updateLevelStrategy(List<LevelVo> list);
}
