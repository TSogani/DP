package com.don.accrssor;

import java.io.IOException;
import java.util.Properties;

abstract public class AbstractPropFileAccessor implements IAccessor{

	protected String loc;

	@Override
	public Properties getData() {
		Properties prop = null;
		
		prop = new Properties();
		// here location null pass at calling time
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(loc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
}
