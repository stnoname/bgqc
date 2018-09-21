package com.bgqc.service.base;

import java.util.Date;

import com.bgqc.beans.MsgBean;

public interface TestService {
	public MsgBean test(int num,Date fromtime,Date totime);
}
