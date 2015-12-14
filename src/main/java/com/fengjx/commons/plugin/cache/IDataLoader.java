
package com.fengjx.commons.plugin.cache;

/**
 * IDataLoader.
 * <p>
 * Example:
 * 
 * <pre>
 * List&lt;Blog&gt; blogList = EhCacheKit.handle(&quot;blog&quot;, &quot;blogList&quot;, new IDataLoader() {
 *     public Object load() {
 *         return Blog.dao.find(&quot;select * from blog&quot;);
 *     }
 * });
 * </pre>
 */
public interface IDataLoader<T> {
    T load();
}
