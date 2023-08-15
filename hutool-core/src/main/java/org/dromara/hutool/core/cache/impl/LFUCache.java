/*
 * Copyright (c) 2023 looly(loolly@aliyun.com)
 * Hutool is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          https://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package org.dromara.hutool.core.cache.impl;

import java.util.HashMap;
import java.util.Iterator;

/**
 * LFU(least frequently used) 最少使用率缓存<br>
 * 根据使用次数来判定对象是否被持续缓存<br>
 * 使用率是通过访问次数计算的。<br>
 * 当缓存满时清理过期对象。<br>
 * 清理后依旧满的情况下清除最少访问（访问计数最小）的对象并将其他对象的访问数减去这个最小访问数，以便新对象进入后可以公平计数。
 *
 * @author Looly,jodd
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class LFUCache<K, V> extends StampedCache<K, V> {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造
	 *
	 * @param capacity 容量
	 */
	public LFUCache(final int capacity) {
		this(capacity, 0);
	}

	/**
	 * 构造
	 *
	 * @param capacity 容量
	 * @param timeout 过期时长
	 */
	public LFUCache(int capacity, final long timeout) {
		if(Integer.MAX_VALUE == capacity) {
			capacity -= 1;
		}

		this.capacity = capacity;
		this.timeout = timeout;
		cacheMap = new HashMap<>(capacity + 1, 1.0f);
	}

	// ---------------------------------------------------------------- prune

	/**
	 * 清理过期对象。<br>
	 * 清理后依旧满的情况下清除最少访问（访问计数最小）的对象并将其他对象的访问数减去这个最小访问数，以便新对象进入后可以公平计数。
	 *
	 * @return 清理个数
	 */
	@Override
	protected int pruneCache() {
		int count = 0;
		CacheObj<K, V> comin = null;

		// 清理过期对象并找出访问最少的对象
		Iterator<CacheObj<K, V>> values = cacheObjIter();
		CacheObj<K, V> co;
		while (values.hasNext()) {
			co = values.next();
			if (co.isExpired() == true) {
				values.remove();
				onRemove(co.key, co.obj);
				count++;
				continue;
			}

			//找出访问最少的对象
			if (comin == null || co.accessCount.get() < comin.accessCount.get()) {
				comin = co;
			}
		}

		// 减少所有对象访问量，并清除减少后为0的访问对象
		if (isFull() && comin != null) {
			final long minAccessCount = comin.accessCount.get();

			values = cacheObjIter();
			CacheObj<K, V> co1;
			while (values.hasNext()) {
				co1 = values.next();
				if (co1.accessCount.addAndGet(-minAccessCount) <= 0) {
					values.remove();
					onRemove(co1.key, co1.obj);
					count++;
				}
			}
		}

		return count;
	}
}
