// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mainmodule.proxies.constants;

import com.mendix.core.Core;

public class Constants
{
	// These are the constants for the MainModule module

	public static java.lang.String getBASE_URL()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("MainModule.BASE_URL");
	}
}