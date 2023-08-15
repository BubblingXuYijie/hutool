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

package org.dromara.hutool.http.client;

import org.dromara.hutool.http.HttpGlobalConfig;
import org.dromara.hutool.http.proxy.HttpProxy;
import org.dromara.hutool.http.ssl.SSLInfo;

/**
 * Http客户端配置
 *
 * @author looly
 */
public class ClientConfig {

	/**
	 * 创建新的 ClientConfig
	 *
	 * @return ClientConfig
	 */
	public static ClientConfig of() {
		return new ClientConfig();
	}

	/**
	 * 默认连接超时
	 */
	private int connectionTimeout;
	/**
	 * 默认读取超时
	 */
	private int readTimeout;
	/**
	 * SSL相关配置
	 */
	private SSLInfo sslInfo;
	/**
	 * 是否禁用缓存
	 */
	private boolean disableCache;
	/**
	 * 代理
	 */
	private HttpProxy proxy;

	/**
	 * 构造
	 */
	public ClientConfig() {
		connectionTimeout = HttpGlobalConfig.getTimeout();
		readTimeout = HttpGlobalConfig.getTimeout();
		sslInfo = SSLInfo.TRUST_ANY;
	}

	/**
	 * 设置超时，单位：毫秒<br>
	 * 超时包括：
	 *
	 * <pre>
	 * 1. 连接超时
	 * 2. 读取响应超时
	 * </pre>
	 *
	 * @param milliseconds 超时毫秒数
	 * @return this
	 * @see #setConnectionTimeout(int)
	 * @see #setReadTimeout(int)
	 */
	public ClientConfig setTimeout(final int milliseconds) {
		setConnectionTimeout(milliseconds);
		setReadTimeout(milliseconds);
		return this;
	}

	/**
	 * 获取连接超时，单位：毫秒
	 *
	 * @return 连接超时，单位：毫秒
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * 设置连接超时，单位：毫秒
	 *
	 * @param connectionTimeout 超时毫秒数
	 * @return this
	 */
	public ClientConfig setConnectionTimeout(final int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
		return this;
	}

	/**
	 * 获取读取超时，单位：毫秒
	 *
	 * @return 读取超时，单位：毫秒
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * 设置读取超时，单位：毫秒
	 *
	 * @param readTimeout 读取超时，单位：毫秒
	 * @return this
	 */
	public ClientConfig setReadTimeout(final int readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	/**
	 * 获取SSLInfo
	 *
	 * @return SSLInfo
	 */
	public SSLInfo getSslInfo() {
		return this.sslInfo;
	}

	/**
	 * 设置SSLSocketFactory<br>
	 * 只针对HTTPS请求，如果不设置，使用默认的SSLSocketFactory<br>
	 * 默认SSLSocketFactory为：SSLSocketFactoryBuilder.create().build();
	 *
	 * @param sslInfo SSLInfo
	 * @return this
	 */
	public ClientConfig setSSLInfo(final SSLInfo sslInfo) {
		this.sslInfo = sslInfo;
		return this;
	}

	/**
	 * 是否禁用缓存
	 *
	 * @return 是否禁用缓存
	 */
	public boolean isDisableCache() {
		return disableCache;
	}

	/**
	 * 设置是否禁用缓存
	 *
	 * @param disableCache 是否禁用缓存
	 */
	public void setDisableCache(final boolean disableCache) {
		this.disableCache = disableCache;
	}

	/**
	 * 获取代理
	 *
	 * @return 代理
	 */
	public HttpProxy getProxy() {
		return proxy;
	}

	/**
	 * 设置Http代理
	 *
	 * @param host 代理 主机
	 * @param port 代理 端口
	 * @return this
	 */
	public ClientConfig setHttpProxy(final String host, final int port) {
		return setProxy(new HttpProxy(host, port));
	}

	/**
	 * 设置代理
	 *
	 * @param proxy 代理 {@link HttpProxy}
	 * @return this
	 */
	public ClientConfig setProxy(final HttpProxy proxy) {
		this.proxy = proxy;
		return this;
	}
}
