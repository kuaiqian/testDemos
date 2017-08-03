package simlator.utils;

import java.util.HashMap;
import java.util.Map;

public class ConfigResponseCode {
	/**
	 * 交易类型对应的配置
	 */
	private Map<String, TxnConfig> txnTypesResultCode = new HashMap<String, TxnConfig>();

	/**
	 * 线程睡眠时间
	 */
	private Long sleepTime = -1L;

	public Long getLongSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(Long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getSleepTime() {
		return String.valueOf(sleepTime);
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = Long.parseLong(sleepTime);
	}

	public Map<String, TxnConfig> getTxnTypesResultCode() {
		return txnTypesResultCode;
	}

	public void setTxnTypesResultCode(Map<String, TxnConfig> txnTypesResultCode) {
		this.txnTypesResultCode = txnTypesResultCode;
	}
}
