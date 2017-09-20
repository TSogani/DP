package com.don.accrssor;

import java.util.Properties;

public class CityRIPropertiAccessor extends AbstractPropFileAccessor {

	@Override
	public String getKey() {
		super.loc = "city_ri.properties";
		String key = "ciry_ri";
		return key;
	}

	public Properties getData() {
		return super.getData();
	}
}
