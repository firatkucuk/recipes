// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mainmodule.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class Microflows
{
	// These are the microflows for the MainModule module
	public static java.util.List<mainmodule.proxies.Category> fetchCategories(IContext context)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		java.util.List<IMendixObject> objs = Core.microflowCall("MainModule.FetchCategories").withParams(params).execute(context);
		java.util.List<mainmodule.proxies.Category> result = null;
		if (objs != null)
		{
			result = new java.util.ArrayList<>();
			for (IMendixObject obj : objs)
				result.add(mainmodule.proxies.Category.initialize(context, obj));
		}
		return result;
	}
	public static java.util.List<mainmodule.proxies.Recipe> fetchRecipes(IContext context, mainmodule.proxies.SearchParams _searchParam)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("SearchParam", _searchParam == null ? null : _searchParam.getMendixObject());
		java.util.List<IMendixObject> objs = Core.microflowCall("MainModule.FetchRecipes").withParams(params).execute(context);
		java.util.List<mainmodule.proxies.Recipe> result = null;
		if (objs != null)
		{
			result = new java.util.ArrayList<>();
			for (IMendixObject obj : objs)
				result.add(mainmodule.proxies.Recipe.initialize(context, obj));
		}
		return result;
	}
}