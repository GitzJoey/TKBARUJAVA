package com.tkbaru.common;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;

public class ShrinkableLazyList extends LazyList {

	  protected ShrinkableLazyList(List list, Factory factory) {
		  super(list, factory);
	  }

	  public static List decorate(List list, Factory factory) {
		  return new ShrinkableLazyList(list, factory);
	  }

	  public void shrink() {
	    for (Iterator i = getList().iterator(); i.hasNext();)
	    	if (i.next()==null)
	        i.remove();
	  	}

	  @Override
	  public Iterator iterator() {
		  shrink();
		  return super.iterator();
	  }
}
