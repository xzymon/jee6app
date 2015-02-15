package com.xzymon.jee6app;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(RemoteCounter.class)
public class CounterBean implements BusinessCounter {

	@Override
	public int countCharacters(String toCount) {
		if(toCount!=null){
			return toCount.length();
		}
		return 0;
	}

}
