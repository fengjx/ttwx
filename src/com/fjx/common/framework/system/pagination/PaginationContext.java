package com.fjx.common.framework.system.pagination;

public class PaginationContext {
	
	// 每页显示条数
	private static ThreadLocal<Integer> pagesize = new ThreadLocal();;
	// 当前页
	private static ThreadLocal<Integer> offset = new ThreadLocal();;

	public static int getPagesize() {
		Integer _pagesize = pagesize.get();
		if (_pagesize == null) {
			return Integer.MAX_VALUE;
		}
		return _pagesize;
	}

	public static void setPagesize(int _pagesize) {
		pagesize = new ThreadLocal<Integer>();
		pagesize.set(_pagesize);
	}

	public static int getOffset() {
		Integer _offset = offset.get();
		if (_offset == null) {
			return 0;
		}
		return _offset;
	}

	public static void setOffset(int _offset) {
		offset = new ThreadLocal<Integer>();
		offset.set(_offset);
	}

	public static void noPaging() {
		offset.set(0);
		pagesize.set(Integer.MAX_VALUE);
	}

	public static void removeOffset() {
		offset.remove();
	}

	public static void removePagesize() {
		pagesize.remove();
	}

	public static void removeAll() {
		offset.remove();
		pagesize.remove();
	}
	
}
