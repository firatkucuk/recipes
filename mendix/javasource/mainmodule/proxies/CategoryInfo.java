// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mainmodule.proxies;

public class CategoryInfo
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject categoryInfoMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MainModule.CategoryInfo";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Name("Name"),
		CategoryInfo_Categories("MainModule.CategoryInfo_Categories");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public CategoryInfo(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MainModule.CategoryInfo"));
	}

	protected CategoryInfo(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject categoryInfoMendixObject)
	{
		if (categoryInfoMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("MainModule.CategoryInfo", categoryInfoMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MainModule.CategoryInfo");

		this.categoryInfoMendixObject = categoryInfoMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'CategoryInfo.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static mainmodule.proxies.CategoryInfo initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return mainmodule.proxies.CategoryInfo.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static mainmodule.proxies.CategoryInfo initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new mainmodule.proxies.CategoryInfo(context, mendixObject);
	}

	public static mainmodule.proxies.CategoryInfo load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return mainmodule.proxies.CategoryInfo.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of Name
	 */
	public final java.lang.String getName()
	{
		return getName(getContext());
	}

	/**
	 * @param context
	 * @return value of Name
	 */
	public final java.lang.String getName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Name.toString());
	}

	/**
	 * Set value of Name
	 * @param name
	 */
	public final void setName(java.lang.String name)
	{
		setName(getContext(), name);
	}

	/**
	 * Set value of Name
	 * @param context
	 * @param name
	 */
	public final void setName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String name)
	{
		getMendixObject().setValue(context, MemberNames.Name.toString(), name);
	}

	/**
	 * @return value of CategoryInfo_Categories
	 */
	public final mainmodule.proxies.Categories getCategoryInfo_Categories() throws com.mendix.core.CoreException
	{
		return getCategoryInfo_Categories(getContext());
	}

	/**
	 * @param context
	 * @return value of CategoryInfo_Categories
	 */
	public final mainmodule.proxies.Categories getCategoryInfo_Categories(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		mainmodule.proxies.Categories result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.CategoryInfo_Categories.toString());
		if (identifier != null)
			result = mainmodule.proxies.Categories.load(context, identifier);
		return result;
	}

	/**
	 * Set value of CategoryInfo_Categories
	 * @param categoryinfo_categories
	 */
	public final void setCategoryInfo_Categories(mainmodule.proxies.Categories categoryinfo_categories)
	{
		setCategoryInfo_Categories(getContext(), categoryinfo_categories);
	}

	/**
	 * Set value of CategoryInfo_Categories
	 * @param context
	 * @param categoryinfo_categories
	 */
	public final void setCategoryInfo_Categories(com.mendix.systemwideinterfaces.core.IContext context, mainmodule.proxies.Categories categoryinfo_categories)
	{
		if (categoryinfo_categories == null)
			getMendixObject().setValue(context, MemberNames.CategoryInfo_Categories.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.CategoryInfo_Categories.toString(), categoryinfo_categories.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return categoryInfoMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final mainmodule.proxies.CategoryInfo that = (mainmodule.proxies.CategoryInfo) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "MainModule.CategoryInfo";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
