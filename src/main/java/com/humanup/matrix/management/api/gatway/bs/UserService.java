package com.humanup.matrix.management.api.gatway.bs;

import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;

public interface UserService {
    Account findOne(String accountMailAdresse);
}
