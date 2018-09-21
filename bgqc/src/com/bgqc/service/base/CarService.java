package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.CarBean;
import com.bgqc.beans.MsgBean;

public interface CarService {

	List<CarBean> getCar(CarBean carbean);

	MsgBean addCar(CarBean carbean);

	MsgBean editCar(CarBean carbean);

	MsgBean delCar(CarBean carbean);

}
