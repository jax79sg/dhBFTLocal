/******************************************************************************
 * 		
 * 	 Copyright 2015, TRX Systems, Inc.  All Rights Reserved.
 * 
 *   TRX Systems, Inc.
 *   7500 Greenway Center Drive, Suite 420
 *   Greenbelt, Maryland  20770
 *   
 *   Tel:    (301) 313-0053
 *   email:  info@trxsystems.com
 * 
 *****************************************************************************/
package com.trx.neon.beta;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Still in beta, not yet ready for public consumption.  This class will eventually be used to 
 * provide access to new functionality for application developers.  
 * @author Dan
 */
public class AccountEvent implements Parcelable, INeonEventBeta
{
	public final Account account;
	
	public AccountEvent(Account account)
	{
		this.account = account;
	}
	
	private AccountEvent(Parcel in)
	{
		this.account = in.readParcelable(null);
	}
	
	/**
	 * Used for sending data across binders
	 */
	public static final Creator<AccountEvent> CREATOR = new Creator<AccountEvent>() {
		@Override
		public AccountEvent createFromParcel(Parcel in) {
			return new AccountEvent(in);
		}

		@Override
		public AccountEvent[] newArray(int size) {
			return new AccountEvent[size];
		}
	};	
	
	@Override
	public int describeContents() 
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeParcelable(account, flags);
	}
}
