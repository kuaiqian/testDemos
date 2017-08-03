package simlator.utils;
/**
 * 
 * 交易类型配置
 * <功能详细描述>
 * 
 * @author  yangkai
 * @version  [版本号, 2013-5-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TxnConfig
{
    /**
     * 交易类型名称
     */
    private String txnName;
    /**
     * 交易结果码
     */
    private String resultCode;
    
    private String resultSubCode;

	public String getTxnName()
    {
        return txnName;
    }

    public void setTxnName(String txnName)
    {
        this.txnName = txnName;
    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }
    public String getResultSubCode() {
		return resultSubCode;
	}

	public void setResultSubCode(String resultSubCode) {
		this.resultSubCode = resultSubCode;
	}

    
}
