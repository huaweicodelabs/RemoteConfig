package mono.com.huawei.hmf.tasks;


public class OnCanceledListenerImplementor
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.huawei.hmf.tasks.OnCanceledListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onCanceled:()V:GetOnCanceledHandler:Huawei.Hmf.Tasks.IOnCanceledListenerInvoker, Huawei.Hmf.Tasks\n" +
			"";
		mono.android.Runtime.register ("Huawei.Hmf.Tasks.IOnCanceledListenerImplementor, Huawei.Hmf.Tasks", OnCanceledListenerImplementor.class, __md_methods);
	}


	public OnCanceledListenerImplementor ()
	{
		super ();
		if (getClass () == OnCanceledListenerImplementor.class)
			mono.android.TypeManager.Activate ("Huawei.Hmf.Tasks.IOnCanceledListenerImplementor, Huawei.Hmf.Tasks", "", this, new java.lang.Object[] {  });
	}


	public void onCanceled ()
	{
		n_onCanceled ();
	}

	private native void n_onCanceled ();

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
