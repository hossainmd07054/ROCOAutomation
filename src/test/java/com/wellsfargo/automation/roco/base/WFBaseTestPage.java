package com.wellsfargo.automation.roco.base;

import com.qmetry.qaf.automation.ui.api.PageLocator;

public abstract class WFBaseTestPage {
	public abstract void waitForPageToLoad();
	public abstract void openPage(PageLocator locator, Object... args) ;

}
