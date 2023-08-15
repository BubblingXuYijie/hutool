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

package org.dromara.hutool.core.lang.mutable;

import org.dromara.hutool.core.comparator.CompareUtil;

/**
 * 可变 {@code double} 类型
 *
 * @see Double
 * @since 3.0.1
 */
public class MutableDouble extends Number implements Comparable<MutableDouble>, Mutable<Number> {
	private static final long serialVersionUID = 1L;

	private double value;

	/**
	 * 构造，默认值0
	 */
	public MutableDouble() {
	}

	/**
	 * 构造
	 * @param value 值
	 */
	public MutableDouble(final double value) {
		this.value = value;
	}

	/**
	 * 构造
	 * @param value 值
	 */
	public MutableDouble(final Number value) {
		this(value.doubleValue());
	}

	/**
	 * 构造
	 * @param value String值
	 * @throws NumberFormatException 数字转换错误
	 */
	public MutableDouble(final String value) throws NumberFormatException {
		this.value = Double.parseDouble(value);
	}

	@Override
	public Double get() {
		return this.value;
	}

	/**
	 * 设置值
	 * @param value 值
	 */
	public void set(final double value) {
		this.value = value;
	}

	@Override
	public void set(final Number value) {
		this.value = value.doubleValue();
	}

	// -----------------------------------------------------------------------
	/**
	 * 值+1
	 * @return this
	 */
	public MutableDouble increment() {
		value++;
		return this;
	}

	/**
	 * 值减一
	 * @return this
	 */
	public MutableDouble decrement() {
		value--;
		return this;
	}

	// -----------------------------------------------------------------------
	/**
	 * 增加值
	 * @param operand 被增加的值
	 * @return this
	 */
	public MutableDouble add(final double operand) {
		this.value += operand;
		return this;
	}

	/**
	 * 增加值
	 * @param operand 被增加的值，非空
	 * @return this
	 */
	public MutableDouble add(final Number operand) {
		this.value += operand.doubleValue();
		return this;
	}

	/**
	 * 减去值
	 *
	 * @param operand 被减的值
	 * @return this
	 */
	public MutableDouble subtract(final double operand) {
		this.value -= operand;
		return this;
	}

	/**
	 * 减去值
	 *
	 * @param operand 被减的值，非空
	 * @return this
	 */
	public MutableDouble subtract(final Number operand) {
		this.value -= operand.doubleValue();
		return this;
	}

	// -----------------------------------------------------------------------
	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return (long) value;
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

	@Override
	public double doubleValue() {
		return value;
	}

	// -----------------------------------------------------------------------
	/**
	 * 相等需同时满足如下条件：
	 * <ol>
	 * 	<li>非空</li>
	 * 	<li>类型为 {@code MutableDouble}</li>
	 * 	<li>值相等</li>
	 * </ol>
	 *
	 * @param obj 比对的对象
	 * @return 相同返回<code>true</code>，否则 {@code false}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof MutableDouble) {
			return (Double.doubleToLongBits(((MutableDouble)obj).value) == Double.doubleToLongBits(value));
		}
		return false;
	}

	@Override
	public int hashCode() {
		final long bits = Double.doubleToLongBits(value);
		return (int) (bits ^ bits >>> 32);
	}

	// -----------------------------------------------------------------------
	/**
	 * 比较
	 *
	 * @param other 其它 {@code MutableDouble} 对象
	 * @return x==y返回0，x&lt;y返回-1，x&gt;y返回1
	 */
	@Override
	public int compareTo(final MutableDouble other) {
		return CompareUtil.compare(this.value, other.value);
	}

	// -----------------------------------------------------------------------
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
