package com.xyzq.kid.console.record.action;

import com.xyzq.kid.logic.record.dao.po.RecordPO;
import com.xyzq.kid.logic.record.entity.RecordEntity;
import com.xyzq.kid.logic.record.service.RecordService;
import com.xyzq.simpson.base.json.JSONObject;
import com.xyzq.simpson.maggie.access.spring.MaggieAction;
import com.xyzq.simpson.maggie.framework.Context;
import com.xyzq.simpson.maggie.framework.Visitor;
import com.xyzq.simpson.maggie.framework.action.core.IAction;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 根据飞行日志id删除飞行日志
 */
@MaggieAction(path = "kid/console/deleteFlightDiary")
public class DeleteFlightDiaryAction implements IAction {
	/**
	 * Action中只支持Autowired注解引入SpringBean
	 */
	@Autowired
	private RecordService recordService;


	/**
	 * 动作执行
	 *
	 * @param visitor 访问者
	 * @param context 请求上下文
	 * @return 下一步动作，包括后缀名，null表示结束
	 */
	@Override
	public String execute(Visitor visitor, Context context) throws Exception {
		Integer id = Integer.valueOf(String.valueOf(context.parameter("id")));
		RecordEntity entity = recordService.load(id);
		if (entity == null) {
			context.set("msg", "不能存在该ID记录!");
			context.set("code", "1");
			return "success.json";
		}
		recordService.deleteRecord(id);
		context.set("msg", "删除成功!");
		context.set("code", "0");
		return "success.json";
	}

}
