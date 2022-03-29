package com.jagex.oldscape.pub;

public interface OAuthApi {
	boolean isOnLoginScreen();

	void setOtlTokenRequester(OtlTokenRequester var1);

	long getAccountHash();
}
