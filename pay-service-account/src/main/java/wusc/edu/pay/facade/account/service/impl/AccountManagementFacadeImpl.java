package wusc.edu.pay.facade.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wusc.edu.pay.core.account.biz.AccountManagementBiz;
import wusc.edu.pay.facade.account.enums.AccountInitiatorEnum;
import wusc.edu.pay.facade.account.enums.AccountOperationTypeEnum;
import wusc.edu.pay.facade.account.enums.AccountTypeEnum;
import wusc.edu.pay.facade.account.exception.AccountBizException;
import wusc.edu.pay.facade.account.service.AccountManagementFacade;


/**
 * 账户管理
 *
 * @author healy
 */
@Component("accountManagementFacade")
public class AccountManagementFacadeImpl implements AccountManagementFacade {

    @Autowired
    private AccountManagementBiz accountManagementBiz;

    /**
     * 重新绑定商户编号
     */
    @Override
    public long reBindUserNo(String accountNo, String userNo) {
        return accountManagementBiz.reBindUserNo(accountNo, userNo);
    }

    /**
     * 生成账户编号
     *
     * @param accountType
     *         账户类型
     */
    @Override
    public String buildAccountNo(AccountTypeEnum accountType) {
        return accountManagementBiz.buildAccountNo(accountType);
    }

    /**
     * 创建账户
     *
     * @param userNo
     * @param accountNo
     * @param accountType
     * @return
     */
    @Override
    public long createAccount(String userNo, String accountNo, int accountType) {
        return accountManagementBiz.createAccount(userNo, accountNo, accountType);
    }

    @Override
    public long createPrivateAccount(String userNo, String accountNo, Double balance, Double securityMoney) throws AccountBizException {
        return accountManagementBiz.createPrivateAccount(userNo, accountNo, balance, securityMoney);
    }

    /**
     * 账户状态变更操作.
     *
     * @param userNo
     *         用户编号
     * @param operationType
     *         账户操作类型
     * @param initiator
     *         账户操作,发起方
     * @param desc
     *         变更操作说明
     */
    @Override
    public void changeAccountStatus(String userNo, AccountOperationTypeEnum operationType, AccountInitiatorEnum initiator, String desc) {
        accountManagementBiz.changeAccountStatus(userNo, operationType, initiator, desc);
    }

}
