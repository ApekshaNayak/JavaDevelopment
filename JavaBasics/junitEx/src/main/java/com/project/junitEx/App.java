package com.project.junitEx;

/**
 * Hello world!
 *
 */
public class App {
	public static int findMax(int ary[]) {
	   int max=0;
	   for(int i=0; i<ary.length;i++) {
		   if(max<ary[i]) {
			   max=ary[i];
		   }
	   }
	   return max;
   }
}
