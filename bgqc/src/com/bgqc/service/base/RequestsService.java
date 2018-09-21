package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;

public interface RequestsService {

	public List<RequestBean> getRequests(RequestBean RequestBean);
	public MsgBean addRequests(RequestBean RequestBean);
	public MsgBean editRequests(RequestBean RequestBean);
	public MsgBean delRequests(RequestBean RequestBean);
	public MsgBean repassRequests(RequestBean RequestBean);

}
