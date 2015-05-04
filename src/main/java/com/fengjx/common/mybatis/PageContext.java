
package com.fengjx.common.mybatis;

/**
 * 分页容器
 */
public class PageContext {

    private static ThreadLocal<Page> page = new ThreadLocal<Page>();

	public static Page getPage() {
		return page.get();
	}

	public static void setPage(Page page) {
		PageContext.page.set(page);
	}

	public static void remove() {
		PageContext.page.remove();
	}

}
