package simlator.bank.cup.shzlcp;

public interface HelperUtil {
    /** 银行返回代码转换 */
    public static final String BANK_RESP_CODE = "mas.auth.00012000.resp_code_convertor";

    public static final String AUTH_CONFIG = "mas.auth.00012000.config";

    public static final String ACQ_INST_ID = "acq_inst_id";

    public static final String ICTRANSDATA = "ICTransData";

    public static final String ORG_AUTH_TRACE_NO = "org.auth_trace_no";

    public static final String ORG_SERV_ENTRY_MODE = "org.serv_entry_mode";

    public static final String ORG_SERINO = "org.seri_no";

    public static final String ORG_AUTH_BATCH_NO = "org.auth_batch_no";

    public static final String BANK_CLEAR_DATE = "bank.cleardate";

    public static final String PINBLOB = "PINBlob";

    public static final String PIN = "PIN";

    public static final String PIN_KEY = "PIN_key";

    public static final String MAC_KEY = "MAC_key";

    // 磁道秘钥
    public static final String TD_KEY = "TD_key";

    /**
     * 交易时间hhmmss
     */
    public static final String TXN_TIME = "txn_time";

    /**
     * 交易日期
     */
    public static final String TXN_DATE = "txn_date";

    /** 输入一个密钥，请求指令代码 */
    public static final String IMPORT_KEY_REQUEST = "A6";

    /** 输入一个密钥，应答指令代码 */
    public static final String IMPORT_KEY_RESPONSE = "A7";

    /** 用ANSI X9.19方式对大消息生成MAC的请求指令代码 */
    public static final String ANSI_X919_MAC_REQUEST = "MS";

    /** 用ANSI X9.19方式对大消息生成MAC的应答指令代码 */
    public static final String ANSI_X919_MAC_RESPONSE = "MT";

    /** 3des加密指令代码 */
    public static final String E0_KEY_REQUEST = "E0";

    /** E0 响应头部码 */
    public static final String E0_RESP_HEAD = "00000000E10010";

    /** 单倍长 */
    public static final String SINGLE_MODE = "Z";

    /** 双倍长 */
    public static final String DOUBLE_MODE = "X";

    /** 三倍长 */
    public static final String TREBLE_MODE = "Y";

    /** 区域主密钥 */
    public static final String ZMK = "000";

    /** 区域工作密钥 */
    public static final String ZPK = "001";

    /** 区域认证密钥 */
    public static final String ZAK = "008";

    /** 终端工作密钥 */
    public static final String TPK = "002";

    /** 终端认证密钥 */
    public static final String TAK = "004";// 004包含磁道秘钥
}
