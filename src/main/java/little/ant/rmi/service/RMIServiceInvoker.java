package little.ant.rmi.service;

import java.rmi.Naming;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.expocms.server.constant.Constants;

import little.ant.payment.pojo.GetDynTr2Entity;
import little.ant.payment.pojo.IndAuthEntity;
import little.ant.payment.pojo.IndAuthVerifyEntity;
import little.ant.payment.pojo.Pay2BankEntity;
import little.ant.payment.pojo.QuickPayReqEntity;
import little.ant.payment.pojo.RedPackageEntity;
import little.ant.sms.bo.SMSMsgEntity;
import little.ant.sms.bo.SMSSendResult;

public class RMIServiceInvoker {

    private static Logger logger = Logger.getLogger (RMIServiceInvoker.class);


    public Map<String, String> quickpay (final QuickPayReqEntity qpr) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            return asrmis.quickPay (qpr);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }
        return null;
    }


    public void refound (final Pay2BankEntity pae) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            asrmis.refound (pae);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }
    }


    public boolean SendSMS (final int type, final SMSMsgEntity smsMsgEntity) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            SMSSendResult result = null;
            if (type > 0) {
                result = asrmis.sendSMSCF (smsMsgEntity);
            } else {
                result = asrmis.sendSMSYes (smsMsgEntity);
            }
            if (result.getCode () < 0) {
                return false;
            }
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
            return false;
        }
        return true;
    }


    public List<RedPackageEntity> listRedPackage (final String userId) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            return asrmis.getRedPackage (userId);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
            return null;
        }
    }


    public int applyRedPackage (final String userId, final String redPackageID) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            final int flag = asrmis.applyRedPackage (userId, redPackageID);

            return flag;
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
            return -1;
        }
    }


    public void indAuth (final IndAuthEntity indAuthEntity) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            asrmis.indAuth (indAuthEntity);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }

    }


    public void indAuthVerify (final IndAuthVerifyEntity indAuthVerifyEntity) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            asrmis.indAuthVerify (indAuthVerifyEntity);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }

    }


    public Map<String, String> getDynTr2 (final GetDynTr2Entity getDynTr2Entity) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            return asrmis.getDynTr2 (getDynTr2Entity);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }
        return null;
    }


    public void autoOnboard (final String productType) {
        try {
            final AppSrvRMIService asrmis = (AppSrvRMIService)Naming.lookup (Constants.rmiserivce);
            asrmis.autoOnboard (productType);
        } catch (final Exception e) {
            logger.error (e.getMessage (), e);
        }
    }
}
