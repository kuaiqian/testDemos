package simlator.bank.cup.shzlcp;

import org.apache.commons.lang.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import mas.base.AppExcCodes;
import mas.iso8583.ResponseCodes;
import mas.transaction.TxnCtrl;
import simlator.rpc.RpcSwitchService;
import steel.base.AppBizException;
import steel.base.AppRTException;
import steel.encode.BCDASCII;

public class SecurityService {
    private static final Logger logger = LogManager.getLogger(SecurityService.class);

    private RpcSwitchService rpcSwitchService;

    public byte[] calMacByHsm(String macKey, ISOMsg isoMsg) throws AppBizException {
        byte[] macBlock = MacHelper.getMacBlock(isoMsg);
        String macXorEcbStr = MacHelper.xorMabResultByECB(macBlock);
        byte[] highPart = macXorEcbStr.substring(0, 8).getBytes();
        byte[] lowPart = macXorEcbStr.substring(8).getBytes();
        // 高8位DES后与低8位异或结果再次DES
        byte[] firstBlock = doDesInHSM(macKey, highPart);
        byte[] secondBlock = MacHelper.doXor(firstBlock, lowPart);
        byte[] macEnBlock = doDesInHSM(macKey, secondBlock);
        String macStr = BCDASCII.fromBCDToASCIIString(macEnBlock, 0, macEnBlock.length * 2, false);
        return macStr.substring(0, 8).getBytes();
    }

    public boolean verifyMacByHsm(String mackey, ISOMsg isoMsg) throws AppBizException {
        byte[] preMab = null;
        try {
            isoMsg.setPackager(new ISO87TxnPackager());
            preMab = isoMsg.pack();
        }catch (ISOException e) {
            throw new AppBizException(AppExcCodes.ISO8583_MAC_CALC_ERROR,
                    "cal mac error because of isomsg pack error!");
        }
        byte[] macFromBankBytes = MacHelper.subByte(preMab, preMab.length - 8, preMab.length);
        String macFromBank = BCDASCII.fromBCDToASCIIString(macFromBankBytes, 0, macFromBankBytes.length * 2, false);
        byte[] result = calMacByHsm(mackey, isoMsg);
        String calResult = BCDASCII.fromBCDToASCIIString(result, 0, result.length * 2, false);
        return macFromBank.subSequence(0, 8).equals(calResult.subSequence(0, 8));
    }

    /**
     * 加密PIN
     *
     * @param txnCtrl
     * @param zpkOnHSM
     * @return
     * @throws AppBizException
     */
    public byte[] encreptPIN(TxnCtrl txnCtrl) throws AppBizException {
        String pin = ObjectUtils.toString(txnCtrl.getTunnelData(HelperUtil.PIN));
        byte[] pinBlocb = AnsiX98.convertToPinBlock(pin, txnCtrl.getPAN());
        String pinBlock = BCDASCII.fromBCDToASCIIString(pinBlocb, 0, pinBlocb.length * 2, false);
        StringBuffer requestMsg = new StringBuffer(HSMConst.X9_19_REQ_HEAD);
        requestMsg.append(ObjectUtils.toString(txnCtrl.getTunnelData(HelperUtil.PIN_KEY)));
        String mabLength = String.format("%1$04X", pinBlock.length() / 2);
        requestMsg.append(mabLength);
        requestMsg.append(pinBlock);
        logger.info("Racal message to be send: " + requestMsg.toString());
        String responseMsg = rpcSwitchService.getHostSerucityModuleService().racal(requestMsg.toString());
        logger.info("Racal message from HSM: " + responseMsg);
        int position = HSMConst.X99_RESP_HEAD.length();
        String respCode = responseMsg.substring(position - 2, position);
        if(ResponseCodes.SUCCESS.equals(respCode) == false) {
            throw new AppBizException("", "Racal response error code:[" + respCode + "]!");
        }
        String pinEncript = responseMsg.substring(position, position + 16);
        return BCDASCII.fromASCIIToBCD(pinEncript, 0, pinEncript.length(), false);
    }

    public String importKeyInToHSM(String encZMK, String encKey, String desMode, String checkValue)
            throws AppBizException {
        String msgTo = getRequestBody(encZMK, encKey, desMode);
        String msgRec = rpcSwitchService.getHostSerucityModuleService().racal(msgTo);
        String msgBlock = getResponseMsgBlock(msgRec);
        String keyInHSM = null;
        String checkValueInHSM = null;
        if(HelperUtil.SINGLE_MODE.equals(desMode)) {
            keyInHSM = msgBlock.substring(0, 16);
            checkValueInHSM = msgBlock.substring(16, 16 + 8);
        }else {
            keyInHSM = msgBlock.substring(0, 33);
            checkValueInHSM = msgBlock.substring(33, 33 + 8);
        }
        logger.info("checkValue={},checkValueInHSM={}", checkValue, checkValueInHSM);
        if(checkValue.equals(checkValueInHSM) == false) {
            throw new AppRTException(AppExcCodes.MAS_KEY_FILL_TYPE_ERROR, " Check Value mismatch");
        }
        return keyInHSM;
    }

    private String getRequestBody(String encZMK, String encKey, String desMode) {
        StringBuffer msgRequest = new StringBuffer();
        msgRequest.append(HSMConst.MSG_HEAD);
        msgRequest.append(HelperUtil.IMPORT_KEY_REQUEST);
        msgRequest.append("003");
        msgRequest.append(encZMK);
        if(HelperUtil.SINGLE_MODE.equals(desMode) == false) {
            msgRequest.append(desMode + encKey);
        }else {
            msgRequest.append(encKey);
        }
        msgRequest.append(desMode);
        logger.info("Racal message send to Hsm={}", msgRequest.toString());
        return msgRequest.toString();
    }

    private String getResponseMsgBlock(String msgRec) throws AppBizException {
        logger.info("Racal message received from Hsm={} ", msgRec);
        int headerLength = HSMConst.A7_RESP_HEAD.length();
        String respCode = msgRec.substring(headerLength - 2, headerLength);
        if(ResponseCodes.SUCCESS.equals(respCode) == false) {
            throw new AppBizException(AppExcCodes.MAS_KEY_FILL_TYPE_ERROR,
                    "Racal response error code:[" + respCode + "]!");
        }
        String msgBlock = msgRec.substring(HSMConst.A7_RESP_HEAD.length());
        return msgBlock;
    }

    private byte[] doDesInHSM(String macKey, byte[] data) throws AppBizException {
        StringBuffer requestMsg = new StringBuffer(HSMConst.X99_REQ_HEAD);
        String dataStr = BCDASCII.fromBCDToASCIIString(data, 0, data.length * 2, false);
        requestMsg.append(macKey);
        String mabLength = String.format("%1$04X", data.length);
        requestMsg.append(mabLength);
        requestMsg.append(dataStr);
        String responseMsg = rpcSwitchService.getHostSerucityModuleService().racal(requestMsg.toString());
        int respLength = HSMConst.X99_RESP_HEAD.length();
        String macStr = responseMsg.substring(respLength);
        return BCDASCII.fromASCIIToBCD(macStr, 0, macStr.length(), false);
    }

    public void setRpcSwitchService(RpcSwitchService rpcSwitchService) {
        this.rpcSwitchService = rpcSwitchService;
    }

    public RpcSwitchService getRpcSwitchService() {
        return rpcSwitchService;
    }
}
