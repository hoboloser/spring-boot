package org.bin.schema.dao;

import org.bin.schema.entity.UserAccount;

public interface UserAccountDAO {

	
	/**
	 * 查询一条记录
	 * @return
	 */
	public UserAccount getAccountById(long userId);
}
