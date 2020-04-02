// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package mainmodule.proxies;

public class SearchParams extends system.proxies.Paging
{
	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "MainModule.SearchParams";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Category("Category"),
		Term("Term"),
		PageNumber("PageNumber"),
		IsSortable("IsSortable"),
		SortAttribute("SortAttribute"),
		SortAscending("SortAscending"),
		HasMoreData("HasMoreData");

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

	public SearchParams(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "MainModule.SearchParams"));
	}

	protected SearchParams(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject searchParamsMendixObject)
	{
		super(context, searchParamsMendixObject);
		if (!com.mendix.core.Core.isSubClassOf("MainModule.SearchParams", searchParamsMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a MainModule.SearchParams");
	}

	/**
	 * @deprecated Use 'SearchParams.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static mainmodule.proxies.SearchParams initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return mainmodule.proxies.SearchParams.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static mainmodule.proxies.SearchParams initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new mainmodule.proxies.SearchParams(context, mendixObject);
	}

	public static mainmodule.proxies.SearchParams load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return mainmodule.proxies.SearchParams.initialize(context, mendixObject);
	}

	/**
	 * @return value of Category
	 */
	public final java.lang.String getCategory()
	{
		return getCategory(getContext());
	}

	/**
	 * @param context
	 * @return value of Category
	 */
	public final java.lang.String getCategory(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Category.toString());
	}

	/**
	 * Set value of Category
	 * @param category
	 */
	public final void setCategory(java.lang.String category)
	{
		setCategory(getContext(), category);
	}

	/**
	 * Set value of Category
	 * @param context
	 * @param category
	 */
	public final void setCategory(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String category)
	{
		getMendixObject().setValue(context, MemberNames.Category.toString(), category);
	}

	/**
	 * @return value of Term
	 */
	public final java.lang.String getTerm()
	{
		return getTerm(getContext());
	}

	/**
	 * @param context
	 * @return value of Term
	 */
	public final java.lang.String getTerm(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Term.toString());
	}

	/**
	 * Set value of Term
	 * @param term
	 */
	public final void setTerm(java.lang.String term)
	{
		setTerm(getContext(), term);
	}

	/**
	 * Set value of Term
	 * @param context
	 * @param term
	 */
	public final void setTerm(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String term)
	{
		getMendixObject().setValue(context, MemberNames.Term.toString(), term);
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final mainmodule.proxies.SearchParams that = (mainmodule.proxies.SearchParams) obj;
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
		return "MainModule.SearchParams";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Override
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}