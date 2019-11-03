package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getByTypeCode(String dict_type_code);

}
