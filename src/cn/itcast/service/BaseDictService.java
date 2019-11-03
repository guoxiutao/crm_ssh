package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.BaseDict;

public interface BaseDictService {
	//根据typecode获得数据字典list
	List<BaseDict> getByTypeCode(String dict_type_code);

}
