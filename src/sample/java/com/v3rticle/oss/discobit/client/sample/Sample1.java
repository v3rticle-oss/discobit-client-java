package com.v3rticle.oss.discobit.client.sample;

import com.v3rticle.oss.discobit.client.DiscobitClient;
import com.v3rticle.oss.discobit.client.DiscobitClientFactory;
import com.v3rticle.oss.discobit.client.DiscobitOperationException;

public class Sample1 {
	
	DiscobitClient discobit = DiscobitClientFactory.getClient();

	public static void main(String[] args) {
		
		Sample1 sample = new Sample1();
		sample.doStuff();
	}
	
	public void doStuff(){
		try {
			System.out.println(discobit.getConfigProperty(null, "test1", false));
		} catch (DiscobitOperationException e) {
			e.printStackTrace();
		}
		
	}

}
